package com.womeng;

import cn.hutool.core.bean.BeanUtil;
import com.womeng.entity.FileInfo;
import com.womeng.entity.FileNode;
import com.womeng.entity.TrashBin;
import com.womeng.mapper.FileInfoMapper;
import com.womeng.utils.RedisUtil;
import freemarker.core.Environment;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@SpringBootTest
public class MyTest {

    @Autowired
    private  RedisUtil redisUtil;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private static final String signKey = "womeng";
    private static final Long expire = 60*60*24*1000L;

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Test
    public void testId(){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoi6YaJ5Y2n5qKmIiwiaWQiOjE5LCJleHAiOjE3MjYwNDIzNTYsInVzZXJuYW1lIjoienVpd29tZW5nIn0.zG_Mr2uFgRk_k6cv1etvxkuLBauFCny_A4XYPDA8cbM")
                .getBody();

        System.out.println(claims.toString());
    }

    @Test
    void testJWT(){
        String jwt = (String) redisUtil.get("token");
        System.out.println(jwt);
    }

    @Test
    void testRedisFlush(){
        String[] keys = Objects.requireNonNull(redisTemplate.opsForValue().getOperations().keys("*" + "user" + "*")).toArray(new String[0]);
        System.out.println("清理前：keys = " + Arrays.toString(keys));

        for (String key : keys) {
            redisTemplate.opsForValue().getOperations().delete(key);
        }

        String[] keys2 = Objects.requireNonNull(redisTemplate.opsForValue().getOperations().keys("*" + "user" + "*")).toArray(new String[0]);
        System.out.println("清理后：keys = " + Arrays.toString(keys2));
    }

    @Test
    void testTransform(){
        FileInfo fileInfo = fileInfoMapper.selectById(2);
        TrashBin trashBin = BeanUtil.copyProperties(fileInfo, TrashBin.class);
        log.info("trashBin = {}",trashBin);
    }

    @Test
    void testGetFileTree(){
        FileInfo fileInfo = fileInfoMapper.selectById(2);
        FileNode fileTree = getFileTree(fileInfo, null);
        System.out.println(fileTree);
    }

    private FileNode getFileTree(FileInfo source ,Integer depth) {
        // 查询当前目录下的所有文件和子目录
        List<FileInfo> files = fileInfoMapper.selectNextList(source.getId());
        // 将父目录转换为文件节点
        FileNode parentNode = BeanUtil.copyProperties(source, FileNode.class);
        // 设置节点深度
        parentNode.setDepth(Objects.requireNonNullElse(depth, 0));

        ArrayList<FileNode> children = new ArrayList<>();
        files.forEach(file ->{
            FileNode childNode;
            if (file.getIsDir()){
                // 如果是目录，进一步搜索子树
                childNode = getFileTree(file,parentNode.getDepth() + 1);
            }else {
                // 否则直接转换为 叶子节点
                childNode = BeanUtil.copyProperties(file, FileNode.class);
                childNode.setDepth(parentNode.getDepth() + 1);
            }
            children.add(childNode);
        });
        parentNode.setChildren(children);
        return parentNode;
    }


}

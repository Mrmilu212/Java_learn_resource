package com.womeng.mapper;

import com.womeng.entity.FileInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 文件表 Mapper 接口
 * </p>
 *
 * @author womeng
 * @since 2024-09-20
 */
@Mapper
public interface FileInfoMapper extends BaseMapper<FileInfo> {

    Long getRootPathId(String userId, String username, Long parentId);

    List<FileInfo> selectByParentId(Long fileId);

    List<FileInfo> selectNextList(Long fileId);

    List<FileInfo> getFilePath(Long id);
}

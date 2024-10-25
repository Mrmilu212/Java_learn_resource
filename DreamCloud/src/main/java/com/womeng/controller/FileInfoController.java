package com.womeng.controller;

import com.womeng.entity.Result;
import com.womeng.entity.dto.MultiFileDto;
import com.womeng.entity.dto.FileInfoDto;
import com.womeng.service.IFileInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 文件表 前端控制器
 * </p>
 *
 * @author womeng
 * @since 2024-09-20
 */
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileInfoController {

    private final IFileInfoService fileInfoService;

    /**
     *
     * @param fileId 文件id
     * @param userId 用户id
     * @return 统一响应结果
     */
    @GetMapping("/openfolder")
    public Result openFolder(@RequestParam("fileId") Long fileId ,@RequestParam("userId") String userId ) {
        FileInfoDto info = new FileInfoDto();
        info.setFileId(fileId);
        info.setUserId(userId);
        return fileInfoService.getFileList(info);
    }

    /**
     * 预览文件接口
     * @return
     */
    @PutMapping("/preview")
    public Result preview() {
        return null;
    }

    /**
     * 修改文件名称
     * @param info 包含目标文件id和新文件名的请求体
     * @return 统一响应结果
     */
    @PutMapping("/updatefilename")
    public Result updateFileName(@RequestBody FileInfoDto info) {
        return fileInfoService.updateFileName(info);
    }

    /**
     * 删除文件
     *
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody MultiFileDto info) {
        return fileInfoService.delete(info);
    }


    /**
     * 移动文件
     *
     * @return
     */
    @PutMapping("/move")
    public Result move(@RequestBody FileInfoDto fileInfoDto) {
        return fileInfoService.move(fileInfoDto);
    }

    /**
     *
     * @param info
     * @return
     */
    @PostMapping("/create")
    public Result createFolder(@RequestBody FileInfoDto info){
        return fileInfoService.createFolder(info);
    }

    /**
     * 上传文件
     *
     * @return
     */
    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file,
                             @ModelAttribute FileInfoDto Info) throws IOException {
        return fileInfoService.uploadFile(file,Info);
    }

    /**
     * 下载文件
     *
     * @return
     */
    @GetMapping("/download")
    public Result download(@RequestBody MultiFileDto info) {
        return fileInfoService.download(info);
    }

}

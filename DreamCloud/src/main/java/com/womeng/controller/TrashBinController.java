package com.womeng.controller;


import com.womeng.entity.Result;
import com.womeng.entity.dto.FileInfoDto;
import com.womeng.entity.dto.MultiFileDto;
import com.womeng.service.ITrashBinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 回收站 前端控制器
 * </p>
 *
 * @author womeng
 * @since 2024-09-20
 */
@RestController
@RequestMapping("/trashBin")
public class TrashBinController {

    @Autowired
    private ITrashBinService trashBinService;

    /**
     * 查看回收站
     * @param info 包含用户id的请求体
     * @return 统一响应结果
     */
    @GetMapping("/getlist")
    public Result trashBin(@RequestBody FileInfoDto info) {
        return trashBinService.getTrashBinList(info);
    }

    /**
     * 还原文件
     *
     * @return
     */

    @PutMapping("/recover")
    public Result recover(@RequestBody MultiFileDto info) {
        return trashBinService.recover(info);
    }

    /**
     * 清除回收站
     *
     * @return
     */
    @DeleteMapping("/clean")
    public Result cleanTrashBin(@RequestBody MultiFileDto info) {
        return trashBinService.clean(info);
    }

}

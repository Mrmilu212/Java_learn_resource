package com.womeng.service;

import com.womeng.entity.Result;
import com.womeng.entity.TrashBin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.womeng.entity.dto.FileInfoDto;
import com.womeng.entity.dto.MultiFileDto;

/**
 * <p>
 * 回收站 服务类
 * </p>
 *
 * @author womeng
 * @since 2024-09-20
 */
public interface ITrashBinService extends IService<TrashBin> {

    Result getTrashBinList(FileInfoDto info);

    Result recover(MultiFileDto info);

    Result clean(MultiFileDto info);
}

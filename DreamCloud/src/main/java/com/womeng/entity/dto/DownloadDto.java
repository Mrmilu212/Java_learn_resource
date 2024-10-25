package com.womeng.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadDto {

    private List<String> urls;

    private List<byte[]> zips;

}

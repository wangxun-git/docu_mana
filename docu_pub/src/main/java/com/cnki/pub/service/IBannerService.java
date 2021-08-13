package com.cnki.pub.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cnki.common.entity.home.Banner;

import java.util.List;

public interface IBannerService extends IService<Banner> {

    /**
     * 获取banner列表
     * @param index
     * @param number
     * @return
     */
    List<Banner> getBannerListByIndex(Integer index, Integer number);

}

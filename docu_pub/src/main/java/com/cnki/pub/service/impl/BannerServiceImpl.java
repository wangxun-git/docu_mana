package com.cnki.pub.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cnki.common.constant.DocuConstant;
import com.cnki.common.entity.home.Banner;
import com.cnki.common.mapper.home.BannerMapper;
import com.cnki.common.utils.DocuManaUtils;
import com.cnki.common.utils.EnvironUtils;
import com.cnki.common.utils.RedisUtils;
import com.cnki.pub.service.IBannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private EnvironUtils environUtils;

    @Override
    public List<Banner> getBannerListByIndex(Integer index, Integer number) {
        if (DocuManaUtils.isEmpty(number)) {
            number = environUtils.getPageNumber();
        }
        //从redis缓存中获取数据
        List<Banner> bannerList = (List<Banner>) redisUtils.getList(DocuConstant.Redis.BANNER, 0, -1);
        if (DocuManaUtils.isEmpty(bannerList)) {
            QueryWrapper<Banner> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByDesc("created");
            Page<Banner> page = new Page(index, number);
            Page<Banner> bannerPage = baseMapper.selectPage(page, queryWrapper);

            bannerList = bannerPage.getRecords();

            //将banner列表放入缓存
            redisUtils.setList(DocuConstant.Redis.BANNER, bannerList);
        }
        return bannerList;
    }
}

package com.cnki.common.entity.home;

import com.baomidou.mybatisplus.annotation.TableName;
import com.cnki.common.entity.BaseEntity;
import lombok.Data;

@Data
@TableName(value = "banner")
public class Banner extends BaseEntity {

    private Integer bannerId;

    private String title;

    private String imgUrl;

    private String href;

}

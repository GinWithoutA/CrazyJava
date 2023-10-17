package com.ginwithouta.redis.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Package : com.ginwithouta.redis.entity
 * @Author : NONO Wang
 * @Date : 2023 - 9月 - 周五
 * @Desc :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "聚划算活动Product信息")
public class Product {
    @Schema(description = "产品ID")
    private Long id;
    @Schema(description = "产品名称")
    private String name;
    @Schema(description = "产品价格")
    private Integer price;
    @Schema(description = "产品详情信息")
    private String details;
}

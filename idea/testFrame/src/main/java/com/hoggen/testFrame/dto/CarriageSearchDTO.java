package com.hoggen.testFrame.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import java.util.List;

/**
 * @author baogang
 */
@Data
@Accessors(chain = true)
@ApiModel("运费规则搜索")
public class CarriageSearchDTO {

    @Min(1)
    @ApiModelProperty(value = "页码",required = true)
    private Integer pageIndex;

    @Min(1)
    @ApiModelProperty(value = "页大小",required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "编码")
    private List<String> codes;

    @ApiModelProperty(value = "文本描述")
    private String text;

    @ApiModelProperty(value = "区域编码")
    private String areaCode;
}
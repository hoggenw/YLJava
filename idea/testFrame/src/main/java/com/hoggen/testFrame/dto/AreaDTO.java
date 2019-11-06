package com.kykj.internethospital.express.web.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author baogang
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("区域编码视图")
public class AreaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编码",required = true)
    @NotBlank
    private String code;

    @ApiModelProperty(value = "文本描述",required = true)
    @NotBlank
    private String text;
}
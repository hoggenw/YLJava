package com.hoggen.testFrame.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author baogang
 */
@Data
@Accessors(chain = true)
@ApiModel("运费规则读取视图")
public class CarriageReadDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @NotBlank
    private String id;


    @ApiModelProperty(value = "编码")
    @NotBlank
    private String code;


    @ApiModelProperty(value = "文本描述")
    @NotBlank
    private String text;

    @ApiModelProperty(value = "长文本描述")
    @NotBlank
    private String longText;

    @ApiModelProperty(value = "金额", example = "0.00")
    @NotNull
    private BigDecimal amount;

    @ApiModelProperty(value = "区域")
    @NotEmpty
    private List<AreaDTO> areas;

    @ApiModelProperty(value = "机构id")
    @NotEmpty
    private String organizationId;

    @ApiModelProperty(value = "机构编码")
    @NotBlank
    private String organizationCode;

    @ApiModelProperty(value = "机构文本")
    @NotBlank
    private String organizationText;
}
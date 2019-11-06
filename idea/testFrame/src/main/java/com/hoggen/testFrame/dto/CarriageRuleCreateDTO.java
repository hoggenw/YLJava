package com.kykj.internethospital.express.web.rest.dto;

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
@ApiModel("运费规则创建视图")
public class CarriageRuleCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文本描述", required = true)
    @NotBlank
    private String text;

    @ApiModelProperty(value = "长文本描述", required = true)
    @NotBlank
    private String longText;


    @ApiModelProperty(value = "机构id", required = true)
    @NotBlank
    private String organizationId;

    @ApiModelProperty(value = "机构编码", required = true)
    @NotBlank
    private String organizationCode;

    @ApiModelProperty(value = "机构描述", required = true)
    @NotBlank
    private String organizationText;

    @ApiModelProperty(value = "金额", example = "0.00", required = true)
    @NotNull
    private BigDecimal amount;

    @ApiModelProperty(value = "区域", required = true)
    @NotEmpty
    private List<AreaDTO> areas;


}
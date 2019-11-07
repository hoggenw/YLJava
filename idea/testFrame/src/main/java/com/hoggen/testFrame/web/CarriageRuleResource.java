package com.hoggen.testFrame.web;

import com.hoggen.testFrame.dto.CarriageReadDTO;
import com.hoggen.testFrame.dto.CarriageRuleCreateDTO;
import com.hoggen.testFrame.dto.CarriageRuleUpdateDTO;
import com.hoggen.testFrame.dto.CarriageSearchDTO;
import com.hoggen.testFrame.service.Impl.CarriageRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "运费规则")
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CarriageRuleResource {
    private final CarriageRuleService service;


    @ApiOperation("创建运费规则")
    @PostMapping(value = "organizations/{organizationCode}/carriageRules/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    public CarriageReadDTO createOne(@PathVariable @ApiParam(value = "机构编码",required = true) String organizationCode,
                                     @PathVariable String code,
                                     @Validated @RequestBody CarriageRuleCreateDTO createDTO) {
        createDTO.setOrganizationCode(organizationCode);
        CarriageReadDTO temp = service.createOne(organizationCode, code, createDTO);
         return temp;
    }

    @ApiOperation("修改运费规则")
    @PutMapping(value = "organizations/{organizationCode}/carriageRules/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    public CarriageReadDTO updateOne(@PathVariable @ApiParam(value = "机构编码",required = true) String organizationCode,
                                     @PathVariable String code,
                                     @Validated @RequestBody CarriageRuleUpdateDTO updateDTO) {
        return service.updateOne(organizationCode, code, updateDTO);
    }

    @ApiOperation("删除运费规则")
    @DeleteMapping(value = "organizations/{organizationCode}/carriageRules/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOne(@PathVariable @ApiParam(value = "机构编码",required = true) String organizationCode,
                          @PathVariable @ApiParam(value = "编码",required = true) String code) {
        service.deleteOne(organizationCode, code);
    }

    @ApiOperation("按编码查询运费详情")
    @GetMapping(value = "organizations/{organizationCode}/carriageRules/{code}")
    @ResponseStatus(HttpStatus.OK)
    public CarriageReadDTO findOneByCode(@PathVariable @ApiParam(value = "机构编码",required = true) String organizationCode,
                                         @PathVariable @ApiParam(value = "编码",required = true) String code) {
        return service.viewOneByCode(organizationCode, code);
    }

    @ApiOperation("按区域和组织查询运费详情")
    @GetMapping(value = "organizations/{organizationCode}/areas/{areaCode}/carriageRule")
    @ResponseStatus(HttpStatus.OK)
    public CarriageReadDTO findOneAreaCode(@PathVariable @ApiParam(value = "机构编码",required = true) String organizationCode,
                                           @PathVariable @ApiParam(value = "区域编码",required = true) String areaCode) {
        return service.viewOneByAreaCode(organizationCode, areaCode);
    }

    @ApiOperation("查询运费规则列表")
    @GetMapping(value = "organizations/{organizationCode}/carriageRules")
    @ResponseStatus(HttpStatus.OK)
    public Page<CarriageReadDTO> find(@PathVariable @ApiParam(value = "机构编码",required = true) String organizationCode,
                                      @Validated CarriageSearchDTO searchDTO) {
        return service.find(organizationCode, searchDTO);
    }
}

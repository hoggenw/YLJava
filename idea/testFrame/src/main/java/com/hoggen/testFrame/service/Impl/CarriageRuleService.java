package com.hoggen.testFrame.service.Impl;

import com.hoggen.testFrame.domain.CarriageEntity;
import com.hoggen.testFrame.domain.CarriageEntity_;
import com.hoggen.testFrame.dto.*;
import com.hoggen.testFrame.errors.DataHasCreateException;
import com.hoggen.testFrame.errors.DataNotFoundException;
import com.hoggen.testFrame.mapper.CarriageMapper;
import com.hoggen.testFrame.repository.CarriageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
//在@Transactional注解中如果不配置rollbackFor属性,那么事物只会在遇到RuntimeException的时候才会回滚,加上rollbackFor=Exception.class,可以让事物在遇到非运行时异常时也回滚
@Transactional(rollbackFor = Exception.class)
//不用写写很多个 @Autowired 很麻烦
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@CacheConfig(cacheNames = "carriages")
public class CarriageRuleService {
    private final CarriageRepository ruleRepository;

    private final CarriageMapper beanMapper;

    @CacheEvict
    public CarriageReadDTO createOne(String organizationCode, String code, CarriageRuleCreateDTO createDTO) {
        ruleRepository.findByOrganizationCodeAndCode(organizationCode, code).stream().findAny().ifPresent(rule -> {
            throw new DataHasCreateException(code, "运费");
        });

        createDTO.getAreas().forEach(areaDTO -> {
            ruleRepository.findByOrganizationCodeAndAreaCode(createDTO.getOrganizationCode(), areaDTO.getCode())
                    .ifPresent(carriage -> {
                        throw new DataHasCreateException(code, "机构区域运费");
                    });
            CarriageEntity rule = beanMapper.toEntity(createDTO);
            rule.setCode(code);
            rule.setAreaCode(areaDTO.getCode());
            rule.setAreaText(areaDTO.getText());
            ruleRepository.save(rule);
        });
        CarriageReadDTO temp =  viewOneByCode(organizationCode, code);
        return temp;
    }

    @CacheEvict(condition = "#organizationCode==#result.organizationCode")
    public CarriageReadDTO updateOne(String organizationCode, String code, CarriageRuleUpdateDTO updateDTO) {
        List<CarriageEntity> carriageRules = ruleRepository.findByOrganizationCodeAndCode(organizationCode, code);
        if (CollectionUtils.isEmpty(carriageRules)) {
            throw new DataNotFoundException(code, "运费");
        }
        ruleRepository.deleteAll(carriageRules);
        CarriageEntity oldCarriageEntity = carriageRules.get(0);
        updateDTO.getAreas().forEach(areaDTO -> {
            ruleRepository.findByOrganizationCodeAndAreaCode(oldCarriageEntity.getOrganizationCode(), areaDTO.getCode())
                    .ifPresent(carriage -> {
                        throw new DataHasCreateException(code, "机构区域运费");
                    });
            CarriageEntity rule = beanMapper.toEntity(updateDTO);
            rule.setAreaCode(areaDTO.getCode());
            rule.setAreaText(areaDTO.getText());
            rule.setOrganizationId(oldCarriageEntity.getOrganizationId());
            rule.setOrganizationCode(oldCarriageEntity.getOrganizationCode());
            rule.setOrganizationText(oldCarriageEntity.getOrganizationText());
            rule.setCode(code);
            ruleRepository.save(rule);
        });
        return viewOneByCode(organizationCode, code);
    }

    @CacheEvict
    public void deleteOne(String organizationCode, String code) {
        ruleRepository.deleteAll(ruleRepository.findByOrganizationCodeAndCode(organizationCode, code));
    }


    @Cacheable
    public CarriageReadDTO viewOneByCode(String organizationCode, String code) {
        List<CarriageEntity> carriageRules = ruleRepository.findByOrganizationCodeAndCode(organizationCode, code);
        if (CollectionUtils.isEmpty(carriageRules)) {
            throw new DataNotFoundException(code, "运费");
        }
        CarriageReadDTO ruleReadDTO = beanMapper.toRead(carriageRules.get(0));
        List<AreaDTO> areas = new ArrayList<>();
        carriageRules.forEach(carriageRule -> areas.add(new AreaDTO(carriageRule.getAreaCode(), carriageRule.getAreaText())));
        ruleReadDTO.setAreas(areas);
        return ruleReadDTO;
    }

    @Cacheable
    public CarriageReadDTO viewOneByAreaCode(String organizationCode, String areaCode) {
        List<CarriageEntity> carriageRules = ruleRepository.findByOrganizationCode(organizationCode);
        if (carriageRules.isEmpty()) {
            CarriageReadDTO ruleReadDTO = new CarriageReadDTO();
            ruleReadDTO.setCode("default");
            ruleReadDTO.setAmount(BigDecimal.ZERO);
            return ruleReadDTO;
        }
        CarriageEntity carriageEntity = carriageRules.stream().filter(carriageRule -> areaCode.equals(carriageRule.getAreaCode()))
                .findFirst().orElse(null);
        if (carriageEntity == null) {
            String regionCode = StringUtils.substring(areaCode, 0, 6);
            carriageEntity = carriageRules.stream().filter(carriageRuleArea -> regionCode.equals(carriageRuleArea.getAreaCode()))
                    .findFirst().orElse(null);
        }
        if (carriageEntity == null) {
            String cityCode = StringUtils.substring(areaCode, 0, 4) + "00";
            carriageEntity = carriageRules.stream().filter(carriageRuleArea -> cityCode.equals(carriageRuleArea.getAreaCode()))
                    .findFirst().orElse(null);
        }
        if (carriageEntity == null) {
            String provinceCode = StringUtils.substring(areaCode, 0, 2) + "0000";
            carriageEntity = carriageRules.stream().filter(carriageRuleArea -> provinceCode.equals(carriageRuleArea.getAreaCode()))
                    .findFirst().orElse(null);
        }
        if (carriageEntity == null) {
            CarriageReadDTO ruleReadDTO = new CarriageReadDTO();
            ruleReadDTO.setCode("default");
            ruleReadDTO.setAmount(BigDecimal.ZERO);
            return ruleReadDTO;
        }
        return viewOneByCode(organizationCode, carriageEntity.getCode());
    }

    public Page<CarriageReadDTO> find(String organizationCode, CarriageSearchDTO searchDTO) {
        return ruleRepository.findAll((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            query.groupBy(root.get(CarriageEntity_.code));
            predicates.add(builder.equal(root.get(CarriageEntity_.organizationCode), organizationCode));
            if (CollectionUtils.isNotEmpty(searchDTO.getCodes())) {
                In<String> codeIn = builder.in(root.get(CarriageEntity_.code));
                searchDTO.getCodes().forEach(codeIn::value);
                predicates.add(codeIn);
            }
            if (StringUtils.isNotBlank(searchDTO.getAreaCode())) {
                predicates.add(builder.equal(root.get(CarriageEntity_.areaCode), searchDTO.getAreaCode()));
            }
            if (StringUtils.isNotBlank(searchDTO.getText())) {
                predicates.add(builder.like(root.get(CarriageEntity_.text), searchDTO.getText() + "%"));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        }, PageRequest.of(searchDTO.getPageIndex() - 1, searchDTO.getPageSize()))
                .map(carriageRule -> viewOneByCode(carriageRule.getOrganizationCode(), carriageRule.getCode()));
    }
}

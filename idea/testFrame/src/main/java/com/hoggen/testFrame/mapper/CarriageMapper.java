package com.kykj.internethospital.express.mapper;


import com.kykj.internethospital.express.domain.CarriageEntity;
import com.kykj.internethospital.express.web.rest.dto.CarriageReadDTO;
import com.kykj.internethospital.express.web.rest.dto.CarriageRuleCreateDTO;
import com.kykj.internethospital.express.web.rest.dto.CarriageRuleUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;

/**
 * @author baogang
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface CarriageMapper {

    /**
     * 创建视图转换实体
     *
     * @param createDTO 创建视图
     * @return 实体
     */
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "available", ignore = true)
    @Mapping(target = "areaText", ignore = true)
    @Mapping(target = "areaCode", ignore = true)
    CarriageEntity toEntity(CarriageRuleCreateDTO createDTO);

    /**
     * 更新视图转换实体
     *
     * @param updateDTO 更新视图
     * @return 实体
     */
    @Mapping(target = "organizationText", ignore = true)
    @Mapping(target = "organizationId", ignore = true)
    @Mapping(target = "organizationCode", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "available", ignore = true)
    @Mapping(target = "areaText", ignore = true)
    @Mapping(target = "areaCode", ignore = true)
    CarriageEntity toEntity(CarriageRuleUpdateDTO updateDTO);

    /**
     * 创建视图转换实体
     *
     * @param po 实体
     * @return 查询视图
     */
    @Mapping(target = "areas", ignore = true)
    CarriageReadDTO toRead(CarriageEntity po);
}

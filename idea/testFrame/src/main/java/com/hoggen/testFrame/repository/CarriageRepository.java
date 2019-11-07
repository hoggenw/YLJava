package com.hoggen.testFrame.repository;

import com.hoggen.testFrame.domain.CarriageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface CarriageRepository extends JpaRepository<CarriageEntity, String>, JpaSpecificationExecutor<CarriageEntity> {
    /**
     * 按机构编码和编码查询运费
     *
     * @param organizationCode 机构编码
     * @param code             编码
     * @return 运费列表
     **/
    List<CarriageEntity> findByOrganizationCodeAndCode(String organizationCode, String code);

    /**
     * 按机构编码查询运费
     *
     * @param organizationCode 机构编码
     * @return 运费列表
     **/
    List<CarriageEntity> findByOrganizationCode(String organizationCode);

    /**
     * 按机构编码和区域编码查询运费
     *
     * @param organizationCode 机构编码
     * @param areaCode         区域编码
     * @return 单条运费
     **/
    Optional<CarriageEntity> findByOrganizationCodeAndAreaCode(String organizationCode, String areaCode);

}

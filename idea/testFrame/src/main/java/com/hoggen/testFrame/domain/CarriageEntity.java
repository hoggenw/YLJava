package com.hoggen.testFrame.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
//import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2019-03-28
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "kykj_carriage_rule")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Data
@Accessors(chain = true)
@SQLDelete(sql = "update kykj_carriage_rule set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
//@Audited
public class CarriageEntity extends AbstractAuditingEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 编码
     */
    private String code;

    /**
     * 文本
     */
    private String text;

    /**
     * 长文本描述
     */
    private String longText;


    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 区域编码
     */
    private String areaCode;


    /**
     * 区域描述
     */
    private String areaText;

    /**
     * 机构id
     */
    private String organizationId;


    /**
     * 机构编码
     */
    private String organizationCode;

    /**
     * 机构文本
     */
    private String organizationText;


}

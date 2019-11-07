package com.hoggen.testFrame.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
//chain ，@Accessors用于配置getter和setter方法的生成结果，下面介绍三个属性
//fluent的中文含义是流畅的，设置为true，则getter和setter方法的方法名都是基础属性名，且setter方法返回当前对象。
//prefix的中文含义是前缀，用于生成getter和setter方法的字段名会忽视指定前缀（遵守驼峰命名)
//chain的中文含义是链式的，设置为true，则setter方法返回当前对象
@Accessors(chain = true)
//在项目开发中使用JPA的@MappedSuperclass注解将实体类的多个属性分别封装到不同的非实体类中。
// 例如，数据库表中都需要id来表示编号，id是这些映射实体类的通用的属性，交给jpa统一生成主键id编号，
// 那么使用一个父类来封装这些通用属性，并用@MappedSuperclas标识
//需要依赖库spring-boot-starter-data-jpa
@MappedSuperclass
//EntityListeners在jpa中使用，如果你是mybatis是不可以用的
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity implements Serializable {

/**
 * 1.@MappedSuperclass注解使用在父类上面，是用来标识父类的作用
 *
 * 2.@MappedSuperclass标识的类表示其不能映射到数据库表，因为其不是一个完整的实体类，但是它所拥有的属性能够映射在     其子类对用的数据库表中
 *
 * 3.@MappedSuperclass标识得类不能再有@Entity或@Table注解  但是可以使用@Id 和@Column注解

 * */
    private static final long serialVersionUID = 1L;

    @Id
// @GeneratedValue注解存在的意义主要就是为一个实体生成一个唯一标识的主键
// (JPA要求每一个实体Entity,必须有且只有一个主键)@GeneratedValue提供了主键的生成策略。
    @GeneratedValue(generator = "uuid")
    //@GenericGenerator注解中name属性一致，strategy属性表示hibernate的主键生成策略，
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @CreatedBy
    @Column(columnDefinition = "varchar(50) comment '创建者'", updatable = false)
    //实体类中快照属性上加注解@JsonIgnore，那么最后返回的json数据，将不会包含
    @JsonIgnore
    // @NotAudited 标记该属性不支持数据修改记录支持
//    @NotAudited
    private String createdBy;


    @Column(columnDefinition = "tinyint(1) default '0' comment '删除 0：未删除 1：已删除'")
    @JsonIgnore
//    @NotAudited
    private Boolean deleted = false;

    @Column(columnDefinition = "tinyint(1) default '1' comment '是否可用 0：禁用 1：可用'")
    @JsonIgnore
//    @NotAudited
    private Boolean available = true;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "datetime comment '创建日期'", updatable = false)

//    @NotAudited
    private LocalDateTime createdDate = LocalDateTime.now();

    @LastModifiedBy
    @Column(columnDefinition = "varchar(50) comment '最后修改者'")
    @JsonIgnore
    private String lastModifiedBy;

    @LastModifiedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "datetime comment '最后修改日期'")
    @JsonIgnore
//    @NotAudited
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

}

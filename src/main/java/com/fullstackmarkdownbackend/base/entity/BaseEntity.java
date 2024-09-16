package com.fullstackmarkdownbackend.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

/**
 * packageName    : com.fullstackmarkdownbackend.common.entity
 * fileName       : BaseEntity
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedBy
    @Column(name = "created_by")
    protected Long createdBy;

    @CreatedDate
    @Column(name = "created_date")
    protected Timestamp createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected Long lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected Timestamp lastModifiedDate;

}

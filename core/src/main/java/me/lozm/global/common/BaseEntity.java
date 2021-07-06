package me.lozm.global.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import me.lozm.global.code.UseYn;
import me.lozm.global.code.converter.UseYnConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {

    @Column(name = "CREATED_DATETIME", updatable = false)
    private LocalDateTime createdDateTime;

    @Column(name = "MODIFIED_DATETIME")
    private LocalDateTime modifiedDateTime;

    @Setter
    @Column(name = "CREATED_BY", updatable = false, nullable = false)
    private Long createdBy;

    @Setter
    @Column(name = "MODIFY_BY")
    private Long modifiedBy;

    @Setter
    @Column(name = "USE_YN")
    @Convert(converter = UseYnConverter.class)
    private UseYn use = UseYn.USE;

}


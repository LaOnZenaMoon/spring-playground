package me.lozm.domain.user.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import me.lozm.global.code.UseYn;
import me.lozm.global.code.UsersType;
import me.lozm.global.code.converter.UsersTypeConverter;
import me.lozm.global.common.BaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;


@Entity
@Table(schema = "LOZM", name = "USERS")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "USER_SEQ_GEN", sequenceName = "USER_SEQ", initialValue = 1, allocationSize = 50)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GEN")
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IDENTIFIER")
    private String identifier;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TYPE")
    @Convert(converter = UsersTypeConverter.class)
    private UsersType type;


    public void edit(String name, String encodedPassword, UsersType type, Long modifiedBy, UseYn use) {
        this.name = StringUtils.isEmpty(name) ? this.name : name;
        this.password = StringUtils.isEmpty(encodedPassword) ? this.password : encodedPassword;
        this.type = ObjectUtils.isEmpty(type) ? this.type : type;
        setModifiedBy(modifiedBy);
        setUse(use);
    }

}

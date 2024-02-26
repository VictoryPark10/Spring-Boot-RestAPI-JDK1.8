package com.vicsoft.basicapi8.entity.user;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_USER")
public class UserEntity {

    @Id
    private long seq;

    @Column(name = "ID", length = 20, nullable = false)
    private String identifier;

    @Column(length = 64, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String name;

    private int age;

    @Column(name = "REGIST_DATE", length = 20, nullable = false)
    private LocalDateTime registDate;

    @Column(name = "MODIFY_DATE", length = 20)
    private LocalDateTime modifyDate;

}

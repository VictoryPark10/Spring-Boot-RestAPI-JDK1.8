package com.vicsoft.basicapi8.entity.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TBL_ITEM")
public class ItemEntity {

    @Id
    private long seq;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 50)
    private String description;

}


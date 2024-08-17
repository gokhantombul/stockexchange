package com.ing.stockexchange.v1.dto.user;

import com.ing.stockexchange.v1.dto.base.BaseDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class RoleDTO extends BaseDto {

    private String name;

    public RoleDTO(String name) {
        this.name = name;
    }

    public RoleDTO() {

    }

}

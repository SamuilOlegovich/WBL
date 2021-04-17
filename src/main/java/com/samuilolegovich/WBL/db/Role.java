package com.samuilolegovich.WBL.db;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Table;



@Table(name = "player_role")
public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}

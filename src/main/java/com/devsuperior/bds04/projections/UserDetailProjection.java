package com.devsuperior.bds04.projections;

public interface UserDetailProjection {

    String getUsername();
    String getPassword();
    Long getRoleId();
    String getAuthority();
}

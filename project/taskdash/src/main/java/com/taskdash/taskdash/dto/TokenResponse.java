package com.taskdash.taskdash.dto;

import java.util.Date;

public class TokenResponse {
    
    private String accessToken;
    private Iterable<String> authorities;
    private Date issuedAt;
    private Date expiresAt;
    
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public Iterable<String> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Iterable<String> authorities) {
        this.authorities = authorities;
    }
    public Date getIssuedAt() {
        return issuedAt;
    }
    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }
    public Date getExpiresAt() {
        return expiresAt;
    }
    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }


    
}

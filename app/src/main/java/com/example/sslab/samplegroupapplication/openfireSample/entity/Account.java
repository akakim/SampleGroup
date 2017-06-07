package com.example.sslab.samplegroupapplication.openfireSample.entity;

/**
 * Created by SSLAB on 2017-05-29.
 */

public class Account {
    private AuthenticationToken token;
    private String host;
    private String port;


    public Account() {
        token = null;
        host = "";
        port = "";
    }

    public Account(AuthenticationToken token, String host, String port) {
        this.token = token;
        this.host = host;
        this.port = port;
    }

    public AuthenticationToken getAuthenticationToken() {
        return token;
    }

    public void setAuthenticationToken(AuthenticationToken token) {
        this.token = token;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}

package com.dkorniichuk.entity;

import org.springframework.context.annotation.Bean;

public class DataSourceProperty {
    private String host;
    private String port;
    private String schema;
    private String user;
    private String password;

    public DataSourceProperty(String host, String port, String schema, String user, String password) {
        this.host = host;
        this.port = port;
        this.schema = schema;
        this.user = user;
        this.password = password;
    }

    public DataSourceProperty() {
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "DataSourceProperty{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", schema='" + schema + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

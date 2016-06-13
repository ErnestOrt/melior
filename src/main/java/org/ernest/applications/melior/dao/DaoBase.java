package org.ernest.applications.melior.dao;


import org.lightcouch.CouchDbProperties;

public class DaoBase {
    protected CouchDbProperties buildCouchDbProperties(String dbHost, String dbName) {
        CouchDbProperties properties = new CouchDbProperties();
        properties.setDbName(dbName);
        properties.setHost(dbHost);
        properties.setPort(5984);
        properties.setCreateDbIfNotExist(true);
        properties.setProtocol("http");
        return properties;
    }
}

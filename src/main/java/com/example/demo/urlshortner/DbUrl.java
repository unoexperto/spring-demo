package com.example.demo.urlshortner;

import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.jdbi.v3.core.mapper.reflect.JdbiConstructor;

// POJO
public class DbUrl {
    long id;
    String url;

    @JdbiConstructor
    public DbUrl(@ColumnName("ID") int id, @ColumnName("URL") String url) {
        this.id = id;
        this.url = url;
    }
}

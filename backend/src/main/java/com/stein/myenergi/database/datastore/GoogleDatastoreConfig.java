package com.stein.myenergi.database.datastore;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("nosql")
public class GoogleDatastoreConfig {

    @Bean
    public Datastore datasource(DataSourceProperties dataSourceProperties) {
        return DatastoreOptions.getDefaultInstance().getService();
    }

}

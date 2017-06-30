package it.polito.ai.chatmodule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * Created by france193 on 24/06/2017.
 */
@Configuration
@PropertySource("classpath:mongo.properties")
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public MongoDbFactory mongoDbFactory(Environment env) {
        MongoClient mongoClient = new MongoClient(
                env.getRequiredProperty("spring.data.mongodb.host"),
                Integer.parseInt(env.getRequiredProperty("spring.data.mongodb.port")));

        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(
                mongoClient,
                env.getRequiredProperty("spring.data.mongodb.database"));

        return mongoDbFactory;
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
        return mongoTemplate;
    }

    @Override
    protected String getDatabaseName() {
        return env.getRequiredProperty("spring.data.mongodb.database");
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(
                env.getProperty("spring.data.mongodb.host"),
                Integer.parseInt(env.getProperty("spring.data.mongodb.port")));
    }
}

package org.aub.db.util;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.net.UnknownHostException;

@ApplicationScoped
public class MongoUtil {

    private final static Logger logger = LoggerFactory.getLogger(MongoUtil.class);

    private static final int PORT = 27017;
    private static final String HOST = "localhost";
    private static final String DB_NAME = "ee6";
    private DB db = null;

    @PostConstruct
    private void init() {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            db = mongoClient.getDB(DB_NAME);
        } catch (UnknownHostException | MongoException e) {
            logger.error(e.getMessage());
        }
    }

    public DB getDb() {
        return db;
    }
}

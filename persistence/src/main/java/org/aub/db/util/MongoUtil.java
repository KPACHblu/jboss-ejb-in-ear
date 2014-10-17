package org.aub.db.util;

import com.mongodb.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.net.UnknownHostException;
import java.util.Arrays;

@ApplicationScoped
public class MongoUtil {

    private final static Logger logger = LoggerFactory.getLogger(MongoUtil.class);
    //TODO put properties into file
    private static final int PORT = 27017;
    private static final String HOST = "localhost";
    private static final String DB_NAME = "ee6";
    private DB db = null;

    @PostConstruct
    private void init() {
        try {
            db = (isLocalEnvironment())? getLocalClient() : getOpenShiftClient();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public DB getDb() {
        return db;
    }

    private boolean isLocalEnvironment() {
        boolean isLocalEnvironment = true;
        if (System.getenv("OPENSHIFT_MONGODB_DB_HOST") != null) {
            isLocalEnvironment = false;
        }
        return  isLocalEnvironment;
    }

    private DB getLocalClient() throws UnknownHostException {
        return new MongoClient(HOST, PORT).getDB(DB_NAME);
    }

    private DB getOpenShiftClient() throws UnknownHostException {
        String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
        String portRaw = System.getenv("OPENSHIFT_MONGODB_DB_PORT");
        int port = Integer.decode(portRaw);
        String db = System.getenv("OPENSHIFT_APP_NAME");
        String user = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");

        MongoCredential credential = MongoCredential.createMongoCRCredential(user, db, password.toCharArray());
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));

        return mongoClient.getDB(db);
    }


}

package org.aub.db.util;

import com.mongodb.DB;
import org.aub.mongodb.odm.util.Persistence;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class MongoUtil {

    private DB db = null;

    @PostConstruct
    private void init() {
        try {
            db = (isLocalEnvironment()) ? Persistence.getDB() : Persistence.getDB(getOpenShiftConfig());
        } catch (Exception e) {
            throw new RuntimeException(e);
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
        return isLocalEnvironment;
    }

    private Map<String, String> getOpenShiftConfig() throws UnknownHostException {
        Map<String, String> props = new HashMap<>();
        String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
        String portRaw = System.getenv("OPENSHIFT_MONGODB_DB_PORT");
        Integer port = Integer.decode(portRaw);
        String dbName = System.getenv("OPENSHIFT_APP_NAME");
        String user = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");

        props.put(Persistence.HOST, host);
        props.put(Persistence.PORT, port.toString());
        props.put(Persistence.DB_NAME, dbName);
        props.put(Persistence.USER_NAME, user);
        props.put(Persistence.USER_PASSWORD, password);

        return props;
    }

}

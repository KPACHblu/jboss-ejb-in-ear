package org.aub.db.odm.util;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Persistence {
    public static final String HOST = "mongodb.odm.connection.host";
    public static final String PORT = "mongodb.odm.connection.port";
    public static final String DB_NAME = "mongodb.odm.connection.db.name";
    public static final String USER_NAME = "mongodb.odm.connection.user.name";
    public static final String USER_PASSWORD = "mongodb.odm.connection.user.password";

    private static final String XML_PROPERTY_FILE = "META-INF/mongodb.odm.xml";
    private static final String XML_PROPERTY_ELEMENT = "property";
    private static final String XML_PROPERTY_ATTRIBUTE = "name";


    /**
     * Use properties from META-INF/mongodb.odm.xml for getting DB
     * @return mongoDB DB
     */
    public static DB getDB() {
        Enumeration<URL> configXml;
        try {
            configXml = Thread.currentThread().getContextClassLoader().getResources(XML_PROPERTY_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getDB(getProperties(configXml.nextElement()));
    }

    public static DB getDB(Map<String, String> properties) {
        MongoClient mongoClient;
        String host = properties.get(HOST);
        Integer port = Integer.parseInt(properties.get(PORT));
        String dbName = properties.get(DB_NAME);
        String userName = properties.get(USER_NAME);
        String userPassword = properties.get(USER_PASSWORD);

        MongoCredential credential = null;
        if (userName != null && userPassword != null) {
            credential = MongoCredential.createMongoCRCredential(userName, dbName, userPassword.toCharArray());
        }
        mongoClient = getNewMongoClient(host, port, credential);
        return mongoClient.getDB(dbName);
    }

    private static MongoClient getNewMongoClient(String host, int port, MongoCredential credential) {
        MongoClient mongoClient;
        try {
            if (credential != null) {
                mongoClient = new MongoClient(new ServerAddress(host, port), Arrays.asList(credential));
            } else {
                mongoClient = new MongoClient(host, port);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return mongoClient;
    }

    private static Map<String, String> getProperties(URL configURL) {
        Map<String, String> properties = new HashMap<>();
        try {
            InputStream is = null;
            if (configURL != null) {
                URLConnection conn = configURL.openConnection();
                conn.setUseCaches(false); //avoid JAR locking on Windows and Tomcat
                is = conn.getInputStream();
            }
            if (is == null) {
                throw new RuntimeException("Failed to obtain InputStream from url: " + configURL);
            }
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            InputSource source = new InputSource(is);
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(source);
            NodeList entries = doc.getElementsByTagName(XML_PROPERTY_ELEMENT);
            int num = entries.getLength();
            for (int i = 0; i < num; i++) {
                Element node = (Element) entries.item(i);
                String propertyName = node.getAttributes().getNamedItem(XML_PROPERTY_ATTRIBUTE).getNodeValue();
                String propertyValue = node.getChildNodes().item(0).getNodeValue();
                properties.put(propertyName, propertyValue);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}

package edu.umg;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConexionMongo {
    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            String uri = System.getenv("MONGODB_URI");
            if (uri == null || uri.isEmpty()) {
                throw new RuntimeException("Error: La variable de entorno MONGODB_URI no esta configurada.");
            }
            mongoClient = MongoClients.create(uri);
        }
        return mongoClient.getDatabase("tienda");
    }
}
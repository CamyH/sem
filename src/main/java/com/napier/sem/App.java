package com.napier.sem;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class App
{
    public static void main(String[] args)
    {
        // Connect to MongoDB on local system - port 27000
        MongoClient mongoClient = new MongoClient("localhost", 27000);
        // Get a database - will create when we use it
        MongoDatabase database = mongoClient.getDatabase("myDB");
        // Get a collection from the database
        MongoCollection<Document> collection = database.getCollection("test");
        // Create a document to store
        Document doc = new Document("Name", "Cameron Hunt")
                .append("Class", "Software Engineering Methods")
                .append("Year", "2021")
                .append("result", new Document("CW", 100).append("Exam", 100));
        // Add document to collection
        collection.insertOne(doc);

        // Check document in collection
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
    }
}
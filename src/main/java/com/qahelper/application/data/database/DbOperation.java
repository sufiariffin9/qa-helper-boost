package com.qahelper.application.data.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.qahelper.application.Constant;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DbOperation {

    /*public static void main (String[] args){
        DbOperation test = new DbOperation();
        test.updateRegLimit("922194f8502a4173bc5442954d0e3fd8");
    }*/

    public String getCustomerId(String msisdn){
        String collection="customerprofile";
        String result = "No data";
        DbConnection dbcon = new DbConnection();
        MongoClient mongoClientSession = dbcon.getServiceDB(Constant.ServiceName.Customer.getResponse(), Constant.Env.Test.getResponse());

        try {
            MongoDatabase database = mongoClientSession.getDatabase(Constant.ServiceName.Customer.getResponse());

            MongoCollection<Document> customerprofile = database.getCollection(collection);
            Bson filters = Filters.and(Filters.eq("mobiles.msisdn", msisdn));

            List<Document> listDocuments = customerprofile.find(filters).into(new ArrayList<Document>());

            Iterator<Document> it;
            it = listDocuments.iterator();
            while(it.hasNext()) {
                Document doc = it.next();
                result = doc.get("_id").toString();
                System.out.println("Customer id = "+doc.get("_id"));
            }

            System.out.println("Close mongoClientSession");
            mongoClientSession.close();
        }catch(Exception e) {
            System.out.println("Close mongoClientSession");
            mongoClientSession.close();
            throw e;
        }

        return result;
    }

    public String getCustomerOtp(String msisdn){
        String collection="customerprofile";
        String result = "No data";
        DbConnection dbcon = new DbConnection();
        MongoClient mongoClientSession = dbcon.getServiceDB(Constant.ServiceName.Customer.getResponse(), Constant.Env.Test.getResponse());

        try {
            MongoDatabase database = mongoClientSession.getDatabase(Constant.ServiceName.Customer.getResponse());

            MongoCollection<Document> customerprofile = database.getCollection(collection);
            Bson filters = Filters.and(Filters.eq("mobiles.msisdn", msisdn));

            List<Document> listDocuments = customerprofile.find(filters).into(new ArrayList<Document>());

            Iterator<Document> it;
            it = listDocuments.iterator();
            while(it.hasNext()) {
                Document doc = it.next();

                ArrayList mobilesAL = doc.get("mobiles", ArrayList.class);
                Document doc2 = (Document) mobilesAL.get(0);
                Document doc3 = (Document) doc2.get("status");
                System.out.println("result = "+doc3.getString("pin"));
                result = doc3.getString("pin");
            }

            System.out.println("Close mongoClientSession");
            mongoClientSession.close();
        }catch(Exception e) {
            System.out.println("Close mongoClientSession");
            mongoClientSession.close();
            throw e;
        }

        return result;
    }

    public String getAndroidUid(String msisdn){
        String collection="customerprofile";
        String result = "No data";
        DbConnection dbcon = new DbConnection();
        MongoClient mongoClientSession = dbcon.getServiceDB(Constant.ServiceName.Customer.getResponse(), Constant.Env.Test.getResponse());

        try {
            MongoDatabase database = mongoClientSession.getDatabase(Constant.ServiceName.Customer.getResponse());

            MongoCollection<Document> customerprofile = database.getCollection(collection);
            Bson filters = Filters.and(Filters.eq("mobiles.msisdn", msisdn));

            List<Document> listDocuments = customerprofile.find(filters).into(new ArrayList<Document>());

            Iterator<Document> it;
            it = listDocuments.iterator();
            while(it.hasNext()) {
                Document doc = it.next();

                Document doc2 = (Document) doc.get("device");
                System.out.println("result = "+doc2.getString("uid"));
                result = doc2.getString("uid");
            }

            System.out.println("Close mongoClientSession");
            mongoClientSession.close();
        }catch(Exception e) {
            System.out.println("Close mongoClientSession");
            mongoClientSession.close();
            throw e;
        }

        return result;
    }

    public String getIosUid(String customerId){
        String collection="signininformation";
        String result = "No data";
        DbConnection dbcon = new DbConnection();
        MongoClient mongoClientSession = dbcon.getServiceDB(Constant.ServiceName.Customer.getResponse(), Constant.Env.Test.getResponse());

        try {
            MongoDatabase database = mongoClientSession.getDatabase(Constant.ServiceName.Customer.getResponse());

            MongoCollection<Document> signininformation = database.getCollection(collection);
            Bson filters = Filters.and(Filters.eq("customerId", customerId));

            List<Document> listDocuments = signininformation.find(filters).into(new ArrayList<Document>());

            if(listDocuments.size() > 0) {
                Document doc = listDocuments.get(listDocuments.size() - 1);
                Document doc2 = (Document) doc.get("device");
                result = doc2.getString("uid");
                System.out.println("result = "+result);
            }

            System.out.println("Close mongoClientSession");
            mongoClientSession.close();
        }catch(Exception e) {
            System.out.println("Close mongoClientSession");
            mongoClientSession.close();
            throw e;
        }

        return result;
    }

    public String updateRegLimit(String uid){
        String collection="deviceinformation";
        String result = "Failed to Increase Reg Limit";
        DbConnection dbcon = new DbConnection();
        MongoClient mongoClientSession = dbcon.getServiceDB(Constant.ServiceName.Customer.getResponse(), Constant.Env.Test.getResponse());

        try {
            MongoDatabase database = mongoClientSession.getDatabase(Constant.ServiceName.Customer.getResponse());

            MongoCollection<Document> deviceinformation = database.getCollection(collection);
            Bson filters = Filters.and(Filters.eq("device.uid", uid));

            List<Document> listDocuments = deviceinformation.find(filters).into(new ArrayList<Document>());

            if(listDocuments.size() > 0) {

                BasicDBObject newDocument = new BasicDBObject();
                newDocument.put("registrationLimit", 99);

                BasicDBObject updateObject = new BasicDBObject();
                updateObject.put("$set", newDocument);

                deviceinformation.updateMany(filters, updateObject);

                result = "Successfully Increase Reg Limit";
            }

            System.out.println("Close mongoClientSession");
            mongoClientSession.close();
        }catch(Exception e) {
            System.out.println("Close mongoClientSession");
            mongoClientSession.close();
            throw e;
        }

        return result;
    }
}

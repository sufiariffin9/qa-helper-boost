package com.qahelper.application.data.database;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.qahelper.application.Constant;
import com.qahelper.application.data.generator.DataGenerator;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.qahelper.application.data.generator.DataGenerator.randomNumGenerator;

public class DbOperation {

    /*public static void main (String[] args) throws IOException {
        DbOperation test = new DbOperation();
        test.upgradePremium("61de3fe419534d0de84d62e5", "+60172430004");
    }*/

    public String getCustomerId(String msisdn){
        String result = "No data";
        DbConnection dbcon = new DbConnection();
        MongoClient mongoClientSession = dbcon.getServiceDB(Constant.ServiceName.Customer.getResponse(), Constant.Env.Test.getResponse());

        try {
            MongoDatabase database = mongoClientSession.getDatabase(Constant.ServiceName.Customer.getResponse());

            MongoCollection<Document> customerprofile = database.getCollection(Constant.Collection.Customerprofile.getResponse());
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
        String result = "No data";
        DbConnection dbcon = new DbConnection();
        MongoClient mongoClientSession = dbcon.getServiceDB(Constant.ServiceName.Customer.getResponse(), Constant.Env.Test.getResponse());

        try {
            MongoDatabase database = mongoClientSession.getDatabase(Constant.ServiceName.Customer.getResponse());

            MongoCollection<Document> customerprofile = database.getCollection(Constant.Collection.Customerprofile.getResponse());
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
        String result = "No data";
        DbConnection dbcon = new DbConnection();
        MongoClient mongoClientSession = dbcon.getServiceDB(Constant.ServiceName.Customer.getResponse(), Constant.Env.Test.getResponse());

        try {
            MongoDatabase database = mongoClientSession.getDatabase(Constant.ServiceName.Customer.getResponse());

            MongoCollection<Document> customerprofile = database.getCollection(Constant.Collection.Customerprofile.getResponse());
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
        String result = "No data";
        DbConnection dbcon = new DbConnection();
        MongoClient mongoClientSession = dbcon.getServiceDB(Constant.ServiceName.Customer.getResponse(), Constant.Env.Test.getResponse());

        try {
            MongoDatabase database = mongoClientSession.getDatabase(Constant.ServiceName.Customer.getResponse());

            MongoCollection<Document> signinInformation = database.getCollection(Constant.Collection.Signininformation.getResponse());
            Bson filters = Filters.and(Filters.eq("customerId", customerId));

            List<Document> listDocuments = signinInformation.find(filters).into(new ArrayList<Document>());

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
        String result = "Failed to Increase Reg Limit";
        DbConnection dbcon = new DbConnection();
        MongoClient mongoClientSession = dbcon.getServiceDB(Constant.ServiceName.Customer.getResponse(), Constant.Env.Test.getResponse());

        try {
            MongoDatabase database = mongoClientSession.getDatabase(Constant.ServiceName.Customer.getResponse());

            MongoCollection<Document> deviceinformation = database.getCollection(Constant.Collection.Deviceinformation.getResponse());
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

    public String upgradePremium(String customerId, String msisdn) throws IOException {
        String result = "Failed to Upgrade Premium";
        DbConnection dbcon = new DbConnection();
        MongoClient mongoClientSession = dbcon.getServiceDB(Constant.ServiceName.Screening.getResponse(), Constant.Env.Test.getResponse());

        try {
            MongoDatabase database = mongoClientSession.getDatabase(Constant.ServiceName.Screening.getResponse());

            MongoCollection<Document> screeningCustomer = database.getCollection(Constant.Collection.ScreeningCustomer.getResponse());
            Bson filters = Filters.and(Filters.eq("customerId", customerId));

            List<Document> listDocuments = screeningCustomer.find(filters).into(new ArrayList<Document>());

            if(listDocuments.size() > 0) {
                String rawSC = DataGenerator.convertJsonToString("screeningCustomerData.json");

                Document mainDoc = Document.parse(rawSC);
                mainDoc.replace("customerId", customerId);
                mainDoc.replace("referenceNo", "SC00000"+String.valueOf(randomNumGenerator(5)));

                System.out.println("Test :"+mainDoc.get("userInfo").toString());

                Document userInfoDoc = (Document) mainDoc.get("userInfo");
                userInfoDoc.replace("currentIdNumber", "70010105"+String.valueOf(randomNumGenerator(4)));
                userInfoDoc.replace("idNumber", "70010105"+String.valueOf(randomNumGenerator(4)));
                userInfoDoc.replace("extractedIdNumber", "70010105"+String.valueOf(randomNumGenerator(4)));

                Document contactInfoDoc = (Document) mainDoc.get("contactInfo");
                contactInfoDoc.replace("msisdn", msisdn);

                mainDoc.put("userInfo", userInfoDoc);
                mainDoc.put("contactInfo", contactInfoDoc);

                System.out.println("------- Start JSON result --------");
                System.out.println(mainDoc.toJson().toString());
                System.out.println("------- End JSON result --------");

                screeningCustomer.insertOne(mainDoc);
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

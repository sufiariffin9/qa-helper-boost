package com.qahelper.application.data.database;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.qahelper.application.Constant;
import org.apache.commons.lang3.StringUtils;
import org.bson.Document;

public class DbConnection {
    /*Sufi mongodb credential
    String user = "SufiAriffin";
    String pwd = "3UQhh4dLCSZtMX28LYEb";*/

    /*general mongodb credential*/
    String user = "boostorium";
    String pwd = "boostorium";

    //original - for reference only
    private void connectToMongodb(){
        System.out.println("start connect db");
        //String user = "SufiAriffin";
        //String pwd = "3UQhh4dLCSZtMX28LYEb";
        String user = "boostorium";
        String pwd = "boostorium";
        String servicename="customer";
        String collection="customerprofile";

        MongoClientURI mongoClientCustomerURI = null;

        //dev enviroment
        //mongoClientCustomerURI = new MongoClientURI("mongodb://"+user+":"+pwd+"@boostorium-shard-00-00-g9cti.mongodb.net:27017,boostorium-shard-00-01-g9cti.mongodb.net:27017,boostorium-shard-00-02-g9cti.mongodb.net:27017/"+servicename+"?replicaSet=boostorium-shard-0&authSource=admin&ssl=true&sslInvalidHostNameAllowed=true&w=majority&journal=true&retryWrites=true");

        //test enviroment
        mongoClientCustomerURI = new MongoClientURI("mongodb://"+user+":"+pwd+"@boostoriumtest-shard-00-00-hxhud.mongodb.net:27017,boostoriumtest-shard-00-01-hxhud.mongodb.net:27017,boostoriumtest-shard-00-02-hxhud.mongodb.net:27017/"+servicename+"?ssl=true&replicaSet=boostoriumTest-shard-0&authSource=admin&retryWrites=true&w=majority");

        MongoClient mongoClientCustomer = new MongoClient(mongoClientCustomerURI);

        MongoDatabase loyaltyDB = mongoClientCustomer.getDatabase(servicename);

        try {
            MongoCollection<Document> customerprofile = loyaltyDB.getCollection(collection);
            System.out.println("Total of customer profile ="+customerprofile.countDocuments());
        }catch(Exception e) {
            mongoClientCustomer.close();
            throw e;
        }

        mongoClientCustomer.close();

        System.out.println("end connect db");
    }

    public MongoClient getServiceDB(String servicename, String env){
        MongoClientURI mongoClientCustomerURI = null;
        MongoDatabase database = null;
        MongoClient mongoClientSession = null;

        try{
            if(StringUtils.equals(env, Constant.Env.Dev.getResponse())){
                //dev enviroment
                mongoClientCustomerURI = new MongoClientURI("mongodb://"+user+":"+pwd+"@boostorium-shard-00-00-g9cti.mongodb.net:27017,boostorium-shard-00-01-g9cti.mongodb.net:27017,boostorium-shard-00-02-g9cti.mongodb.net:27017/"+servicename+"?replicaSet=boostorium-shard-0&authSource=admin&ssl=true&sslInvalidHostNameAllowed=true&w=majority&journal=true&retryWrites=true");
            }else if(StringUtils.equals(env, Constant.Env.Test.getResponse())){
                //test enviroment
                mongoClientCustomerURI = new MongoClientURI("mongodb://"+user+":"+pwd+"@boostoriumtest-shard-00-00-hxhud.mongodb.net:27017,boostoriumtest-shard-00-01-hxhud.mongodb.net:27017,boostoriumtest-shard-00-02-hxhud.mongodb.net:27017/"+servicename+"?ssl=true&replicaSet=boostoriumTest-shard-0&authSource=admin&retryWrites=true&w=majority");
            }else{
                System.out.println("Environment is not found. Please add in constant.java");
            }

            mongoClientSession = new MongoClient(mongoClientCustomerURI);
            //database = mongoClientSession.getDatabase(servicename);
        }catch (Exception e){
            throw e;
        }

        return mongoClientSession;
    }
}

package com.qahelper.application;

public class Constant {

    public enum Env{
        Dev("DEV"), Test("TEST"), Stage("STAGE");
        private String value;

        public String getResponse() {
            return value;
        }

        Env(String value){
            this.value = value;
        }
    }

    public enum ServiceName{
        Customer("customer"), Screening("screening");
        private String value;

        public String getResponse() {
            return value;
        }

        ServiceName(String value){
            this.value = value;
        }
    }

    public enum Collection{
        Customerprofile("customerprofile"), Signininformation("signininformation"), Deviceinformation("deviceinformation"), ScreeningCustomer("screening.customer");
        private String value;

        public String getResponse() {
            return value;
        }

        Collection(String value){
            this.value = value;
        }
    }

    public static final String MY_MOBILE_PREFIXES="+6";
    public static final String COMMON_JSONDATA_PATH ="src/main/java/com/qahelper/application/data/jsonData/";
}

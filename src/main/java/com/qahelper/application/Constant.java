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
        Customer("customer");
        private String value;

        public String getResponse() {
            return value;
        }

        ServiceName(String value){
            this.value = value;
        }
    }

    public static final String MY_MOBILE_PREFIXES="+6";
}

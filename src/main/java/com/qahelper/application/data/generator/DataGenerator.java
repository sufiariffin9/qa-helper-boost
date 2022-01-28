package com.qahelper.application.data.generator;

import com.qahelper.application.Constant;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;


public class DataGenerator {

    /*@Bean
    public CommandLineRunner loadData(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (userRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            logger.info("... generating 2 User entities...");
            User user = new User();
            user.setName("John Normal");
            user.setUsername("user");
            user.setHashedPassword(passwordEncoder.encode("user"));
            user.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1535713875002-d1d0cf377fde?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            user.setRoles(Collections.singleton(Role.USER));
            userRepository.save(user);
            User admin = new User();
            admin.setName("John Normal");
            admin.setUsername("admin");
            admin.setHashedPassword(passwordEncoder.encode("admin"));
            admin.setProfilePictureUrl(
                    "https://images.unsplash.com/photo-1607746882042-944635dfe10e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=128&h=128&q=80");
            admin.setRoles(Collections.singleton(Role.ADMIN));
            userRepository.save(admin);

            logger.info("Generated demo data");
        };
    }*/

    /*public static void main (String[] args) throws IOException {
        //DataGenerator test = new DataGenerator();
        //String result = test.convertJsonToString( "screeningCustomerData.json");
        System.out.println("Answer : "+ String.valueOf(randomNumGenerator(4)));
    }*/

    public static String convertJsonToString(String filename) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(Constant.COMMON_JSONDATA_PATH + filename)));
        }catch (Exception e){
            throw e;
        }
    }

    public static int randomNumGenerator(int num) throws IOException{
        System.out.println("Answer 1 : "+ Integer.valueOf(StringUtils.rightPad("1", num, "0")));
        System.out.println("Answer 9 : "+ Integer.valueOf(StringUtils.rightPad("9", num, "9")));
        int randomNum = Integer.valueOf(Integer.valueOf(StringUtils.rightPad("1", num, "0"))) + new Random().nextInt(Integer.valueOf(StringUtils.rightPad("9", num, "9")));
        return Integer.valueOf(randomNum);
    }

}
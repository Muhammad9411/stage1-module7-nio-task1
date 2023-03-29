package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;


public class FileReader {// Compliant

    public Profile getDataFromFile(File file) {
        Map<String, Object> map = new HashMap<>();

        try (RandomAccessFile raf = new RandomAccessFile(file, "r")){

            String str;

            while ((str = raf.readLine()) != null) {
                String[] keyValuePair = str.split(":");
                if (keyValuePair.length > 1) {
                    String key = keyValuePair[0];
                    String value = keyValuePair[1];
                    map.put(key, value.replace(" ", ""));
                }
            }
        } catch (IOException ex) {
            System.err.println("File not found or error is: " + ex);
        }

        Profile profile = new Profile();
        profile.setName(map.get("Name").toString());
        profile.setAge(Integer.parseInt(map.get("Age").toString()));
        profile.setEmail(map.get("Email").toString());
        profile.setPhone(Long.parseLong(map.get("Phone").toString()));

        return profile;
    }
}

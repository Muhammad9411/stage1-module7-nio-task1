package com.epam.mjc.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileReader {// Compliant
    public Profile getDataFromFile(File file) {
        Map<String, Object> map = new HashMap<>();
        List<String> allLines = null;
        try {
            allLines = Files.readAllLines(file.toPath());
        } catch (IOException ex) {
            System.err.println("File not found or error is: " + ex);
        }

        for (String line : allLines) {
            System.out.println(line);
            String[] keyValuePair = line.split(":");
            if (keyValuePair.length > 1) {
                String key = keyValuePair[0];
                String value = keyValuePair[1];
                map.put(key, value.replace(" ", ""));
            }
        }

        Profile profile = new Profile();
        profile.setName(map.get("Name").toString());
        profile.setAge(Integer.parseInt(map.get("Age").toString()));
        profile.setEmail(map.get("Email").toString());
        profile.setPhone(Long.parseLong(map.get("Phone").toString()));

        return profile;
    }
}

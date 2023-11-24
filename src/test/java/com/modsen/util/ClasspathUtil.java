package com.modsen.util;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClasspathUtil {

    @SneakyThrows
    public String readFile(String pathToFile) {
        InputStream inputStream = ClasspathUtil.class.getResourceAsStream(pathToFile);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        StringBuilder file = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            file.append(line);
            file.append(" ");
        }
        return file.toString();
    }

}

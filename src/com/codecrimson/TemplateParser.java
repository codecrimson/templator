package com.codecrimson;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateParser {

    String fileData;

    public TemplateParser() {
        fileData = new String();
    }

    public void loadFile(String fileName){
        File file = new File(fileName);
        try {
            fileData = FileUtils.readFileToString(file, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String generate(Map<String,String> tokensMap, String templateData){
        String regex = "\\$\\{([^}]++)\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(templateData);
        String result = templateData;
        while (matcher.find()) {
            String token = matcher.group();     // Ex: ${name}
            String tokenKey = matcher.group(1); // Ex: name
            String replacementValue = null;

            if (tokensMap.containsKey(tokenKey))
                replacementValue = tokensMap.get(tokenKey);
            else {
                System.out.println("tokenKey: " + tokenKey);
                throw new RuntimeException("Template contained an unsupported token: " + tokenKey+".");
            }

            result = result.replaceFirst(Pattern.quote(token), replacementValue);
        }
        return result;
    }

}
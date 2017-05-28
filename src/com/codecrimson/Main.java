package com.codecrimson;

public class Main {
    public static String FOLDER = "data/";
    public static String TEMPLATE_FILE = "template.tf";
    public static String DATA_FILE = "data.xml";

    public static void main(String[] args) {
        TemplateParser templateParser = new TemplateParser();
        InputParser inputParser = new InputParser();

        inputParser.parse(FOLDER + DATA_FILE);

        templateParser.loadFile(FOLDER + TEMPLATE_FILE);

        String result = templateParser.generate(inputParser.tokenMap, templateParser.fileData);

        System.out.println("Result: " + result);

    }
}

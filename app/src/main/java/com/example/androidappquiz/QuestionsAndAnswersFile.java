package com.example.androidappquiz;

public class QuestionsAndAnswersFile {

    //first create an array of string question and list all questions

    public static String[] questions ={
            "Who is the first European explorer to reach the southernmost tip of Africa?",
            "What is apartheid?",
            "Who first performed heart transplantation?",
            "Which country is surrounded on all sides by South Africa?",
            "How many provinces are in South Africa?",
            "Who was the first black president in South Africa",
            "Which country won the 2023 Rugby World Cup?",
            "Table Mountain is located in which province?",
            "Which currency does South Africa use?"
    };

    //create a 2d array of choices and list the choices for each question
    public static String[][] choices ={
            {"Bartholomeu Dias", "Vasco da Gama", "Abel Tasman", "Francis Drake"},
            {"Peaceful co-existance", "Racial segregation", "Military rule", "Theocracy"},
            {"Philip Blaiberg", "Dorothy Fisher", "Christian Barnard", "Denise Darvall"},
            {"Angola", "Swaziland", "Lesotho", "Namibia"},
            {"7", "9", "10", "6"},
            {"Jacob Zuma", "Nelson Mandela", "Cyril Ramaphosa", "Thabo Mbeki"},
            {"New Zealand", "Wales", "South Africa", "France"},
            {"Eastern Cape", "Gauteng", "KwaZulu-Natal", "Western Cape"},
            {"ZAR", "USD", "EUR", "GBP"}
    };

    //create an array of correct choices or answers and place all correct answers
    public static String correctChoices[]={
            "Bartholomeu Dias",
            "Racial segregation",
            "Christian Barnard",
            "Lesotho",
            "9",
            "Nelson Mandela",
            "South Africa",
            "Western Cape",
            "ZAR"
    };
}

//Montana Williams
//8/11/22
//CS 145
//Word Search Assignment
//
//Coded in IntelliJ. GitHub is operational.
//Crash-proof as far as I know?

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.InputMismatchException;

public class WordSearchMain {

    public static void main(String[] args) {
        introduction();
        System.out.println();
        menu();
        System.out.println("Have a nice day!");
    }//end main

    //Supplies an introduction to the word search generator
    public static void introduction(){
        System.out.println("Hello and welcome to the word search generator!");
        System.out.println("You can generate word searches - all you need is\nto supply me with some words!");
    }//end introduction

    //Provides a menu of options to the user
    public static void menu(){
        Scanner input=new Scanner(System.in);
        boolean quit=false;
        String answer;
        WordSearch currentSearch=new WordSearch();

        do {
            System.out.println("""
                    What would you like to do?
                    g. Generate new word search
                    p. Print out your word search
                    s. Show the solution to your word search
                    q. Quit""");
            answer=input.next();

            if(answer.equals("q")||answer.equals("Q")){
                quit=true;
            }else{
                currentSearch=choose(answer,input,currentSearch);
                System.out.println();
            }//end if
        }while(!quit);
    }//end menu

    //Based on user's choice, directs to correct method
    //Requires string of user answer, a scanner, and the WordSearch object
    public static WordSearch choose(String answer,Scanner input,WordSearch currentSearch){
        switch(answer) {
            case "g":
            case "G":
                currentSearch=generate(input);
                break;
            case "p":
            case "P":
                display(currentSearch);
                break;
            case "s":
            case "S":
                displaySolution(currentSearch);
                break;
            default:
                System.out.println("I'm sorry, please choose a valid option from the menu.");
        }//End switch case
        return currentSearch;
    }//end choose

    //Asks user for input to create a word search.
    //Requires input of a scanner, and gives back the WordSearch object
    public static WordSearch generate(Scanner input){
        Queue<String> allWords=new LinkedList<>();
        System.out.println("Please type words you want included in your word search.\nWhen you have no more words, type NoMore.");
        String word=input.next();
        do{
            System.out.println(word+ " has been added.");
            allWords.add(word);
            word=input.next();
        }while(!word.toLowerCase().equals("nomore"));
        System.out.println("How difficult do you want this word search to be?\n1.Easy\n2.Medium\n3.Hard\n4.Very hard");
        int size=difficultyToSize(input);
        System.out.println("Your word search has been generated.\nPlease note generating a new word search will delete this one.");
        return new WordSearch(allWords,size);
    }//end generate

    //Takes the user given difficulty and returns the size of the word search
    //Requires input of a scanner
    public static int difficultyToSize(Scanner input){
        int difficulty=-2;
        do {
            try {
                difficulty = input.nextInt();
                if(difficulty>5||difficulty<0){
                    System.out.println("Please select one of the given difficulty levels.");
                }//end if
            } catch (InputMismatchException a) {
                    System.out.println("That's not a number, silly. Try again.");
                    input.next();
            }//end try/catch
        }while(difficulty>5||difficulty<0);

        if(difficulty==1){
            return 30;
        }else if(difficulty==2){
            return 45;
        }else if(difficulty==3){
            return 60;
        }else if(difficulty==4){
            return 90;
        }//end if else
        return 1000;
    }//end difficultyToSize

    //Displays the word search
    public static void display(WordSearch currentSearch){
        if(currentSearch.isNull()){
            System.out.println("You must generate a new word search before you can view it.");
        }else{
            currentSearch.displayWordSearch();
        }//end if else
    }//end display

    //Displays the word search solution
    public static void displaySolution(WordSearch currentSearch){
        if(currentSearch.isNull()){
            System.out.println("You must generate a new word search before you can view solution.");
        }else{
            currentSearch.displaySolution();
        }//end if else
    }//end displaySolution

}//end main class

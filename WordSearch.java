//Montana Williams
//8/11/22
//CS 145
//Word Search Assignment
//
//Coded in IntelliJ. GitHub is operational.
//Only thing wrong: If word is placed onto grid and runs into other word, the start of the first word
//will show up in the solution until it runs into the other word.

import java.util.Queue;
import java.util.Random;

public class WordSearch {
    private int size;

    private Queue<String> allWords;
    private char[][] wordSearchGrid;
    private char[][] wordSearchSolution;
    private boolean isNull;

    //When you need a placeholder WordSearch
    public WordSearch(){
        this.isNull=true;
    }

    //Constructor for word search object
    public WordSearch(Queue<String> allWords, int size){
        this.allWords=allWords;
        this.size=size; //All word searches generated are squares.
        this.wordSearchGrid=new char[size][size];
        this.wordSearchSolution=new char[size][size];
        this.isNull=false;
        buildSearchSolution();
        buildWordSearch();
    }//end WordSearch constructor

    //Sets up grid full of '-' for easy viewing of the solution
    public void buildSearchSolution(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                wordSearchSolution[i][j]='-';
            }//end j
        }//end i
    }//end buildWordSearchSolution

    //Places words in the solution and word search grid
    public void buildWordSearch() {
        Random rando = new Random();
        int xCord;
        int yCord;
        boolean vert;
        String word;

        while (!allWords.isEmpty()) {
            word = allWords.remove();
            xCord = rando.nextInt(0, size - word.length());
            yCord = rando.nextInt(0, size - word.length());
            vert = rando.nextBoolean();
            if (vert) {//if word is vertical
                placeWordVert(word, xCord, yCord);
            } else {
                placeWordHorz(word, xCord, yCord);
            }//end if
        }//end while
        fillGrid(rando);
    }//end buildWordSearch

    //Fills empty space in the word search grid with random characters
    //Requires random object
    public void fillGrid(Random rando){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(wordSearchGrid[i][j]==0) {
                    wordSearchGrid[i][j]=(char) (rando.nextInt(26) + 'a');
                }//end if
            }//end j
        }//end i
    }//end fillGrid

    //Places words vertically into the grids
    //Requires a string word to place, and starting x and y coordinates
    public void placeWordVert(String word,int xCord,int yCord){
        char letter;

        //Check if there is room for the word
        for(int i=0; i<word.length(); i++) {
            if (wordSearchGrid[xCord][yCord + i] != 0) {
                allWords.add(word);
                return;
            }
        }

        //Once sure there is room, place the word
        for(int i=0; i<word.length(); i++){
            letter = Character.toLowerCase(word.charAt(i));
            wordSearchGrid[xCord][yCord + i] = letter;
            wordSearchSolution[xCord][yCord+i]=letter;
        }//end for

    }//end placeWordVert

    //Places words horizontally into the grids
    //Requires a string word to place, and starting x and y coordinates
    public void placeWordHorz(String word,int xCord,int yCord){
        char letter;

        //Check if there is room for the word
        for(int i=0; i<word.length(); i++) {
            if (wordSearchGrid[xCord+i][yCord] != 0) {
                allWords.add(word);
                return;
            }
        }

        //Once you know there's room, place that word!
        for(int i=0; i<word.length(); i++){
            letter = Character.toLowerCase(word.charAt(i));
            wordSearchGrid[xCord+i][yCord] = letter;
            wordSearchSolution[xCord+i][yCord] = letter;
        }//end for
    }//end placeWordVert

    //Displays the word search
    public void displayWordSearch(){
        for (char[] row : wordSearchGrid) {
            for (char column : row) {
                System.out.print(column + " ");
            }//end for
            System.out.println();
        }//end for
    }//end displayWordSearch

    //Displays the word search solution
    public void displaySolution(){
        for (char[] row : wordSearchSolution) {
            for (char column : row) {
                System.out.print(column + " ");
            }//end for
            System.out.println();
        }//end for
    }//end displaySolution

    public boolean isNull(){
        return isNull;
    }//end isNull

}//end WordSearch

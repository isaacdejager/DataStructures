/*
Program written for CSII
Assign 7
Program written by Isaac DeJager
Last Modified on 3/28/18
MacOS Sierra
jGrasp

This program takes in 3 files and calculates the number of sentences, words, and characters in each file.
Then this data is all outputted to a seperate file. Should there be an incorrect number of files sent
to the program, an exception is thrown that lets the user know and then the program closes.

*/
//Importing utilities for Scanner input and file input and output
import java.util.*;
import java.io.*;

public class Assign7 {

   //Throwing the IOException just in case the complier cannot find the file
   public static void main(String[] files) throws IOException {
  
      //Indicating the number of files expected
      int num_of_files = 3;
         
      //Creating arrays to hold the number of senteces, words, and characters for each file
      int[] sentence_count = new int[num_of_files];
      int[] word_count = new int[num_of_files];
      int[] character_count = new int[num_of_files];
      String[] headers = new String[num_of_files];
         
      //Coding a try-catch block in case the correct number of files was not inputted 
      try {
         
         //This outerloop runs through each individual file and caculuates and outputs the data
         for (int i = 0; i < num_of_files; i++) {
            
            //If the correct number of files was not inputted, the exception is thrown
            if (files.length != num_of_files) {
               
               throw new ArrayIndexOutOfBoundsException();
               
            }
            
            else {
            
               //Grabbing the current file and using the delimiter to read in one sentence at a time
               File input = new File(files[i]);
               Scanner read_txt = new Scanner(input);
               read_txt.useDelimiter("\\.");
               
               //Outputting the header line of the file (the type of Bible it is) to the output file
               headers[i] = read_txt.nextLine();
            
               //Creating an empty array of a bunch of strings to hold the sentences in each file
               String[] sentences = new String[200];
         
               int j = 0;      
               //As long as there is another sentence, this loop will keep running
               while (read_txt.hasNext()) {
                  
                  //This loop takes all the sentences in the file and puts them into the sentence array
                  sentences[j] = read_txt.next();
                  
                  //This counts how many times the loop runs, and therefore how many sentences there are
                  j++;
            
               }
               
               //The number of sentences in the current file is equal to the number of times the previous loop ran
               sentence_count[i] = j;
               
               //Creating an arbitrary sentecne
               String sentence;
            
               //This loop caculuates the words and characters of each individual sentence
               for(int k = 0; k < j; k++) {
            
                  sentence = sentences[k];

                  //Adding one character to each sentence length to account for the period the delimeter removed
                  character_count[i] = character_count[i] + sentence.length() + 1;
         
                  //This loop runs through the sentences, splitting it at every space, and calculated the nubmer of words
                  for (String a : sentence.split(" ")) {
                  
                     word_count[i]++;
                  }
                  
                  //Subracting one word from the final count to account for the space after periods
                  word_count[i]--;
               }
            }
         }   
        
         //Creating the output file
         File theOutput = new File("compareInfo.txt");
         PrintWriter outputFile = new PrintWriter(theOutput);
         
         //Outputting the header line to the output file
         outputFile.println("Statistics for the first chapter of James:");
         outputFile.println();
         
         //Outputting all the results to the output file
         for(int x = 0; x < num_of_files; x++) {
            
            //Outputting the headers
            outputFile.println(headers[x]);
            
            if (x < files.length - 1) {   
               outputFile.println("Characters: " + character_count[x]);
               outputFile.println("Words: " + (word_count[x] + 1));
               outputFile.println("Sentences: " + sentence_count[x]);
               outputFile.println();
            }
            
            else {
               outputFile.println("Characters: " + character_count[x]);
               outputFile.println("Words: " + (word_count[x] + 1));
               outputFile.print("Sentences: " + sentence_count[x]);
            }          
         }
                           
         //Statement to let the user know the program ran and the data has been outputted to a file
         System.out.println("The program is complete, and the data is in the file " +
            "named \"compareInfo.txt\".");
         
         //Closing the output file
         outputFile.close();
      }
      
      //Catching the exception that is thrown if the correct number of files have not been inputted
      catch(ArrayIndexOutOfBoundsException ex) {
         System.out.println("The correct number of files was not inputted.");
      }      
   }      
}

      
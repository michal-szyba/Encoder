package org.example.encoder;

import org.example.ioservice.FileService;

import java.io.File;
import java.text.Normalizer;
import java.util.Scanner;

public class EncoderOne {
    private static FileService fileService = new FileService();
    public static String encodeAlgOne() {
//        The pattern based on which this algorythm codes input String can be found in the 'keyboard.txt' file.
        System.out.println("Type input source: ");
        System.out.println("1: File");
        System.out.println("2: Type from keyboard");
        System.out.println("Type 'exit' to quit.");
        System.out.println("Please answer using numbers 1 or 2");
        Scanner inputScanner = new Scanner(System.in);
        String decision = inputScanner.next().trim().toLowerCase();
        String input = "";
        switch(decision){
            case "1":
                //I do not create a FileService object, because I only need 1 method from this class, and it would be less efficient.
                File file = fileService.findFile();
                input = fileService.loadContent(file);
                break;
            case "2":
                System.out.println("Type a message you wish to encode");
                Scanner scan = new Scanner(System.in);
                input = scan.nextLine();
                break;
            case "exit":
                System.out.println("Goodbye!");
                return null;
        }
        input = normalize(input);
        char[] inputArr = input.toCharArray();
        StringBuilder coded = new StringBuilder();
        for (char c : inputArr) {
            if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){ //encoding letters
                for (int i = 1; i <= Constants.ALPHABET.length(); i++) {
                    int position;
                    int set;
                    if (Character.toUpperCase(c) == Constants.ALPHABET.charAt(i - 1)) {
                        set = (i - 1) / 3 + 1;
                        position = (i - 1) % 3 + 1;
                        for (int j = 0; j < position; j++) {
                            coded.append(set);
                        }
                        coded.append(" ");
                        break;
                    }
                }
            } else if (c >= '0' && c <= '9'){
                int value = Integer.parseInt(String.valueOf(c)) + 1; //encoding digits
                for(int i = 0; i < value; i++){
                    coded.append("0");
                }
                coded.append(" ");
            } else if (c == 32){
                coded.append("--- "); //encoding spaces
            } else {
                coded.append(c).append(" "); //encoding anything else (dots, comas etc.)
            }
        }
        System.out.println(coded);
        fileService.saveToFile(coded.toString());
        return coded.toString();
    }


    private static String normalize(String input){
        String normalizer = Normalizer.normalize(input, Normalizer.Form.NFD);
        String output = normalizer.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        output = output.replace("Ł", "L").replace("ł", "l");
        return output;
    }

}

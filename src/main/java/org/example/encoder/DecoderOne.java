package org.example.encoder;

import org.example.ioservice.FileService;

import java.io.File;
import java.util.Scanner;

public class DecoderOne {
    private static final FileService fileService = new FileService();
    public static String decodeAlgOne(){
        //The pattern based on which this algorythm decodes input String can be found in the 'keyboard.txt.' file.
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
                System.out.println("Type a message you wish to decode");
                Scanner scan = new Scanner(System.in);
                input = scan.nextLine();
                break;
            case "exit":
                System.out.println("Goodbye!");
                return null;
        }
        StringBuilder decoded = new StringBuilder();
        String[] inputArr = input.split(" ");
        for (String s : inputArr){
            try {
                if(s.startsWith("0")){
                    int value = s.length() - 1;
                    decoded.append(value);
                } else if(s.contains("---")){
                    String toAdd = s.replace("---", " ");
                    decoded.append(toAdd);
                } else {
                    int cycle = Integer.parseInt(s.substring(0, 1));
                    int position = 3 * (cycle - 1) + s.length();
                    decoded.append(Constants.ALPHABET.charAt(position - 1));
                }
            } catch (NumberFormatException e){
                decoded.append(s);
            }
        }
        System.out.println(decoded);
        fileService.saveToFile(decoded.toString());
        return decoded.toString();
    }
}

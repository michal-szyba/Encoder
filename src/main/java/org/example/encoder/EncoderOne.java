package org.example.encoder;

import org.example.ioservice.FileService;

import java.io.File;
import java.util.Scanner;

public class EncoderOne {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static FileService fileService = new FileService();
    public static String encodeAlgOne() {
        //The pattern based on which this algorythm codes input String can be found in the 'keyboard.txt.' file.
        //Right now this algorithm is not capable of encoding inputs that contain numbers.
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
        char[] inputArr = input.toCharArray();
        StringBuilder coded = new StringBuilder();
        for (char c : inputArr) {
            if(c == ' '){
                coded.append("--- ");
            }else if (!Character.isAlphabetic(c)){
                coded.append(c).append(' ');
            }
            int set;
            int position = 0;
            for (int i = 1; i <= ALPHABET.length(); i++) {
//                if (position == 3) {
//                    position = 0;
//                }
//                position++;
                if (Character.toUpperCase(c) == ALPHABET.charAt(i - 1)) {
                    set = (i - 1) / 3 + 1;
                    position = (i - 1) % 3 + 1;
                    for (int j = 0; j < position; j++) {
                        coded.append(set);
                    }
                    coded.append(" ");
                    break;
                }
            }
        }
        System.out.println(coded);
        fileService.saveToFile(coded.toString());
        return coded.toString();
    }

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
                System.out.println("Type a message you wish to encode");
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
                int cycle = Integer.parseInt(s.substring(0, 1));
                int position = 3 * (cycle - 1) + s.length();
                decoded.append(ALPHABET.charAt(position - 1));
            } catch (NumberFormatException e){
                if(s.contains("-")) {
                    decoded.append(" ");
                } else{
                    decoded.append(s);
                }
            } catch (StringIndexOutOfBoundsException e){
                System.out.println(decoded);
            }
        }
        fileService.saveToFile(decoded.toString());
        return decoded.toString();
    }


}

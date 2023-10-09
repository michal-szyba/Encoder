package org.example.ioservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileService {
    public static File findFile(){
        String currentWorkingDirectory = System.getProperty("user.dir");
        while(true) {
            System.out.println("Current Working Directory: " + currentWorkingDirectory);
            System.out.println("Enter file name or type 'exit' to quit.");
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if(input.equalsIgnoreCase("exit")){
                System.out.println("Goodbye!");
                return null;
            }
            try {
                File file = new File(currentWorkingDirectory + File.separator + input + ".txt");
                if (file.exists()) {
                    return file;
                } else {
                    throw new FileNotFoundException("File not found exception: " + input);
                }
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
                System.out.println("Please try again.");
                return null;
            }
        }
    }
    public static String loadContent(File file){
        StringBuilder output = new StringBuilder();
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                output.append(scan.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return output.toString();
    }
}

package org.example.inspector;

import org.example.encoder.Constants;
import org.example.ioservice.FileService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Inspector {
    private static final FileService fileService = new FileService();
    public static void search() {
        Set<String> results = new HashSet<>();
        int occurrences = 0;
        File file = fileService.findFile();
        System.out.println("What phrase are you looking for?");
        Scanner scan = new Scanner(System.in);
        String targetPhrase = scan.nextLine().toLowerCase();
        String line = "";
        String word = "";
        try(Scanner fileScanner = new Scanner(file)) {
            String regex = "(?i)(" + Pattern.quote(targetPhrase) + ")";
            while(fileScanner.hasNextLine()){
                line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                while(lineScanner.hasNext()){
                    word = lineScanner.next().toLowerCase();
                    if (word.contains(targetPhrase)){
                        results.add(line.replaceAll(regex, Constants.ANSI_RED + targetPhrase + Constants.RESET_COLOR ));
                        occurrences++;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
        if(occurrences > 0){
            for(String result : results){
                System.out.println(result);
            }
        } else {
            System.out.println("No results.");
        }

    }
}

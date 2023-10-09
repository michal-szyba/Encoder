package org.example.inspector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Inspector {
    public static void search() {
        String currentWorkingDirectory = System.getProperty("user.dir");
        System.out.println("Current Working Directory: " + currentWorkingDirectory);
        System.out.println("Enter file name");
        Scanner scan3 = new Scanner(System.in);
        String fileName = scan3.nextLine();
        System.out.println("Enter the phrase that you are looking for");
        String searched1 = scan3.nextLine().toLowerCase();
        Set<String> results = new HashSet<>();
        //This method looks for files starting from current working directory.
        // To help you with entering the correct path to your file, it also prints current working directory when you run it.
        File file = new File(currentWorkingDirectory + File.separator + fileName + ".txt");
        int counter = 0;
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                int index = line.toLowerCase().indexOf(searched1);
                while (index != -1) {
                    counter++;
                    results.add(line);
                    index = line.toLowerCase().indexOf(searched1, index + 1);
                }
            }
            for (String result : results) {
                System.out.println(result);
            }
            if (counter > 0) {
                System.out.println("Occurrences: " + counter);
            } else {
                System.out.println("None");
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }
}

package org.example;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EncoderOne encOne = new EncoderOne();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a phrase to encode. This algorythm is not able to code numbers.  \nPlease use letters only.");
        String input = scan.nextLine();
        System.out.println("Coded: " + encOne.encodeAlgOne(input));
        System.out.println("Decoded: " + encOne.decodeAlgOne(encOne.encodeAlgOne(input)));
    }


}
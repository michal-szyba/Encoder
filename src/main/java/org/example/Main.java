package org.example;


import org.example.encoder.EncoderOne;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        EncoderOne encoderOne = new EncoderOne();
        System.out.println("Encode or decode? (please answer with numbers: 1 or 2) \n 1. Encode \n 2. Decode ");
        Scanner scan = new Scanner(System.in);
        String decision = scan.next();
        switch (decision){
            case "1":
                encoderOne.encodeAlgOne();
                break;
            case "2":
                encoderOne.decodeAlgOne();
        }

    }




}
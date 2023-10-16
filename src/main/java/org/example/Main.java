package org.example;


import org.example.encoder.Constants;
import org.example.encoder.DecoderOne;
import org.example.encoder.EncoderOne;
import org.example.inspector.Inspector;
import org.example.ioservice.FileService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Encoding, decoding or searching a file? (please answer with numbers) \n1. Encode \n2. Decode \n3. Search ");
        Scanner scan = new Scanner(System.in);
        String decision = scan.next();
        switch (decision){
            case "1":
                EncoderOne encoderOne = new EncoderOne();
                encoderOne.encodeAlgOne();
                break;
            case "2":
                DecoderOne decoderOne = new DecoderOne();
                decoderOne.decodeAlgOne();
                break;
            case "3":
                Inspector inspector = new Inspector();
                inspector.search();
                break;

        }
    }




}
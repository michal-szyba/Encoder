package org.example;

public class EncoderOne {
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String encodeAlgOne(String input) {
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
            for (int i = 1; i <= alphabet.length(); i++) {
                if (position == 3) {
                    position = 0;
                }
                position++;
                if (Character.toUpperCase(c) == alphabet.charAt(i - 1)) {
                    if (i % 3 == 0) {
                        set = i / 3;
                    } else {
                        set = i / 3 + 1;
                    }
                    for (int j = 0; j < position; j++) {
                        coded.append(set);
                    }
                    coded.append(" ");
                    break;
                }
            }
        }
        return coded.toString();
    }

    public static String decodeAlgOne(String input){
        StringBuilder decoded = new StringBuilder();
        String[] inputArr = input.split(" ");
        for (String s : inputArr){
            try {
                int cycle = Integer.parseInt(s.substring(0, 1));
                int position = cycle * 3 - 3 + s.length();
                decoded.append(alphabet.charAt(position - 1));
            } catch (NumberFormatException e){
                if(s.contains("-")) {
                    decoded.append(" ");
                } else{
                    decoded.append(s);
                }
            }
        }
        return decoded.toString();
    }
}

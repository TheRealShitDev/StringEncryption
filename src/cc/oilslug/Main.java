package cc.oilslug;

import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        final String input = "I got bored ok shush";
        final String encryption = encrypt(input);

        System.out.println("O=" + encryption + " D=" + decrypt(encryption));

    }

    public static String encrypt(String message) {
        final String key = UUID.randomUUID().toString().replaceAll("-", "");
        final String[] inputChars = message.split("");
        final String[] uuidChars = key.split("");

        String output = "";
        String decryptKey = "";

        for (int i = inputChars.length-1; i >= 0; i--) {
            String randomChar = uuidChars[(int) (Math.random() * (uuidChars.length - 1) + 1)];
            decryptKey += randomChar;
            output += randomChar + inputChars[i];
        }
        return output.replaceAll(" ", "#") + "=" + decryptKey;
    }

    public static String decrypt(String message) {
        String[] split = message.split("=");
        String encryption = split[0].replaceAll("#", " ");
        String key = split[1];

        final String[] inputChars = encryption.split("");
        final String[] uuidChars = key.split("");

        String output = "";

        for (int i = inputChars.length-1; i >= 0; i--) {
           output += inputChars[i];
        }

        String keyL = "";

        String fixed = "";
        String[] outputChars = output.split("");
        for (int i = 0; i <= outputChars.length-1; i++) {
            if ((i+1) % 2 != 0 || outputChars[i].equals(" ")) {
                fixed += outputChars[i];
            } else {
                keyL += outputChars[i];
            }
        }

        String flippedL = "";
        String[] c = keyL.split("");

        for (int i = c.length-1; i >= 0; i--) {
            flippedL += c[i];
        }

        if (flippedL.equals(key)) {
            return fixed;
        } else {
            return null;
        }

    }

}

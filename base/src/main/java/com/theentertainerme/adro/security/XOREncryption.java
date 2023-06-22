package com.theentertainerme.adro.security;


import com.theentertainerme.adro.security.CLibController;

public class XOREncryption {

    public static String decryptFromCKey(String input)
    {
        char[] jKey = {'y', 'C', 'b','f'};
        String sb = String.valueOf(jKey) + CLibController.INSTANCE.getX();
        char[] key = sb.toCharArray();
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < input.length(); i++) {
            output.append((char) (input.charAt(i) ^ key[i % key.length]));
        }

        return output.toString();
    }

}

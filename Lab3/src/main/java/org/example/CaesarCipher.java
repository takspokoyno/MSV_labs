package org.example;

public class CaesarCipher {
    private final Substitutor substitutor;

    private SymbolSubstitution sub = new SymbolSubstitution();

    public CaesarCipher(Substitutor _substitutor) {
        this.substitutor = _substitutor;
    }

    public String encrypt(String input, int step) {
        String ciphertext = "";
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            char encryptedChar = substitutor.substitute(currentChar, step);
            ciphertext += encryptedChar;
        }
        return ciphertext;
    }

    public String decrypt(String input, int step) {
        String plaintext = "";
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            char decryptedChar = substitutor.substitute(currentChar, -step);
            plaintext += decryptedChar;
        }
        return plaintext;
    }
}

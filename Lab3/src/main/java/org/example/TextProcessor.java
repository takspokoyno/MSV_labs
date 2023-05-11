package org.example;

public class TextProcessor {
    private CaesarCipher caesarCipher;

    public TextProcessor(CaesarCipher _cipher) {
        this.caesarCipher = _cipher;
    }

    public String processText(String text, int key) {
        if (text.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be empty");
        }
        String encrypted = caesarCipher.encrypt(text, key);
        String decrypted = caesarCipher.decrypt(encrypted, key);
        return decrypted;
    }
}
package org.example;

public class SymbolSubstitution implements Substitutor {
    @Override
    public char substitute(char c, int step)
    {
        if (!Character.isLetter(c)) {
            return c;
        }

        int alphabetLength = 26;

        char normalizedChar = Character.toLowerCase(c);
        char shiftedChar = (char) (normalizedChar + step);

        if (shiftedChar > 'z') {
            shiftedChar = (char) (shiftedChar - alphabetLength);
        }

        if (shiftedChar < 'a') {
            shiftedChar = (char) (shiftedChar + alphabetLength);
        }

        return Character.isUpperCase(c) ? Character.toUpperCase(shiftedChar) : shiftedChar;
    }
}

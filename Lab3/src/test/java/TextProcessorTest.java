import static org.mockito.Mockito.*;

import org.example.CaesarCipher;
import org.example.Substitutor;
import org.example.SymbolSubstitution;
import org.example.TextProcessor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class TextProcessorTest {

    @Mock
    private Substitutor substitutor;

    @InjectMocks
    private CaesarCipher caesarCipher;

    @Mock
    private CaesarCipher caesarCipherMock;

    private TextProcessor processor;

    private String inputText;
    private String encryptedText;
    private String decryptedText;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        processor = new TextProcessor(caesarCipherMock);
    }

    @Test
    public void testProcessText() {
        inputText = "aboba";
        System.out.println(inputText);
        encryptedText = caesarCipher.encrypt(inputText, 0);
        System.out.println(encryptedText);
        decryptedText = caesarCipher.decrypt(encryptedText, 0);
        System.out.println(decryptedText);
        verify(substitutor, times(2)).substitute('a', 2);
        verify(substitutor, times(1)).substitute('o', -2);
    }

    /*@Test
    public void testProcessTextWithEmptyInput() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> processor.processText("", 3));
    }

    @Test
    public void testSymbolSubstitution() {
        char originalChar = 'a';
        char shiftedChar = 'd';
        int key = 3;

        substitution = new SymbolSubstitution(key, SymbolSubstitution.Direction.RIGHT);
        char result = substitution.substitute(originalChar);
        Assertions.assertEquals(shiftedChar, result);

        substitution = new SymbolSubstitution(key, SymbolSubstitution.Direction.LEFT);
        result = substitution.substitute(shiftedChar);
        Assertions.assertEquals(originalChar, result);
    }

    @Test
    public void testSymbolSubstitutionWithNonAlphabeticInput() {
        char nonAlphabeticChar = ',';
        substitution = new SymbolSubstitution(3, SymbolSubstitution.Direction.RIGHT);

        char result = substitution.substitute(nonAlphabeticChar);
        Assertions.assertEquals(nonAlphabeticChar, result);
    }

    @Test
    public void testSymbolSubstitutionWithInvalidKey() {
        char originalChar = 'a';
        int invalidKey = -1;

        Assertions.assertThrows(IllegalArgumentException.class, () -> substitution = new SymbolSubstitution(invalidKey, SymbolSubstitution.Direction.RIGHT));
    }

    @Test
    public void testSymbolSubstitutionWithException() {
        char originalChar = 'a';
        int key = 3;

        substitution = new SymbolSubstitution(key, SymbolSubstitution.Direction.RIGHT);
        doThrow(new RuntimeException()).when(substitution).substitute(originalChar);

        Assertions.assertThrows(RuntimeException.class, () -> substitution.performSubstitution("abc"));
    }*/
}

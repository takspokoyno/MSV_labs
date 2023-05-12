import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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

public class TextProcessorTest {

    @Mock
    private Substitutor subMock;

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
        when(subMock.substitute('a', 2)).thenReturn('c');
        when(subMock.substitute('c', -2)).thenReturn('a');
        when(subMock.substitute('b', 2)).thenReturn('d');
        when(subMock.substitute('d', -2)).thenReturn('b');
        when(subMock.substitute('c', 2)).thenReturn('e');
        when(subMock.substitute('e', -2)).thenReturn('c');
        when(subMock.substitute('*', 2)).thenReturn('*');
    }

    @Test
    public void testProcessText() {
        inputText = "abc";
        System.out.println(inputText);
        encryptedText = caesarCipher.encrypt(inputText, 2);
        System.out.println(encryptedText);
        decryptedText = caesarCipher.decrypt(encryptedText, 2);
        System.out.println(decryptedText);
        verify(subMock, times(1)).substitute('a', 2);
        verify(subMock, times(1)).substitute('e', -2);
    }

    @Test
    public void testProcessTextWithEmptyInput() {
        assertThrows(IllegalArgumentException.class, () -> processor.processText("", 3));
    }

    @Test
    public void testSymbolSubstitution() {
        char originalChar = 'a';
        char shiftedChar = 'c';
        int key = 2;
        SymbolSubstitution sub = new SymbolSubstitution();

        Substitutor spySub = spy(sub);

        char real_result = sub.substitute(originalChar, key);
        char spy_result = spySub.substitute(originalChar, key);
        assertEquals(real_result, spy_result);

        real_result = sub.substitute(shiftedChar, -key);
        spy_result = spySub.substitute(shiftedChar, -key);
        assertEquals(real_result, spy_result);
    }

    @Test
    public void testSymbolSubstitutionWithNonAlphabeticInput() {
        char nonAlphabeticChar = '*';
        int key = 2;
        SymbolSubstitution sub = new SymbolSubstitution();

        char real_result = sub.substitute(nonAlphabeticChar, key);
        char mock_result = subMock.substitute(nonAlphabeticChar, key);
        assertEquals(real_result, mock_result);
    }

    @Test
    public void testSymbolSubstitutionWithException() {
        char originalChar = 'a';
        int key = 3;

        //substitution = new SymbolSubstitution(key, SymbolSubstitution.Direction.RIGHT);
        doThrow(new RuntimeException()).when(subMock).substitute(originalChar, key);

        assertThrows(RuntimeException.class, () -> subMock.substitute(originalChar, key));
    }
}

import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void checkEqualChars()
    {
        assertTrue(offByOne.equalChars('a','b'));
        assertTrue(offByOne.equalChars('&','%'));
        assertFalse(offByOne.equalChars('A','b'));
        assertFalse(offByOne.equalChars('a','a'));
    }
}

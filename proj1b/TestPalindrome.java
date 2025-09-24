import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome()
    {
        assertFalse(palindrome.isPalindrome("Ana"));
        assertTrue(palindrome.isPalindrome("AnnA"));
        assertFalse(palindrome.isPalindrome("anA"));
        assertTrue(palindrome.isPalindrome("aNa"));
        assertTrue(palindrome.isPalindrome("xionGGnoix"));
        assertFalse(palindrome.isPalindrome("xiongGnoix"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome(""));

        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake",cc));
        assertFalse(palindrome.isPalindrome("Flake",cc));
    }
}

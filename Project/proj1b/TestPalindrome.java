import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void TestisPalindrome(){
        boolean Test_a1 = palindrome.isPalindrome("");
        assertTrue(Test_a1);

        boolean Test_a2 = palindrome.isPalindrome("a");
        assertTrue(Test_a2);

        boolean Test_a3 = palindrome.isPalindrome("abcddcba");
        assertTrue(Test_a3);

        boolean Test_a4 = palindrome.isPalindrome("noon");
        assertTrue(Test_a4);

        boolean Test_b1 = palindrome.isPalindrome("translate");
        assertFalse(Test_b1);

        boolean Test_b2 = palindrome.isPalindrome("iloveprogramming");
        assertFalse(Test_b2);
    }
    @Test
    public void testIsPalindromeOffByOne() {
        OffByOne forfun = new OffByOne();
        assertTrue(palindrome.isPalindrome("", forfun));
        assertTrue(palindrome.isPalindrome("a", forfun));
        assertTrue(palindrome.isPalindrome("Z", forfun));
        assertTrue(palindrome.isPalindrome("anmb", forfun));
        assertFalse(palindrome.isPalindrome("awesome", forfun));
    }
}

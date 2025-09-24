
public class Palindrome {
    public Deque<Character> wordToDeque(String word)
    {
        Deque wordlist = new LinkedListDeque();
        for (int i = 0; i < word.length();i++)
        {
            wordlist.addLast(word.charAt(i));
        }
        return wordlist;
    }
    public boolean isPalindrome(String word)
    {
        Deque<Character> alpha = wordToDeque(word);
        return helper(alpha);
    }

    public boolean isPalindrome(String word, CharacterComparator cc)
    {
        Deque<Character> alpha = wordToDeque(word);
        while(!alpha.isEmpty())
        {
            if (alpha.size() <= 1)
            {
                return true;
            }
            else if (!cc.equalChars(alpha.removeFirst(), alpha.removeLast()))
            {
                return false;
            }
        }
        return true;
    }

    public boolean helper(Deque<Character> alpha)
    {
        if ((alpha.size() == 1) || (alpha.isEmpty()))
        {
            return true;
        }
        else if(alpha.removeLast() != alpha.removeFirst())
        {
            return false;
        }
        return helper(alpha);
    }
}

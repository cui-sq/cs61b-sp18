public class Palindrome {
    /** Given a String, returns a Deque with characters appear in the same order. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    /** Returns true if a given word is a palindrome, and false otherwise. */
    public boolean isPalindrome(String word) {
        Deque<Character> res = wordToDeque(word);
        return isPalindrome(res);
    }

    /** Returns true if a given Deque<Character> is a palindrome, and false otherwise. */
    private boolean isPalindrome(Deque<Character> x) {
        if (x.size() < 2) {
            return true;
        }
        if (x.removeLast() == x.removeFirst()) {
            return isPalindrome(x);
        }
        return false;
    }

    /** Returns true if the word is a palindrome
     * according to the character comparison test provided by the
     * Character Comparator!!*/
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> res = wordToDeque(word);
        return isPalindrome(res, cc);
    }

    private boolean isPalindrome(Deque<Character> x, CharacterComparator cc) {
        if (x.size() < 2) {
            return true;
        }
        if (cc.equalChars(x.removeFirst(), x.removeLast())) {
            return isPalindrome(x, cc);
        }
        return false;
    }



}

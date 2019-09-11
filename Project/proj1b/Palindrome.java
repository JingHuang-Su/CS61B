public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> collection = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i += 1) {
            char c = word.charAt(i);
            collection.addLast(c);
        }
        return collection;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }else{
            Deque<Character> compare = wordToDeque(word);
            while(compare.size() > 1){
                if(compare.removeFirst() != compare.removeLast()){
                    return false;
                }
            }
            return true;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }else{
            Deque<Character> compare = wordToDeque(word);
            while(compare.size() > 1) {
                if (compare.removeFirst() != compare.removeLast() && !cc.equalChars(compare.removeFirst(), compare.removeLast()) ) {
                    return false;
                }
            }
            return true;
        }
    }
}

public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y){
        int maydifferent = x - y;
        if (maydifferent == 1 || maydifferent == -1){
            return true;
        }
        return false;
    }
}

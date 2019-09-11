public class OffByN implements CharacterComparator {

    private int N;

    public OffByN(int N){
        this.N = N;
    }

    public boolean equalChars(char x, char y){
        int maydifferent = x - y;
        if (maydifferent == N || maydifferent == -N){
            return true;
        }
        return false;
    }
}

public class OffByN implements CharacterComparator{
    private int n;

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return Math.abs(diff) == n;
    }
    public OffByN(int N)
    {
        this.n = N;
    }
}
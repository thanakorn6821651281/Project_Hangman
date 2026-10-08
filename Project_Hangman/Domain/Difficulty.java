package Domain;
//===== ระดับความยากของเกม =====
public enum Difficulty {
    EASY(6), MEDIUM(6), HARD(6);
    private final int maxWrong;
    Difficulty(int maxWrong){ this.maxWrong=maxWrong; }
    public int getMaxWrong(){ return maxWrong; }
}

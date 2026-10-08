package Domain;
public class Player {
    private final String name;
    private int score;
    public Player(String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be empty");
        this.name = name.trim();
    }
    public String getName(){ return name; }
    public int getScore(){ return score; }
    public void addScore(int points){ if(points>0) score += points; }
    public void setScore(int score){ this.score = Math.max(0, score); }
}

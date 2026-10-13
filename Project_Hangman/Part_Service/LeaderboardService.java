package Part_Service;
import Domain.Difficulty;
import Domain.Player;
import Part_Data.ScoreRepository;
import java.util.List;

public class LeaderboardService {
    private final ScoreRepository repository;
    public LeaderboardService(ScoreRepository repository){ this.repository=repository; }

    public void save(Player p, Difficulty d){ repository.save(p, d); }
    public List<Player> getScores(){ return repository.load(); }
    public List<Player> getScores(Difficulty d){ return repository.load(d); }

    // ใช้เช็กชื่อซ้ำตอนล็อกอิน (ดูจาก players.csv ทุกระดับ)
    public boolean nameExists(String name){
        for (Player p : repository.load()) {
            if (p.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }
}
package Part_Service;
import Domain.Player;
import Part_Data.ScoreRepository;
import java.util.List;
public class LeaderboardService {
    private final ScoreRepository repository;// ทำหน้าที่เก็บข้อมูลผู้เล่นและคะแนน
    public LeaderboardService(ScoreRepository repository){ this.repository=repository; }// กำหนด repository ที่ใช้เก็บข้อมูลผู้เล่นและคะแนน
    public void save(Player p){ repository.save(p); }// บันทึกคะแนนของผู้เล่น
    public List<Player> getScores(){ return repository.load(); }// ขอรายชื่อผู้เล่นทั้งหมด
}

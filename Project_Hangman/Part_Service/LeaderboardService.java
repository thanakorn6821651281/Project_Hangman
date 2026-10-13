package Part_Service;
import Domain.Player;
import Part_Data.ScoreRepository;
import java.util.List;
import Domain.Difficulty;

public class LeaderboardService {
    private final ScoreRepository repository;// ทำหน้าที่เก็บข้อมูลผู้เล่นและคะแนน
    public LeaderboardService(ScoreRepository repository)
    { this.repository=repository; }// กำหนด repository ที่ใช้เก็บข้อมูลผู้เล่นและคะแนน
    public void save(Player p)
    { repository.save(p); }// บันทึกคะแนนของผู้เล่น
    public List<Player> getScores()
    { return repository.load(); }// ขอรายชื่อผู้เล่นทั้งหมด

    public List<Player> getTopScores(Difficulty difficulty) {
        // เช็คว่า repository ที่ถูกส่งเข้ามาเป็น CsvScoreRepository หรือไม่
        if (this.repository instanceof Part_Data.CsvScoreRepository) {
            Part_Data.CsvScoreRepository csvRepo = (Part_Data.CsvScoreRepository) this.repository;
            // เรียกใช้ฟังก์ชัน getTopScores จาก CsvScoreRepository ที่เราเพิ่งเขียนไปก่อนหน้านี้
            return csvRepo.getTopScores(difficulty);
        }
        // ถ้าไม่ใช่ หรือมีปัญหา ให้คืนค่า List ว่างๆ กลับไปเพื่อไม่ให้โปรแกรมพัง
        return new java.util.ArrayList<>();
    }
}

package Part_Data;
import Domain.Difficulty;
import Domain.Player;
import java.util.List;
public interface ScoreRepository {
    void save(Player player, Difficulty difficulty);  // บันทึกทั้ง players.csv และไฟล์ของระดับ
    List<Player> load();                              // ทุกคนรวมกัน (ใช้เช็กชื่อซ้ำ)
    List<Player> load(Difficulty difficulty);         // ตารางของระดับนั้น
}
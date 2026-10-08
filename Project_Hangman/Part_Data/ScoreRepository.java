package Part_Data;
import Domain.Player;
import java.util.List;
public interface ScoreRepository {
    void save(Player player); //บันทึกคะแนนของผู้เล่น
    List<Player> load(); //ขอรายชื่อผู้เล่นทั้งหมด
}

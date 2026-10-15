package Part_Data;
import Domain.Difficulty;
import Domain.Word;
import java.util.List;
    // ===== อินเทอร์เฟซ WordRepository ใช้จัดการคำศัพท์ โดยมีเมธอดค้นหาคำศัพท์ตามระดับความยาก =====
public interface WordRepository {
    List<Word> findByDifficulty(Difficulty difficulty); //ค้นหาคำศัพท์ตามระดับความยาก
}

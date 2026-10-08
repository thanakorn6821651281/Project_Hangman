package Part_Data;
import Domain.Difficulty;
import Domain.Word;
import java.util.List;
public interface WordRepository {
    List<Word> findByDifficulty(Difficulty difficulty); //ค้นหาคำศัพท์ตามระดับความยาก
}

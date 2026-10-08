package Part_Service;
import Domain.*;
import Part_Data.WordRepository;
import java.util.*;

public class WordService {
    private final WordRepository repository; // ทำหน้าที่เก็บข้อมูลคำศัพท์
    private final Random random=new Random(); // สุ่มคำศัพท์จาก repository
    public WordService(WordRepository repository){ this.repository=repository; }// กำหนด repository ที่ใช้เก็บข้อมูลคำศัพท์

    public Word randomWord(Difficulty difficulty){ return randomWord(difficulty,null); }

    //===== สุ่มคำศัพท์จาก repository ตามระดับความยาก และพยายามไม่ให้ซ้ำคำเดิม =====
    // Next = คำถัดไปในระดับความยากเดิม และพยายามไม่ให้ซ้ำคำเดิม
    public Word randomWord(Difficulty difficulty, String previous){
        List<Word> words=repository.findByDifficulty(difficulty);
        if(words.isEmpty()) throw new IllegalStateException("No words for "+difficulty);
        if(words.size()==1) return words.get(0);
        List<Word> pool=new ArrayList<>(words);
        pool.removeIf(w->w.getWord().equalsIgnoreCase(previous));
        return pool.get(random.nextInt(pool.size()));
    }
}

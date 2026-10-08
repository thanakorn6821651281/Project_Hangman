package Domain;
import java.util.LinkedHashSet;
import java.util.Set;

public class HangmanGame {
    private final Word word; // คำศัพท์ที่ใช้เล่นเกม
    private final Difficulty difficulty; // ระดับความยากของเกม
    private final Set<Character> guessed = new LinkedHashSet<>(); // ตัวอักษรที่ผู้เล่นเดาแล้ว
    private int wrong; // จำนวนครั้งที่ผู้เล่นเดาผิด

    //===== สร้างเกมใหม่ =====
    public HangmanGame(Word word, Difficulty difficulty){
        this.word=word; this.difficulty=difficulty;
    }

    //===== ผู้เล่นเดาตัวอักษร =====

    public boolean guess(char input){
        if(getStatus()!=GameStatus.PLAYING) return false;
        char c=Character.toUpperCase(input);
        if(c<'A'||c>'Z'||guessed.contains(c)) return false;
        guessed.add(c);
        if(word.getWord().indexOf(c)<0) wrong++;
        return true;
    }

    //===== แสดงคำศัพท์ที่ผู้เล่นเดาแล้ว =====

    public String displayWord(){
        StringBuilder s=new StringBuilder();
        for(char c:word.getWord().toCharArray()){
            s.append(guessed.contains(c)?c:'_').append(' ');
        }
        return s.toString().trim();
    }
    
    //===== ตรวจสอบสถานะของเกม =====

    public GameStatus getStatus(){
        boolean complete=true;
        for(char c:word.getWord().toCharArray())
            if(!guessed.contains(c)){ complete=false; break; }
        if(complete) return GameStatus.WIN;
        if(wrong>=difficulty.getMaxWrong()) return GameStatus.LOST;
        return GameStatus.PLAYING;
    }
    public boolean isGuessed(char c){ return guessed.contains(Character.toUpperCase(c)); }// ตรวจสอบว่าผู้เล่นเดาตัวอักษรนี้แล้วหรือไม่
    public int getWrong(){ return wrong; }// คืนจำนวนครั้งที่ผู้เล่นเดาผิด
    public int getMaxWrong(){ return difficulty.getMaxWrong(); }// คืนจำนวนครั้งที่ผู้เล่นเดาผิดได้สูงสุด
    public Set<Character> getGuessed(){ return guessed; }// คืนชุดตัวอักษร
    public String getAnswer(){ return word.getWord(); }// คืนคำตอบของเกม
    public String getHint(){ return word.getHint(); }// คืนคำใบ้ของเกม
    public String getCategory(){ return word.getCategory(); }// คืนหมวดหมู่ของเกม
    public Difficulty getDifficulty(){ return difficulty; }// คืนระดับความยากของเกม
}

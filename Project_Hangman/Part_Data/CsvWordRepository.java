package Part_Data;

import Domain.Difficulty;
import Domain.Word;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class CsvWordRepository implements WordRepository {
    private final Path dataDir; //ทำหน้าที่เก็บไฟล์ CSV
    public CsvWordRepository(Path dataDir) { this.dataDir = dataDir; } //กำหนดโฟลเดอร์ที่เก็บไฟล์ CSV

    // ===== รับระดับความยากแล้วคืนรายการคำศัพท์ทั้งหมดของระดับนั้นจากไฟล์CSV =====
    public List<Word> findByDifficulty(Difficulty difficulty) {
        Path file = dataDir.resolve(difficulty.name().toLowerCase() + ".csv");//กำหนดไฟล์ CSV ตามระดับความยาก
        List<Word> result = new ArrayList<Word>(); //สร้างรายการคำศัพท์
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line; // อ่านไฟล์ CSV ทีละบรรทัด
            boolean first = true; // ข้ามบรรทัดแรกที่เป็นหัวข้อ
            while ((line = br.readLine()) != null) { 
                if (first) { first = false; continue; }
                if (line.trim().isEmpty()) continue;
                String[] p = splitCsv(line);//
                if (p.length >= 3) result.add(new Word(p[0], p[1], p[2]));
            }
        } catch (IOException e) {
            throw new RuntimeException("Cannot read " + file, e);
        }
        return result;
    }
   // ===== ตัดบรรทัด CSV ออกเป็นคอลัมน์ =====
    private String[] splitCsv(String line) {
        List<String> out = new ArrayList<String>(); // กล่องเก็บคอลัมน์ที่ตัดเสร็จแล้ว
        StringBuilder cur = new StringBuilder(); // ที่พักข้อความของคอลัมน์ที่กำลังอ่านอยู่
        boolean quoted = false; // ตอนนี้อยู่ "ข้างใน" เครื่องหมายคำพูดหรือไม่
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '"') quoted = !quoted;
            else if (c == ',' && !quoted) {
                out.add(cur.toString().trim());
                cur.setLength(0);//
            } else cur.append(c);
        }
        out.add(cur.toString().trim());
        return out.toArray(new String[out.size()]);
    }
}

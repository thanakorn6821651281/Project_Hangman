package Part_Data;

import Domain.Difficulty;
import Domain.Player;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class CsvScoreRepository implements ScoreRepository {
    private final Path file; //ทำหน้าที่เก็บไฟล์ CSV
 
    // ===== กำหนดโฟลเดอร์ที่เก็บไฟล์ CSV =====

    public CsvScoreRepository(Path file) {
        this.file = file;
        ensureFile();
    }

    // ===== ตรวจสอบว่ามีไฟล์ CSV อยู่หรือไม่ ถ้าไม่มีให้สร้างขึ้นมา =====

    private void ensureFile() {
        try {
            if (file.getParent() != null) Files.createDirectories(file.getParent());
            if (!Files.exists(file)) {
                Files.write(file, Arrays.asList("name,score,difficulty"), StandardCharsets.UTF_8);// สร้างไฟล์ CSV และเขียนหัวข้อคอลัมน์
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ===== บันทึกคะแนนของผู้เล่น =====

    public void save(Player player) {
        List<Player> all = load();// โหลดรายชื่อผู้เล่นทั้งหมดจากไฟล์ CSV
        Player existing = null;// ตรวจสอบว่าผู้เล่นนี้มีอยู่แล้วหรือไม่
        // ค้นหาว่ามีชื่อนี้และเล่นในโหมดนี้อยู่แล้วหรือไม่
        for (Player p : all) {
            if (p.getName().equalsIgnoreCase(player.getName()) && p.getDifficulty() == player.getDifficulty()) {
                existing = p;
                break;
            }
        }
        if (existing == null) all.add(player);
        else if (player.getScore() > existing.getScore()) existing.setScore(player.getScore());

        Collections.sort(all, new Comparator<Player>() {
            public int compare(Player a, Player b) {
                return Integer.compare(b.getScore(), a.getScore());
            }
        });

        try (BufferedWriter w = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            w.write("name,score,difficulty");
            w.newLine();
            for (Player p : all) {
                String diffStr = (p.getDifficulty() != null) ? p.getDifficulty().name() : "EASY";
                w.write(csv(p.getName()) + "," + p.getScore() + "," + diffStr);
                w.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ===== ขอรายชื่อผู้เล่นทั้งหมด =====

    public List<Player> load() {
        List<Player> list = new ArrayList<Player>();
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                if (line.trim().isEmpty()) continue;

               // ... โค้ดด้านบน ...
                int comma = line.lastIndexOf(',');
                if (comma < 1) continue;
                
                // แก้ไขการแยกข้อมูล (Split) ใหม่ ให้ดึงได้ทั้ง 3 ช่อง
                String[] parts = line.split(",");
                if (parts.length < 2) continue; // ถ้ามีน้อยกว่า 2 ช่อง (ชื่อ, คะแนน) ให้ข้ามไป
                
                String name = parts[0].replace("\"", "").trim();
                int score = Integer.parseInt(parts[1].trim());
                
                Difficulty diff = Difficulty.EASY; 
                
                // ตรวจสอบว่าถ้าไฟล์ CSV มี 3 ช่อง (มีระดับความยาก) ค่อยดึงข้อมูลออกมา
                if (parts.length >= 3) {
                    try {
                        diff = Difficulty.valueOf(parts[2].trim().toUpperCase());
                    } catch (Exception ex) {
                        // ปล่อยผ่านถ้าอ่าน Difficulty ไม่ได้ (จะถือว่าเป็นโหมด EASY ตามบรรทัดบน)
                    }
                }
                
                Player p = new Player(name);
                p.setScore(score);
                p.setDifficulty(diff);
                list.add(p);
                // ... โค้ดด้านล่าง (catch และ return) ...
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Collections.sort(list, new Comparator<Player>() {// เรียงลำดับผู้เล่นตามคะแนนจากมากไปน้อย
            public int compare(Player a, Player b) {
                return Integer.compare(b.getScore(), a.getScore());
            }
        });
        return list;
    }
    
    // ===== แปลงข้อความเป็นรูปแบบ CSV =====

    private String csv(String s) {
        return s.indexOf(',') >= 0 ? "\"" + s.replace("\"", "\"\"") + "\"" : s;
    }
    // ===== ใช้เพิ่มสำหรับตอนดึง Leaderboard เฉพาะระดับความยาก =====
    public List<Player> getTopScores(Difficulty difficulty) {
        List<Player> allPlayers = load();
        List<Player> filteredPlayers = new ArrayList<>();
        
        for (Player p : allPlayers) {
            // ดึงเฉพาะคนที่เล่นในโหมดความยากที่กำหนด
            if (p.getDifficulty() == difficulty) {
                filteredPlayers.add(p);
            }
        }
        return filteredPlayers;
    }
}

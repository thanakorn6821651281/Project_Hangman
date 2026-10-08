package Part_Data;

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
                Files.write(file, Arrays.asList("name,score"), StandardCharsets.UTF_8);// สร้างไฟล์ CSV และเขียนหัวข้อคอลัมน์
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ===== บันทึกคะแนนของผู้เล่น =====

    public void save(Player player) {
        List<Player> all = load();// โหลดรายชื่อผู้เล่นทั้งหมดจากไฟล์ CSV
        Player existing = null;// ตรวจสอบว่าผู้เล่นนี้มีอยู่แล้วหรือไม่
        for (Player p : all) {
            if (p.getName().equalsIgnoreCase(player.getName())) {
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
            w.write("name,score");
            w.newLine();
            for (Player p : all) {
                w.write(csv(p.getName()) + "," + p.getScore());
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
                int comma = line.lastIndexOf(',');
                if (comma < 1) continue;
                String name = line.substring(0, comma).replace("\"", "");
                int score = Integer.parseInt(line.substring(comma + 1).trim());
                Player p = new Player(name);
                p.setScore(score);
                list.add(p);
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
}

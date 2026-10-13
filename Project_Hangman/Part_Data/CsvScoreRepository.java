package Part_Data;

import Domain.Difficulty;
import Domain.Player;
import Domain.ScoreEntry;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class CsvScoreRepository implements ScoreRepository {
    private final Path file; // players.csv : name,score,difficulty (รวมทุกระดับ)

    // ===== กำหนดไฟล์ที่เก็บข้อมูล =====

    public CsvScoreRepository(Path file) {
        this.file = file;
        ensureFile();
    }

    // ===== ตรวจสอบว่ามีไฟล์ CSV อยู่หรือไม่ ถ้าไม่มีให้สร้างขึ้นมา =====

    private void ensureFile() {
        try {
            if (file.getParent() != null) Files.createDirectories(file.getParent());
            if (!Files.exists(file)) {
                Files.write(file, Arrays.asList("name,score,difficulty"), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // ไฟล์คะแนนของแต่ละระดับ อยู่โฟลเดอร์เดียวกับ players.csv
    private Path fileFor(Difficulty d) {
        return file.resolveSibling("scores_" + d.name().toLowerCase() + ".csv");
    }

    // ===== บันทึกคะแนน: เขียนทั้งไฟล์รวมและไฟล์ของระดับนั้น =====

    public void save(Player player, Difficulty d) {
        saveCombined(player, d);
        savePerLevel(fileFor(d), player);
    }

    // ===== เมธอดอ่านที่เปิดให้ภายนอก =====

    // ทุกคนรวมกัน: 1 ชื่อ = 1 คน คะแนนเป็นค่าสูงสุดของทุกระดับ
    public List<Player> load() {
        Map<String, Player> byName = new LinkedHashMap<String, Player>();
        for (ScoreEntry e : readCombined()) {
            String key = e.getName().toLowerCase();
            Player p = byName.get(key);
            if (p == null) {
                p = new Player(e.getName());
                p.setScore(e.getScore());
                byName.put(key, p);
            } else if (e.getScore() > p.getScore()) {
                p.setScore(e.getScore());
            }
        }
        List<Player> list = new ArrayList<Player>(byName.values());
        list.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
        return list;
    }

    // ตารางของระดับที่เลือก
    public List<Player> load(Difficulty d) {
        return readPlayers(fileFor(d));
    }

    // ===== ไฟล์รวม players.csv (มีคอลัมน์ difficulty) =====

    private void saveCombined(Player player, Difficulty d) {
        List<ScoreEntry> all = readCombined();

        ScoreEntry existing = null;
        for (ScoreEntry e : all) {
            if (e.getName().equalsIgnoreCase(player.getName()) && e.getDifficulty() == d) {
                existing = e;
                break;
            }
        }
        ScoreEntry fresh = new ScoreEntry(player.getName(), player.getScore(), d);
        if (existing == null) {
            all.add(fresh);
        } else if (player.getScore() > existing.getScore()) {
            all.remove(existing);   // ScoreEntry แก้ค่าไม่ได้ จึงลบอันเก่าแล้วใส่อันใหม่
            all.add(fresh);
        }
        all.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));

        try (BufferedWriter w = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            w.write("name,score,difficulty");
            w.newLine();
            for (ScoreEntry e : all) {
                String diff = e.getDifficulty() == null ? "" : e.getDifficulty().name();
                w.write(csv(e.getName()) + "," + e.getScore() + "," + diff);
                w.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<ScoreEntry> readCombined() {
        List<ScoreEntry> list = new ArrayList<ScoreEntry>();
        if (!Files.exists(file)) return list;
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }   // ข้ามบรรทัดหัว
                if (line.trim().isEmpty()) continue;
                ScoreEntry e = parse(line);
                if (e != null) list.add(e);               // บรรทัดที่อ่านไม่ได้จะถูกข้าม
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
        return list;
    }

    // แปลง 1 บรรทัดเป็น ScoreEntry รองรับทั้ง name,score,difficulty และแบบเก่า name,score (ระดับเป็น null)
    private ScoreEntry parse(String line) {
        try {
            int last = line.lastIndexOf(',');
            if (last < 1) return null;
            String tail = line.substring(last + 1).trim();

            String name;
            int score;
            Difficulty diff = null;
            try {
                score = Integer.parseInt(tail);            // แบบเก่า: คอลัมน์ท้ายสุดเป็นตัวเลข = คะแนน
                name = line.substring(0, last);
            } catch (NumberFormatException notNumber) {    // แบบใหม่: คอลัมน์ท้ายสุดคือระดับ
                int prev = line.lastIndexOf(',', last - 1);
                if (prev < 1) return null;
                name = line.substring(0, prev);
                score = Integer.parseInt(line.substring(prev + 1, last).trim());
                diff = toDifficulty(tail);
            }
            String clean = name.replace("\"", "").trim();
            if (clean.isEmpty()) return null;
            return new ScoreEntry(clean, Math.max(0, score), diff);
        } catch (NumberFormatException bad) {
            return null;
        }
    }

    private Difficulty toDifficulty(String s) {
        try {
            return Difficulty.valueOf(s.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;   // ว่างหรือสะกดผิด
        }
    }

    // ===== ไฟล์ของแต่ละระดับ (name,score) =====

    private void savePerLevel(Path f, Player player) {
        List<Player> all = readPlayers(f);
        Player existing = null;
        for (Player p : all) {
            if (p.getName().equalsIgnoreCase(player.getName())) {
                existing = p;
                break;
            }
        }
        if (existing == null) all.add(player);
        else if (player.getScore() > existing.getScore()) existing.setScore(player.getScore());
        all.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));

        try (BufferedWriter w = Files.newBufferedWriter(f, StandardCharsets.UTF_8)) {
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

    // อ่านไฟล์ name,score (ไม่มีไฟล์ = ลิสต์ว่าง)
    private List<Player> readPlayers(Path f) {
        List<Player> list = new ArrayList<Player>();
        if (!Files.exists(f)) return list;
        try (BufferedReader br = Files.newBufferedReader(f, StandardCharsets.UTF_8)) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                if (line.trim().isEmpty()) continue;
                int comma = line.lastIndexOf(',');
                if (comma < 1) continue;
                try {
                    String name = line.substring(0, comma).replace("\"", "");
                    int score = Integer.parseInt(line.substring(comma + 1).trim());
                    Player p = new Player(name);
                    p.setScore(score);
                    list.add(p);
                } catch (IllegalArgumentException bad) {
                    // ข้ามบรรทัดที่คะแนนไม่ใช่ตัวเลขหรือชื่อว่าง
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        list.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
        return list;
    }

    // ===== แปลงข้อความเป็นรูปแบบ CSV =====

    private String csv(String s) {
        return s.indexOf(',') >= 0 ? "\"" + s.replace("\"", "\"\"") + "\"" : s;
    }
}
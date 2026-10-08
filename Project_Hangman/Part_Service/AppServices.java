package Part_Service;

import Part_Data.CsvScoreRepository;
import Part_Data.CsvWordRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

// ===== บริการของแอปพลิเคชัน =====
public final class AppServices {
    private static final Path DATA_ROOT = locateDataRoot(); // กำหนดโฟลเดอร์ที่เก็บไฟล์ CSV
    private static final Path DATA_WORDS = DATA_ROOT.resolve("words"); // กำหนดโฟลเดอร์ที่เก็บไฟล์ CSV ของคำศัพท์
    private static final Path DATA_PLAYERS = DATA_ROOT.resolve("players").resolve("players.csv"); // กำหนดไฟล์ CSV ของผู้เล่น

    private static final WordService WORDS = new WordService(new CsvWordRepository(DATA_WORDS)); 
    private static final GameService GAME = new GameService(WORDS);
    private static final LeaderboardService LEADERBOARD =
            new LeaderboardService(new CsvScoreRepository(DATA_PLAYERS));

    private AppServices() {}// ป้องกันการสร้างอินสแตนซ์ของคลาสนี้

    public static GameService game() { return GAME; }
    public static LeaderboardService leaderboard() { return LEADERBOARD; }

    // ===== ค้นหาโฟลเดอร์ data ที่เก็บไฟล์ CSV ของคำศัพท์และผู้เล่น =====

    private static Path locateDataRoot() {
        Path workingDir = Paths.get(System.getProperty("user.dir")).toAbsolutePath().normalize();

       
        // ตรวจสอบโฟลเดอร์ปัจจุบันและโฟลเดอร์พาเรนต์ก่อน ซึ่งครอบคลุมการเปิดใช้งานจากโฟลเดอร์ย่อยของซอร์สด้วย
        for (Path p = workingDir; p != null; p = p.getParent()) {
            Path direct = p.resolve("data");
            if (isDataRoot(direct)) return direct;

            
            Path nested = p.resolve("HangmanSC_FINAL_JAVA8_FIXED2").resolve("data");
            if (isDataRoot(nested)) return nested;
        }

        
        
        // ตรวจสอบโฟลเดอร์ย่อยของโฟลเดอร์ปัจจุบันและโฟลเดอร์พาเรนต์ (สูงสุด 4 ระดับ) เพื่อค้นหาโฟลเดอร์ data
        try (Stream<Path> stream = Files.walk(workingDir, 4)) {
            Path found = stream
                    .filter(Files::isDirectory)
                    .map(p -> p.resolve("data"))
                    .filter(AppServices::isDataRoot)
                    .findFirst()
                    .orElse(null);
            if (found != null) return found;
        } catch (IOException ignored) {
            // หากเกิดข้อผิดพลาดในการค้นหาโฟลเดอร์ data ให้ข้ามไปและแสดงข้อความแสดงข้อผิดพลาดด้านล่าง
        }

        // หากไม่พบโฟลเดอร์ data ให้แสดงข้อความแสดงข้อผิดพลาด
        throw new IllegalStateException(
                "Cannot find Hangman data folder. Expected data/words/*.csv and data/players/players.csv "
                        + "under the project folder. Current folder: " + workingDir);
    }

    // ===== ตรวจสอบว่าโฟลเดอร์ที่กำหนดมีไฟล์ CSV ของคำศัพท์และผู้เล่นครบถ้วนหรือไม่ =====

    private static boolean isDataRoot(Path root) {
        return Files.isDirectory(root.resolve("words"))
                && Files.isRegularFile(root.resolve("words").resolve("easy.csv"))
                && Files.isRegularFile(root.resolve("words").resolve("medium.csv"))
                && Files.isRegularFile(root.resolve("words").resolve("hard.csv"))
                && Files.isDirectory(root.resolve("players"));
    }
}

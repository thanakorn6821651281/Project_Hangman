package test;

// นำเข้าคลาสทั้งหมดในแพ็กเกจ Domain ซึ่งมีคลาสเกี่ยวกับกติกาเกม เช่น HangmanGame, Word และ Difficulty
import Domain.*;

// นำเข้าคลาสในแพ็กเกจ Part_Data ซึ่งใช้จัดการข้อมูล เช่น การอ่านคำศัพท์จากไฟล์ CSV
import Part_Data.*;

// ใช้ Path สำหรับแทนตำแหน่งไฟล์หรือโฟลเดอร์
import java.nio.file.Path;

// ใช้ Paths สำหรับสร้าง Path จากชื่อโฟลเดอร์หรือเส้นทางที่กำหนด
import java.nio.file.Paths;

public class TestRunner {

    // นับจำนวนการทดสอบที่ผ่าน
    private static int passed = 0;

    // นับจำนวนการทดสอบที่ไม่ผ่าน
    private static int failed = 0;

    // ตรวจสอบผลการทดสอบแต่ละกรณี แล้วแสดงผลว่า PASS หรือ FAIL
    private static void check(String name, boolean ok) {
        if (ok) {
            // ถ้าเงื่อนไขเป็นจริง ให้เพิ่มจำนวนที่ผ่านและแสดงชื่อการทดสอบ
            passed++;
            System.out.println("PASS: " + name);
        } else {
            // ถ้าเงื่อนไขเป็นเท็จ ให้เพิ่มจำนวนที่ไม่ผ่านและแสดงชื่อการทดสอบ
            failed++;
            System.out.println("FAIL: " + name);
        }
    }

    // ค้นหาโฟลเดอร์ที่เก็บไฟล์คำศัพท์ โดยลองตรวจสอบจากหลายตำแหน่ง
    private static Path findWordsDataDir() {
        // รายชื่อตำแหน่งที่อาจมีโฟลเดอร์ data/words
        Path[] candidates = new Path[] {
                Paths.get("data", "words"),
                Paths.get("HangmanSC_FINAL_JAVA8_FIXED2", "data", "words"),
                Paths.get("..", "data", "words")
        };

        // ตรวจสอบทีละตำแหน่ง ถ้าพบไฟล์คำศัพท์ครบก็ส่งคืนตำแหน่งนั้น
        for (Path p : candidates) {
            if (hasAllWordFiles(p)) {
                return p;
            }
        }

        // หากไม่พบโฟลเดอร์ที่มีไฟล์คำศัพท์ครบ
        // ให้แจ้งข้อผิดพลาดพร้อมตำแหน่งที่โปรแกรมกำลังทำงาน
        throw new IllegalStateException(
                "Cannot find valid data/words directory from the current working directory: "
                        + Paths.get("").toAbsolutePath().normalize());
    }

    // ตรวจสอบว่าโฟลเดอร์ที่ระบุมีไฟล์คำศัพท์ของทั้ง 3 ระดับครบหรือไม่
    private static boolean hasAllWordFiles(Path dir) {
        // ต้องมีไฟล์ easy.csv, medium.csv และ hard.csv จึงจะถือว่าโฟลเดอร์นี้ใช้ได้
        return java.nio.file.Files.isRegularFile(dir.resolve("easy.csv"))
                && java.nio.file.Files.isRegularFile(dir.resolve("medium.csv"))
                && java.nio.file.Files.isRegularFile(dir.resolve("hard.csv"));
    }

    // จุดเริ่มต้นการทำงานของโปรแกรมทดสอบ
    public static void main(String[] args) {

        // ทดสอบว่าการเดาตัวอักษรที่ถูกต้องแสดงคำศัพท์ได้ถูกต้อง
        HangmanGame g = new HangmanGame(
                new Word("CAT", "animal", "Animals"), Difficulty.EASY);
        check("correct guess", g.guess('C') && "C _ _".equals(g.displayWord()));

        // ทดสอบว่าเมื่อเดาตัวอักษรผิดตัวเดิมซ้ำ จะนับความผิดเพียงครั้งเดียว
        g = new HangmanGame(
                new Word("CAT", "animal", "Animals"), Difficulty.EASY);
        g.guess('Z');
        g.guess('Z');
        check("duplicate guess", g.getWrong() == 1);

        // ทดสอบว่าเมื่อเดาตัวอักษรครบทุกตัวแล้ว สถานะเกมต้องเป็น WIN
        g = new HangmanGame(
                new Word("CAT", "animal", "Animals"), Difficulty.EASY);
        g.guess('C');
        g.guess('A');
        g.guess('T');
        check("win", g.getStatus() == GameStatus.WIN);

        // ทดสอบว่าเมื่อเดาผิดจนถึงเงื่อนไขแพ้ สถานะเกมต้องเป็น LOST
        g = new HangmanGame(
                new Word("CAT", "animal", "Animals"), Difficulty.HARD);
        g.guess('B');
        g.guess('D');
        g.guess('E');
        g.guess('O');
        g.guess('Q');
        g.guess('J');
        check("lose", g.getStatus() == GameStatus.LOST);

        // สร้างตัวอ่านข้อมูลคำศัพท์ โดยค้นหาตำแหน่งโฟลเดอร์ข้อมูลจากเมธอดด้านบน
        CsvWordRepository repo = new CsvWordRepository(findWordsDataDir());

        // ตรวจสอบว่าไฟล์คำศัพท์ระดับ Easy มีจำนวน 50 คำ
        check("easy has 50 words", repo.findByDifficulty(Difficulty.EASY).size() == 50);

        // ตรวจสอบว่าไฟล์คำศัพท์ระดับ Medium มีจำนวน 50 คำ
        check("medium has 50 words", repo.findByDifficulty(Difficulty.MEDIUM).size() == 50);

        // ตรวจสอบว่าไฟล์คำศัพท์ระดับ Hard มีจำนวน 50 คำ
        check("hard has 50 words", repo.findByDifficulty(Difficulty.HARD).size() == 50);

        // แสดงเส้นคั่นและสรุปจำนวนการทดสอบที่ผ่านกับไม่ผ่าน
        System.out.println("------------------------");
        System.out.println("Passed: " + passed + "  Failed: " + failed);

        // หากมีการทดสอบไม่ผ่านอย่างน้อยหนึ่งรายการ ให้จบโปรแกรมด้วยสถานะผิดพลาด
        if (failed > 0) {
            System.exit(1);
        }
    }
}

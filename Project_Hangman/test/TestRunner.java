package test;

import Domain.*;
import Part_Data.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public class TestRunner {
    private static int passed = 0;
    private static int failed = 0;

    private static void check(String name, boolean ok) {
        if (ok) { passed++; System.out.println("PASS: " + name); }
        else { failed++; System.out.println("FAIL: " + name); }
    }

    private static Path findWordsDataDir() {
        Path[] candidates = new Path[] {
            Paths.get("data", "words"),
            Paths.get("HangmanSC_FINAL_JAVA8_FIXED2", "data", "words"),
            Paths.get("..", "data", "words")
        };
        for (Path p : candidates) {
            if (hasAllWordFiles(p)) return p;
        }
        throw new IllegalStateException(
                "Cannot find valid data/words directory from the current working directory: "
                        + Paths.get("").toAbsolutePath().normalize());
    }

    private static boolean hasAllWordFiles(Path dir) {
        return java.nio.file.Files.isRegularFile(dir.resolve("easy.csv"))
                && java.nio.file.Files.isRegularFile(dir.resolve("medium.csv"))
                && java.nio.file.Files.isRegularFile(dir.resolve("hard.csv"));
    }

    public static void main(String[] args) {
        HangmanGame g = new HangmanGame(new Word("CAT", "animal", "Animals"), Difficulty.EASY);
        check("correct guess", g.guess('C') && "C _ _".equals(g.displayWord()));

        g = new HangmanGame(new Word("CAT", "animal", "Animals"), Difficulty.EASY);
        g.guess('Z'); g.guess('Z');
        check("duplicate guess", g.getWrong() == 1);

        g = new HangmanGame(new Word("CAT", "animal", "Animals"), Difficulty.EASY);
        g.guess('C'); g.guess('A'); g.guess('T');
        check("win", g.getStatus() == GameStatus.WIN);

        g = new HangmanGame(new Word("CAT", "animal", "Animals"), Difficulty.HARD);
        g.guess('B'); g.guess('D'); g.guess('E'); g.guess('F');
        check("lose", g.getStatus() == GameStatus.LOST);

        CsvWordRepository repo = new CsvWordRepository(findWordsDataDir());
        check("easy has 50 words", repo.findByDifficulty(Difficulty.EASY).size() == 50);
        check("medium has 50 words", repo.findByDifficulty(Difficulty.MEDIUM).size() == 50);
        check("hard has 50 words", repo.findByDifficulty(Difficulty.HARD).size() == 50);

        System.out.println("------------------------");
        System.out.println("Passed: " + passed + "  Failed: " + failed);
        if (failed > 0) System.exit(1);
    }
}

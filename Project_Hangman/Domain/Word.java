package Domain;
//===== คำศัพท์ =====
public class Word {
    private final String word;
    private final String hint;
    private final String category;

    //===== สร้างคำศัพท์ =====
    
    public Word(String word, String hint, String category) {
        if (word == null || word.trim().isEmpty()) throw new IllegalArgumentException("word");
        if (hint == null || hint.trim().isEmpty()) throw new IllegalArgumentException("hint");
        if (category == null || category.trim().isEmpty()) throw new IllegalArgumentException("category");
        this.word = word.trim().toUpperCase();
        this.hint = hint.trim();
        this.category = category.trim();
    }

    public String getWord() { return word; }
    public String getHint() { return hint; }
    public String getCategory() { return category; }
}

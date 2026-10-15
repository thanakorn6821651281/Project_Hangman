package Part_gui;

import Domain.HangmanGame;
import Domain.Player;
import Part_Service.AppServices;

public class Lose extends javax.swing.JFrame {

    // ===== ตัวแปรที่ใช้เก็บข้อมูลของผู้เล่นและเกม =====

    private Player player; // ข้อมูลผู้เล่นที่ส่งมาจากหน้า GamePlayUI
    private HangmanGame game; // ข้อมูลและสถานะของเกม Hangman

    // ===== ตัวแปรสำหรับจัดการส่วนต่าง ๆ ของหน้าจอ =====
    private javax.swing.JLabel lblScoreText; // ป้ายแสดงตัวเลขคะแนนรวม

    // ===== ตัวสร้างหน้าจอเมื่อแพ้เกม =====
    // ตั้งค่าหน้าจอ ปุ่มกด และตำแหน่งของตัวเลขคะแนน
    public Lose() {
        initComponents();
        setResizable(false);
        pack();
        setLocationRelativeTo(null); // จัดหน้าจอให้อยู่ตรงกลาง

        // ปิดกรอบโฟกัสสีฟ้าเวลาคลิกปุ่ม
        LBacktostart.setFocusable(false);
        LRestart.setFocusable(false);

        // สร้าง Label สำหรับตัวเลขคะแนนแยกต่างหาก เพื่อไม่ให้กวนตำแหน่งป้าย
        lblScoreText = new javax.swing.JLabel("0");
        lblScoreText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScoreText.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        lblScoreText.setForeground(new java.awt.Color(80, 40, 10));
        lblScoreText.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 36));

        // วางทับลงบนตำแหน่งกรอบว่างของป้ายคะแนน
        getContentPane().add(lblScoreText, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 610, 280, 80), 0);
    }

    // ตัวสร้างหน้าจอเมื่อรับข้อมูลผู้เล่นและเกมมาจากหน้า GamePlayUI ตอนแพ้
    public Lose(Player player, HangmanGame game) {
        this();
        this.player = player;
        this.game = game;

        // ดึงคะแนนของผู้เล่นมาแสดงที่ป้ายคะแนน
        int totalScore = (player != null) ? player.getScore() : 0;
        lblScoreText.setText(String.valueOf(totalScore));
    }

    // ===== ส่วนสร้างและจัดวางองค์ประกอบ GUI =====
    // โค้ดส่วนนี้สร้างปุ่ม รูปภาพ และตำแหน่งขององค์ประกอบต่าง ๆ
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LBacktostart = new javax.swing.JButton();
        LRestart = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LBacktostart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Back to Start Label.png")));
        LBacktostart.setBorderPainted(false);
        LBacktostart.setContentAreaFilled(false);
        LBacktostart.addActionListener(this::LBacktostartActionPerformed);
        getContentPane().add(LBacktostart, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, -1, -1));

        LRestart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Restart.png")));
        LRestart.setBorderPainted(false);
        LRestart.setContentAreaFilled(false);
        LRestart.addActionListener(this::LRestartActionPerformed);
        getContentPane().add(LRestart, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 480, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/GameOver.png")));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ScoreBoard.png")));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 540, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/mainmenu.png")));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ===== เหตุการณ์เมื่อกดปุ่มกลับหน้าแรก (BACK TO START) =====
    // ปิดหน้าปัจจุบันแล้วกลับไปยังหน้า HomeUI
    private void LBacktostartActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_LBacktostartActionPerformed
        HomeUI home = new HomeUI();
        home.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_LBacktostartActionPerformed

    // ===== เหตุการณ์เมื่อกดปุ่มเริ่มใหม่ (RESTART) =====
    // เริ่มเล่นเกมใหม่ในระดับความยากเดิมและรีเซ็ตคะแนนของผู้เล่นในรอบใหม่เป็น 0
    private void LRestartActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_LRestartActionPerformed
        if (game != null) {
            // สุ่มคำศัพท์ใหม่ในระดับความยากเดิม
            HangmanGame newGame = AppServices.game().nextGame(game.getDifficulty(), game.getAnswer());

            // สร้างข้อมูลผู้เล่นใหม่ด้วยชื่อเดิมเพื่อให้คะแนนเริ่มนับ 0 ใหม่
            Player newPlayer = (player != null) ? new Player(player.getName()) : null;

            GamePlayUI playAgain = new GamePlayUI(newPlayer, newGame);
            playAgain.setVisible(true);
            this.dispose();
        } else {
            // กรณีเปิดทดสอบหน้า Lose เดี่ยว ๆ ให้กลับไปหน้า HomeUI
            new HomeUI().setVisible(true);
            this.dispose();
        }
    }// GEN-LAST:event_LRestartActionPerformed

    // ===== ประกาศตัวแปรขององค์ประกอบ GUI =====
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LBacktostart;
    private javax.swing.JButton LRestart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
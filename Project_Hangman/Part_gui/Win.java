package Part_gui;

import Domain.HangmanGame;
import Domain.Player;
import Part_Service.AppServices;

public class Win extends javax.swing.JFrame {

    // ===== ตัวแปรที่ใช้เก็บข้อมูลของผู้เล่นและเกม =====

    private Player player; // ข้อมูลผู้เล่นปัจจุบัน
    private HangmanGame game; // ข้อมูลและสถานะของเกม Hangman

    // ===== ตัวแปรสำหรับจัดการส่วนต่าง ๆ ของหน้าจอ =====
    private javax.swing.JLabel lblScoreText; // ป้ายแสดงตัวเลขคะแนนสะสม

    // ===== ตัวสร้างหน้าจอเมื่อชนะเกม =====
    // ตั้งค่าหน้าจอ ปุ่มกด และตำแหน่งของตัวเลขคะแนน
    public Win() {
        initComponents();
        setResizable(false);
        pack();
        setLocationRelativeTo(null); // จัดหน้าจอให้อยู่ตรงกลาง

        // ปิดกรอบโฟกัสสีฟ้าเวลาคลิกปุ่ม
        WBacktostart.setFocusable(false);
        WNext.setFocusable(false);

        // สร้าง Label สำหรับตัวเลขคะแนนแยกต่างหาก เพื่อไม่ให้กวนตำแหน่งป้าย
        lblScoreText = new javax.swing.JLabel("0");
        lblScoreText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScoreText.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        lblScoreText.setForeground(new java.awt.Color(80, 40, 10));
        lblScoreText.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 36));

        // วางทับลงบนตำแหน่งกรอบว่างของป้ายคะแนน
        getContentPane().add(lblScoreText, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 610, 280, 80), 0);
    }

    // ตัวสร้างหน้าจอเมื่อรับข้อมูลผู้เล่นและเกมมาจากหน้า GamePlayUI ตอนชนะ
    public Win(Player player, HangmanGame game) {
        this();
        this.player = player;
        this.game = game;

        // ดึงคะแนนสะสมของผู้เล่นมาแสดงที่ป้ายคะแนน
        int totalScore = (player != null) ? player.getScore() : 0;
        lblScoreText.setText(String.valueOf(totalScore));
    }

    // ===== ส่วนสร้างและจัดวางองค์ประกอบ GUI =====
    // โค้ดส่วนนี้สร้างปุ่ม รูปภาพ และตำแหน่งขององค์ประกอบต่าง ๆ
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WBacktostart = new javax.swing.JButton();
        WNext = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        WBacktostart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Back to Start Label.png")));
        WBacktostart.setBorderPainted(false);
        WBacktostart.setContentAreaFilled(false);
        WBacktostart.addActionListener(this::WBacktostartActionPerformed);
        getContentPane().add(WBacktostart, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 480, -1, -1));

        WNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Next.png")));
        WNext.setBorderPainted(false);
        WNext.setContentAreaFilled(false);
        WNext.addActionListener(this::WNextActionPerformed);
        getContentPane().add(WNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 480, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/You win.png")));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ScoreBoard.png")));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 540, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/mainmenu.png")));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ===== เหตุการณ์เมื่อกดปุ่มกลับหน้าแรก (BACK TO START) =====
    // บันทึกคะแนนสะสมลงตารางคะแนน แล้วกลับไปยังหน้า HomeUI
    private void WBacktostartActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_WBacktostartActionPerformed
        if (player != null && game != null && player.getScore() > 0) {
            try {
                AppServices.leaderboard().save(player, game.getDifficulty());
            } catch (RuntimeException e) {
                System.out.println("Could not save score: " + e.getMessage());
            }
        }
        HomeUI home = new HomeUI();
        home.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_WBacktostartActionPerformed

    // ===== เหตุการณ์เมื่อกดปุ่มถัดไป (NEXT) =====
    // สุ่มคำศัพท์คำต่อไปในระดับความยากเดิม
    // โดยใช้ข้อมูลผู้เล่นคนเดิมเพื่อสะสมคะแนนต่อ
    private void WNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_WNextActionPerformed
        if (game != null) {
            HangmanGame nextGame = AppServices.game().nextGame(game.getDifficulty(), game.getAnswer());
            GamePlayUI nextPlay = new GamePlayUI(player, nextGame);
            nextPlay.setVisible(true);
            this.dispose();
        } else {
            new HomeUI().setVisible(true);
            this.dispose();
        }
    }// GEN-LAST:event_WNextActionPerformed

    // ===== ประกาศตัวแปรขององค์ประกอบ GUI =====
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton WBacktostart;
    private javax.swing.JButton WNext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
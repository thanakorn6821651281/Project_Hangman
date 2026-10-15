package Part_gui;

import Domain.HangmanGame;
import Domain.Player;
import Part_Service.AppServices;

public class Win extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Win.class.getName());

    // ตัวแปรเก็บข้อมูลผู้เล่นและเกม
    private Player player;
    private HangmanGame game;
    private javax.swing.JLabel lblScoreText;

    /**
     * Creates new form Win
     */
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

        // วางทับลงบนตำแหน่งกรอบว่างของป้าย (ในหน้า Win ป้ายอยู่ที่ x=505, y=540)
        // ถ้าในหน้า Lose ปรับตัวเลข x, y ไว้เท่าไหร่ เอาเลขเดียวกันมาใส่ตรงนี้ได้เลยครับ
        getContentPane().add(lblScoreText, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 610, 280, 80), 0);
    }

    // Constructor สำหรับรับข้อมูลจากหน้า GamePlayUI ตอนชนะ
    public Win(Player player, HangmanGame game) {
        this(); // เรียก Constructor ด้านบนเพื่อตั้งค่าหน้าจอ
        this.player = player;
        this.game = game;

        // ดึงคะแนนสะสมของผู้เล่นมาแสดงที่ป้าย SCORE
        int totalScore = (player != null) ? player.getScore() : 0;
        lblScoreText.setText(String.valueOf(totalScore));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WBacktostart = new javax.swing.JButton();
        WNext = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        WBacktostart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Back to Start Label.png"))); // NOI18N
        WBacktostart.setBorderPainted(false);
        WBacktostart.setContentAreaFilled(false);
        WBacktostart.addActionListener(this::WBacktostartActionPerformed);
        getContentPane().add(WBacktostart, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 480, -1, -1));

        WNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Next.png"))); // NOI18N
        WNext.setBorderPainted(false);
        WNext.setContentAreaFilled(false);
        WNext.addActionListener(this::WNextActionPerformed);
        getContentPane().add(WNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 480, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/You win.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ScoreBoard.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 540, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/mainmenu.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ปุ่ม BACK TO START -> บันทึกคะแนนสะสม แล้วกลับไปหน้า HomeUI
    private void WBacktostartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WBacktostartActionPerformed
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
    }//GEN-LAST:event_WBacktostartActionPerformed

    // ปุ่ม NEXT -> ไปเล่นคำศัพท์คำต่อไป โดยใช้ player คนเดิม (คะแนนสะสมต่อเนื่อง)
    private void WNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WNextActionPerformed
        if (game != null) {
            HangmanGame nextGame = AppServices.game().nextGame(game.getDifficulty(), game.getAnswer());
            GamePlayUI nextPlay = new GamePlayUI(player, nextGame);
            nextPlay.setVisible(true);
            this.dispose();
        } else {
            new HomeUI().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_WNextActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new Win().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton WBacktostart;
    private javax.swing.JButton WNext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Part_gui;

import Domain.HangmanGame;
import Domain.Player;
import Part_Service.AppServices;

public class Lose extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Lose.class.getName());
    
    // ตัวแปรเก็บข้อมูลผู้เล่นและเกมที่ส่งมาจากหน้า GamePlayUI
    private Player player;
    private HangmanGame game;
    private javax.swing.JLabel lblScoreText;

    /**
     * Creates new form Lose
     */
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
        
        // วางทับลงบนตำแหน่งกรอบว่างของป้าย (x=500, y=600, กว้าง=280, สูง=80)
        // ปรับค่า y (600) เพื่อเลื่อนเฉพาะตัวเลขขึ้น-ลง
        getContentPane().add(lblScoreText, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 610, 280, 80), 0);
    }

   // Constructor สำหรับรับข้อมูลจากหน้า GamePlayUI ตอนแพ้
    public Lose(Player player, HangmanGame game) {
        this(); // เรียก Constructor ด้านบนเพื่อตั้งค่าหน้าจอ
        this.player = player;
        this.game = game;

        // ดึงคะแนนของผู้เล่นมาแสดงที่ป้าย SCORE
        int totalScore = (player != null) ? player.getScore() : 0;
        lblScoreText.setText(String.valueOf(totalScore)); // <-- ใส่แทน jLabel3.setText ตรงนี้เลยครับ
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LBacktostart = new javax.swing.JButton();
        LRestart = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        LBacktostart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Back to Start Label.png"))); // NOI18N
        LBacktostart.setBorderPainted(false);
        LBacktostart.setContentAreaFilled(false);
        LBacktostart.addActionListener(this::LBacktostartActionPerformed);
        getContentPane().add(LBacktostart, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, -1, -1));

        LRestart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Restart.png"))); // NOI18N
        LRestart.setBorderPainted(false);
        LRestart.setContentAreaFilled(false);
        LRestart.addActionListener(this::LRestartActionPerformed);
        getContentPane().add(LRestart, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 480, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/GameOver.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/ScoreBoard.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 540, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/mainmenu.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ปุ่ม BACK TO START -> กลับไปหน้า HomeUI
    private void LBacktostartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LBacktostartActionPerformed
        HomeUI home = new HomeUI();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LBacktostartActionPerformed

    // ปุ่ม RESTART -> เริ่มเล่นใหม่ในระดับความยากเดิม
    private void LRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LRestartActionPerformed
        if (game != null) {
            // สุ่มคำศัพท์ใหม่ในระดับความยากเดิม
            HangmanGame newGame = AppServices.game().nextGame(game.getDifficulty(), game.getAnswer());
            
            // สร้าง Player ใหม่เพื่อให้คะแนนเริ่มนับ 0 ใหม่ในการเล่นรอบใหม่ (หรือใช้ player เดิมถ้าไม่อยากรีเซ็ต)
            Player newPlayer = (player != null) ? new Player(player.getName()) : null;
            
            GamePlayUI playAgain = new GamePlayUI(newPlayer, newGame);
            playAgain.setVisible(true);
            this.dispose();
        } else {
            // กรณีเปิดเทสหน้า Lose เดี่ยวๆ ให้กลับไปหน้า Home
            new HomeUI().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_LRestartActionPerformed

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

        java.awt.EventQueue.invokeLater(() -> new Lose().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LBacktostart;
    private javax.swing.JButton LRestart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
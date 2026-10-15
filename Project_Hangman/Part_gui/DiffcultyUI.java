package Part_gui;

import Domain.Player;

public class DiffcultyUI extends javax.swing.JFrame {

    // ===== ตัวแปรที่ใช้เก็บข้อมูลของผู้เล่น =====

    private Player player; // ข้อมูลผู้เล่นที่รับมาจากหน้า LoginUI

    // ===== ตัวสร้างหน้าจอเลือกระดับความยาก =====
    // ตั้งค่าขนาดและจัดตำแหน่งหน้าจอให้อยู่กึ่งกลาง
    public DiffcultyUI() {
        initComponents();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    // ตัวสร้างหน้าจอเมื่อรับข้อมูลผู้เล่นมาจากหน้า LoginUI
    public DiffcultyUI(Player player) {
        this();
        this.player = player;
    }

    // คืนค่าข้อมูลผู้เล่นปัจจุบัน
    public Player getPlayer() {
        return player;
    }

    // ===== ส่วนสร้างและจัดวางองค์ประกอบ GUI =====
    // โค้ดส่วนนี้สร้างปุ่ม รูปภาพ และตำแหน่งขององค์ประกอบต่าง ๆ
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/EASY.png")));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/MEDIUM.png")));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/HARD.png")));
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 230, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/BACK.png")));
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(this::jButton4ActionPerformed);
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 600, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/BACKGROUND DIFFCULTY.png")));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ===== การเริ่มต้นเกมตามระดับความยากที่เลือก =====
    // สุ่มคำศัพท์ตามระดับความยาก แล้วส่งข้อมูลผู้เล่นและเกมไปยังหน้า GamePlayUI
    private void startGame(Domain.Difficulty level) {
        try {
            Domain.HangmanGame game = Part_Service.AppServices.game().newGame(level);
            GamePlayUI next = new GamePlayUI(player, game);
            next.setVisible(true);
            this.dispose();
        } catch (RuntimeException | ExceptionInInitializerError ex) {
            javax.swing.JOptionPane.showMessageDialog(this,
                    "โหลดคำศัพท์ไม่ได้: " + ex);

        }
    }

    // ===== เหตุการณ์เมื่อกดปุ่มระดับง่าย (EASY) =====
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        startGame(Domain.Difficulty.EASY);
    }// GEN-LAST:event_jButton1ActionPerformed

    // ===== เหตุการณ์เมื่อกดปุ่มระดับปานกลาง (MEDIUM) =====
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        startGame(Domain.Difficulty.MEDIUM);
    }// GEN-LAST:event_jButton2ActionPerformed

    // ===== เหตุการณ์เมื่อกดปุ่มระดับยาก (HARD) =====
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        startGame(Domain.Difficulty.HARD);
    }// GEN-LAST:event_jButton3ActionPerformed

    // ===== เหตุการณ์เมื่อกดปุ่มย้อนกลับ (BACK) =====
    // กลับไปยังหน้ากรอกชื่อผู้เล่น (LoginUI)
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
        LoginUI back = new LoginUI();
        back.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_jButton4ActionPerformed

    // ===== ประกาศตัวแปรขององค์ประกอบ GUI =====
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
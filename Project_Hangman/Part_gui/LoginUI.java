package Part_gui;

import javax.swing.JOptionPane;
import Domain.Player;
import java.awt.event.*;

public class LoginUI extends javax.swing.JFrame {

    // ===== ตัวแปรสำหรับบันทึกการทำงานของหน้าจอ =====
    // ===== ตัวสร้างหน้าจอเข้าสู่ระบบ (กรอกชื่อผู้เล่น) =====
    // ตั้งค่าหน้าจอ ช่องกรอกข้อความให้โปร่งใส และจำกัดตัวอักษรที่พิมพ์ได้
    public LoginUI() {
        initComponents();
        setResizable(false);
        pack();
        jTextField1.setOpaque(false); // ทำให้ช่องกรอกข้อความโปร่งใส
        jTextField1.setBackground(new java.awt.Color(0, 0, 0, 0)); // ตั้งค่าสีพื้นหลังโปร่งใส
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder()); // ลบขอบของช่องกรอกข้อความ
        setLocationRelativeTo(null); // จัดหน้าจอให้อยู่ตรงกลาง

        // ควบคุมให้พิมพ์ได้เฉพาะตัวอักษรภาษาอังกฤษ (a-z, A-Z) และตัวเลข (0-9)
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                // ตรวจสอบว่าไม่ใช่ a-z, A-Z, 0-9 และไม่ใช่ปุ่มลบ (Backspace)
                if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')
                        || c == KeyEvent.VK_BACK_SPACE)) {
                    evt.consume(); // ปฏิเสธการรับค่าตัวอักษรนั้น
                }
            }
        });
    }

    // ===== การตรวจสอบชื่อผู้เล่นซ้ำ =====
    // ตรวจสอบว่าชื่อที่กรอกมีอยู่ในระบบตารางคะแนนแล้วหรือไม่
    private boolean nameExists(String name) {
        for (Player p : Part_Service.AppServices.leaderboard().getScores()) {
            if (p.getName().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    // ===== ส่วนสร้างและจัดวางองค์ประกอบ GUI =====
    // โค้ดส่วนนี้สร้างปุ่ม รูปภาพ และตำแหน่งขององค์ประกอบต่าง ๆ
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/CONTINUE LOGIN.png")));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 160, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/BACK.png")));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/CONTINUE.png")));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 500, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/gui/ChatGPT Image Sep 30, 2026, 03_05_14 PM 6 (2).png")));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, -1, -1));

        jTextField1.setBackground(new java.awt.Color(242, 242, 242));
        jTextField1.setBorder(null);
        jTextField1.addActionListener(this::jTextField1ActionPerformed);
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 430, 230, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("/gui/ChatGPT Image Sep 30, 2026, 03_05_14 PM 5.png")));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 360, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/mainmenu.png")));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ===== เหตุการณ์เมื่อกดปุ่มย้อนกลับ (BACK) =====
    // กลับไปยังหน้าเมนูหลัก (HomeUI)
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        HomeUI back = new HomeUI();
        back.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_jButton1ActionPerformed

    // ===== เหตุการณ์เมื่อกดปุ่มดำเนินการต่อ (CONTINUE) =====
    // ตรวจสอบความถูกต้องของชื่อผู้เล่น และส่งข้อมูลไปยังหน้าเลือกระดับความยาก
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        String name = jTextField1.getText().trim();

        // ตรวจสอบว่าผู้เล่นได้กรอกชื่อหรือไม่
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "please enter your name");
            return;
        }

        // ตรวจสอบว่ามีชื่อนี้อยู่ในระบบแล้วหรือยัง
        if (nameExists(name)) {
            JOptionPane.showMessageDialog(this, "This name already to use, please enter another name jub jub");
            return;
        }

        // สร้างอ็อบเจกต์ผู้เล่นใหม่หากชื่อไม่ซ้ำ แล้วเปิดหน้าเลือกระดับความยาก
        Player player = new Player(name);
        DiffcultyUI next = new DiffcultyUI(player);
        next.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_jButton2ActionPerformed

    // ===== เหตุการณ์เมื่อกดปุ่ม Enter ในช่องกรอกชื่อ =====
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jTextField1ActionPerformed
    }// GEN-LAST:event_jTextField1ActionPerformed

    // ===== ประกาศตัวแปรขององค์ประกอบ GUI =====
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
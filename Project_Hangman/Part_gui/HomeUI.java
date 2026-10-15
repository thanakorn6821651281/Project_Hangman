package Part_gui;

public class HomeUI extends javax.swing.JFrame {

    // ===== ตัวแปรสำหรับบันทึกการทำงานของหน้าจอ =====

    // ===== ตัวสร้างหน้าจอเมนูหลัก (Home) =====
    // ตั้งค่าขนาดและจัดตำแหน่งหน้าจอให้อยู่กึ่งกลาง
    public HomeUI() {
        initComponents();
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    // ===== ส่วนสร้างและจัดวางองค์ประกอบ GUI =====
    // โค้ดส่วนนี้สร้างปุ่ม รูปภาพ และตำแหน่งขององค์ประกอบต่าง ๆ
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        START = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/CONTINUE HOME.png")));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));

        START.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/START.png")));
        START.setBorderPainted(false);
        START.setContentAreaFilled(false);
        START.addActionListener(this::STARTActionPerformed);
        getContentPane().add(START, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/LEADERBOARD.png")));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/mainmenu.png")));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ===== เหตุการณ์เมื่อกดปุ่มตารางคะแนน (LEADERBOARD) =====
    // เปิดหน้าตารางคะแนน (LeaderBoardUI)
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        LeaderBoardUI next = new LeaderBoardUI();
        next.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_jButton2ActionPerformed

    // ===== เหตุการณ์เมื่อกดปุ่มเริ่มเกม (START) =====
    // เปิดหน้ากรอกชื่อผู้เล่น (LoginUI)
    private void STARTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_STARTActionPerformed
        LoginUI next = new LoginUI();
        next.setVisible(true);
        this.dispose();
    }// GEN-LAST:event_STARTActionPerformed

    // ===== ประกาศตัวแปรขององค์ประกอบ GUI =====
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton START;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
package Part_gui;

public class LeaderBoardUI extends javax.swing.JFrame {

    private javax.swing.JLabel[] nameLabels; // ป้ายแสดงรายชื่อผู้เล่น 5 อันดับแรก
    private javax.swing.JLabel[] scoreLabels; // ป้ายแสดงคะแนนของผู้เล่น 5 อันดับแรก

    // ===== ตัวสร้างหน้าจอตารางคะแนน (Leaderboard) =====
    // ตั้งค่าหน้าจอ ฟอนต์ สีข้อความ และแสดงคะแนนเริ่มต้นที่ระดับง่าย (EASY)
    public LeaderBoardUI() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        nameLabels = new javax.swing.JLabel[] { jLabel14, jLabel16, jLabel15, jLabel17, jLabel12 };
        scoreLabels = new javax.swing.JLabel[] { jLabel19, jLabel20, jLabel21, jLabel22, jLabel18 };

        java.awt.Font font = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20);
        java.awt.Color color = new java.awt.Color(60, 30, 10);
        for (javax.swing.JLabel[] row : new javax.swing.JLabel[][] { nameLabels, scoreLabels }) {
            for (javax.swing.JLabel l : row) {
                l.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER); // จัดข้อความให้อยู่กึ่งกลางป้าย
                l.setVerticalTextPosition(javax.swing.SwingConstants.CENTER);
                l.setFont(font);
                l.setForeground(color);
            }
        }
        showScores(Domain.Difficulty.EASY);
    }

    // ตัวสร้างหน้าจอโดยระบุระดับความยากที่ต้องการแสดงผลทันที
    public LeaderBoardUI(Domain.Difficulty level) {
        this();
        showScores(level);
    }

    // ===== การดึงและแสดงข้อมูลคะแนนตามระดับความยาก =====
    // ดึงรายชื่อและคะแนนจากระบบมาแสดงบนป้ายทั้ง 5 อันดับ
    private void showScores(Domain.Difficulty level) {
        java.util.List<Domain.Player> list;
        try {
            list = Part_Service.AppServices.leaderboard().getScores(level);
        } catch (RuntimeException | ExceptionInInitializerError ex) {

            list = new java.util.ArrayList<>();
        }
        for (int i = 0; i < nameLabels.length; i++) {
            if (i < list.size()) {
                nameLabels[i].setText(list.get(i).getName());
                scoreLabels[i].setText(String.valueOf(list.get(i).getScore()));
            } else {
                nameLabels[i].setText("-");
                scoreLabels[i].setText("-");
            }
        }
    }

    // ===== ส่วนสร้างและจัดวางองค์ประกอบ GUI =====
    // โค้ดส่วนนี้สร้างปุ่ม รูปภาพ และตำแหน่งขององค์ประกอบต่าง ๆ
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/mainmenu.png")));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 750));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/COMPONENT LEDAERBOARD.png")));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Rank Label.png")));
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/CONTINUE LEADERBOARD.png")));
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Player label.png")));
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 290, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Score Label.png")));
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 290, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/1.png")));
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/2.png")));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 390, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/3.png")));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 440, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/4.png")));
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/5.png")));
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 540, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Long Label.png")));
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 540, -1, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Long Label.png")));
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 340, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Long Label.png")));
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Long Label.png")));
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Long Label.png")));
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, -1, -1));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Short Label.png")));
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 540, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Short Label.png")));
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 340, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Short Label.png")));
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 390, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Short Label.png")));
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 440, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/Short Label.png")));
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 490, -1, -1));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/EASYLEAD.png")));
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 50, -1, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/MEDIUMLEAD.png")));
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(this::jButton1ActionPerformed);
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 110, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/HARDLEAD.png")));
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 160, -1, -1));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/BACK.png")));
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.addActionListener(this::jButton4ActionPerformed);
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 600, 300, 100));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/mainmenu.png")));
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // ===== เหตุการณ์เมื่อกดปุ่มดูคะแนนระดับปานกลาง (MEDIUM) =====
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
        showScores(Domain.Difficulty.MEDIUM);
    }// GEN-LAST:event_jButton1ActionPerformed

    // ===== เหตุการณ์เมื่อกดปุ่มดูคะแนนระดับยาก (HARD) =====
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
        showScores(Domain.Difficulty.HARD);
    }// GEN-LAST:event_jButton2ActionPerformed

    // ===== เหตุการณ์เมื่อกดปุ่มดูคะแนนระดับง่าย (EASY) =====
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton3ActionPerformed
        showScores(Domain.Difficulty.EASY);
    }// GEN-LAST:event_jButton3ActionPerformed

    // ===== เหตุการณ์เมื่อกดปุ่มย้อนกลับ (BACK) =====
    // ปิดหน้าตารางคะแนนแล้วกลับไปยังหน้าเมนูหลัก (HomeUI)
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
        HomeUI back = new HomeUI();
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
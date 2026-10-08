import Part_gui.HomeUI;
import Part_Service.AppServices;

public class Main {
    public static void main(String[] args) {
        // โหลดข้อมูลล่วงหน้า ถ้าหาโฟลเดอร์ data ไม่เจอจะแจ้งตั้งแต่เริ่ม ไม่ต้องรอพังตอนเลือกระดับ
        try {
            AppServices.game();
            AppServices.leaderboard();
        } catch (Throwable t) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "เริ่มเกมไม่ได้: " + t.getMessage(),
                "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            t.printStackTrace();
            return;
        }

        java.awt.EventQueue.invokeLater(() -> new HomeUI().setVisible(true));
    }
}

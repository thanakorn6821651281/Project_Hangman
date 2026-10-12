package project_hangman;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {

    private Image background;

    public BackgroundPanel() {
        background = new ImageIcon(
                getClass().getResource("/gui/image 1.png")
        ).getImage();

        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background != null) {
            g.drawImage(
                    background,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    this
            );
        }
    }
}
package hanoi;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;

class ImagePanel extends JComponent { //for frame background
	private static final long serialVersionUID = -9114269599997683531L;
	private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
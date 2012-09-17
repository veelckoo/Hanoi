package hanoi;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class Label extends JLabel{

	private static final long serialVersionUID = -8483113385121105557L;
	Color myColor = new Color(192,192,192);
	private Font myFont = new Font("Trebuchet MS", Font.PLAIN, 36);     
    
	Label(String text, String icon){
		setText(text);
		this.setHorizontalAlignment(CENTER);
		setForeground(myColor);
		setFont(myFont);
		if(icon == null){
		}else{ 
			setIcon(new javax.swing.ImageIcon(getClass().getResource(icon)));
		}
	}

}

package hanoi;

import java.awt.Image;
import java.io.IOException;

public class InfoPanel extends ImagePanel{
	private static final long serialVersionUID = 6135180133699372143L;

	InfoPanel(Image image) throws IOException{
        super(image);
        
        Label crew = new Label("Towers of Hanoi\n"
                + "Java programming project\n"
                + "7.09.2012\n"
                + "\n"
                + "Authors: (in alphabetical order)\n"
                + "Arek\n"
                + "Gemma\n"
                + "Karl", null);  
        crew.setBounds( 0,  20, 800, 500);
        this.add(crew);
     }
}

package hanoi;

public class Disc extends javax.swing.JLabel {

	private static final long serialVersionUID = 1498736294983458651L;
	private final int MY_HEIGHT = 40;
    private int diameter;   //to compare disks when putting down
    private int xCoord;
    private int yCoord;
    private Peg peg;        //when we want to find out on which peg disk sits
      
    public Disc(int num){
        diameter = num;
        xCoord = 0;
        yCoord = 0;
        //setText(String.valueOf(num));
        setBounds(xCoord, yCoord, diameter, MY_HEIGHT);
        String imageName = "/res/disc"+diameter+".png";
        setIcon(new javax.swing.ImageIcon(getClass().getResource(imageName)));
        setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        setBorder(null);
        setOpaque(false);
        //setBorderPainted(false);
    }      
    
    public void moveDisc(int x, int y){
        xCoord=x;
        yCoord=y;
        //repaint(xCoord,xCoord,diameter,MY_HEIGHT);
        setBounds(xCoord,yCoord,diameter,MY_HEIGHT);
    }
    
    public int getDiameter(){
        return diameter;
    };

    public Peg getPeg(){
        return peg;
    }
    
    public void setPeg(Peg p){
        peg = p;
    }
    
}

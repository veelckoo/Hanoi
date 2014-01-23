package hanoi;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Hanoi implements MouseListener, MouseMotionListener {
   private static final int PEGS = 3;
   private static final int BOTTOM_MARGIN = 100;
   private static Peg[] pegArray;
   protected static int moves, minMoves;
   protected static int totalDiscs;
   private static JFrame frame;
   private Label movesNum = new Label("0", null);
   private Image panelBg;
   private int startDragX, startDragY;
   private boolean inDrag = false;
   
   public static void main(String[] args) {
        new Hanoi();
   }

    /**
     * @return returns number of discs to play with
     */
   //TODO Rewrite or make nice graphics
    public static int chooseDifficulty(){
        //TODO panel with radio buttons instead of option pane;
        String tempStr = JOptionPane.showInputDialog("Choose difficulty (4,6,8): ");
        int input = Integer.parseInt(tempStr);
        int difficulty = 0;
        switch(input){
            case 4: 
                difficulty = 4;
                break;
            case 6:
                difficulty = 6;
                break;
            case 8:
                difficulty = 8;
                break;
            default: chooseDifficulty();
        }
            return difficulty; 
    }
    
   Hanoi() {
       
       frame = new JFrame("Towers Of Hanoi");
       frame.setLayout(null);
       frame.setMinimumSize(new Dimension(800, 600));
       frame.setResizable(false);    
       frame.setLocationRelativeTo(null);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
       BufferedImage myImage = null;
       try {
           myImage = ImageIO.read(getClass().getResource("/res/bg.png"));
       } catch (IOException ex) {
           Logger.getLogger(Hanoi.class.getName()).log(Level.SEVERE, null, ex);
       }
       frame.setContentPane(new ImagePanel(myImage));
       // LABELS
       movesNum.setBounds( 25,  66, 233,  50);
       Label movesTxt = new Label("MOVES:", null); 
       movesTxt.setBounds( 25,  10, 233,  50);
       Label timerTxt = new Label("TIME:", null);
       timerTxt.setBounds(545,  10, 233,  50);

       frame.add(movesTxt);
       frame.add(movesNum);
       frame.add(timerTxt);

        moves = 0;
        totalDiscs = chooseDifficulty();
        minMoves = (int)((Math.pow(2,totalDiscs))-1);
        Disc[] discArray = new Disc[totalDiscs];
        int width = 25;  //width + factor = size of smallest disk
        int factor = 25;
        for(int i = 0; i < totalDiscs; i++){
            discArray[i] = new Disc(width+factor);
            width += factor; 
            discArray[i].addMouseListener(this);
            discArray[i].addMouseMotionListener(this);
            frame.add(discArray[i]);
        }
        pegArray = new Peg[PEGS];
        for(int i = 0; i < PEGS; i++){
            pegArray[i] = new Peg();
            pegArray[i].setMiddle((i+1)*35 +i*220 + 110);//266 - width of space for one peg
        }
        for(int i = totalDiscs-1; i >-1; i--){
            pegArray[0].addDisc(discArray[i]);  
            discArray[i].setPeg(pegArray[0]);   
        }
        int counter = 0;
        for(int i = totalDiscs-1; i >-1; i--){
            Disc d = discArray[i];
            counter++;
            d.moveDisc(pegArray[0].getMiddle() - (int)d.getWidth()/2, 
                frame.getHeight()- BOTTOM_MARGIN - (int)d.getHeight()*counter); 
                //lowest disk 100 px from the bottom
        }
        DigitalTimer myTimer = new DigitalTimer();
        myTimer.setBounds( 544,66, 233,50);
        frame.add(myTimer);
        //frame.pack();
        frame.setVisible(true);
    }
   
    @Override
    public void mouseEntered(MouseEvent e) {
        // not needed
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // not needed
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startDragX = e.getX();
        startDragY = e.getY();
        Sound.play("res/pick.wav");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       if (inDrag) {
           tryPut(e.getComponent(),e.getComponent().getX(),e.getComponent().getY());
           inDrag = false;
       }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       // not needed
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //if dragged disk is on top of its peg
        Disc d = (Disc)e.getComponent();
        if(d.getPeg().peek().equals(d)){
            int newX = e.getComponent().getX() + (e.getX() - startDragX);
            int newY = e.getComponent().getY() + (e.getY() - startDragY);
            e.getComponent().setLocation(newX, newY);
            inDrag = true;
            
        }
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
       // not needed
    }
 
    /**
     * Establish if a move is a valid one or not
     * @param c Component Disc that user moves
     * @param x,y X,Y-coord at which user is trying to drop disc
     * 
     */
    public void tryPut(Component c, int x, int y){
        Disc d = (Disc)c;
        int i;
       
        //we are dividing the screen into 3 parts (3 pegs)
        //since frame size width is 800 each peg area width is 266
        if(x<266){
            i = 0;   //user is dropping on peg0
        }else if(x<532){
            i = 1;   //user is dropping on peg1
        }else{
            i = 2;   //we drop on peg2
        }
        if(!pegArray[i].isEmpty()){
            Disc onPeg = (Disc)pegArray[i].peek();
            if(d.getDiameter()<=onPeg.getDiameter()){
                put(d, pegArray[i], true);
            }else{
                put(d, d.getPeg(), false);
                            }
        }else{
           put(d, pegArray[i], true);
        }
    }     
      
    /**
     * @param d - Hovering Disc we are interacting with
     * @param p - Peg on which to put hovered disc
     * @param isMove - needed to count moves & not count putting disc back
     */
    public void put(Disc d, Peg p, boolean isMove){
        d.getPeg().removeDisc();//remove disc from the source peg 
        d.setPeg(p);
        p.addDisc(d);           //add disc to target peg
        //move the disc on screen tp top of selected peg
        d.setLocation(p.getMiddle() - (int)d.getDiameter()/2, 
             frame.getHeight() - BOTTOM_MARGIN - 
                (int)d.getHeight()*p.getStackSize());
        if(isMove){  //this is for illegal moves
            moves++;
            movesNum.setText(String.valueOf(moves));
        } else {
        	Sound.play("res/fail.wav");
        }
        Sound.play("res/put.wav");
        checkGameOver();
    }
     
    public void checkGameOver() {
    	//if all discs on last peg == game over
        if(pegArray[2].getDiscs().size() == totalDiscs){
            //gameOverSnd.play();
            try {
            	panelBg = ImageIO.read(getClass().getResource("/res/panel_bg.png"));
                frame.setContentPane(new GameOverPanel(panelBg)); 
            } catch (IOException ex) {
                Logger.getLogger(Hanoi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}




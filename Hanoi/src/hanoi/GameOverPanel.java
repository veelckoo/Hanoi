package hanoi;

import java.awt.Image;
import java.io.IOException;
import javax.swing.JButton;

public class GameOverPanel extends ImagePanel{
	private static final long serialVersionUID = 7687097722694528907L;
	private JButton exitButton;
	private JButton restartButton;
   
    GameOverPanel(Image image) throws IOException{
        
        super(image);
        int min = (int)Math.pow(Hanoi.totalDiscs,2) - 1;       
        Label gameOver = new Label("GAME OVER!", null);  
        gameOver.setBounds( 0,  50, 800, 50);
        
        Label numMovesTxt = new Label("Number of moves: ", null);  
        numMovesTxt.setBounds( 0,  100, 800, 50);
        
        Label numMoves = new Label( String.valueOf(Hanoi.moves), null);  
        numMoves.setBounds( 0,  150, 800, 50);
        
        Label minMovesTxt = new Label("Minimum number of moves with " + 
                Hanoi.totalDiscs + " disks is:", null);  
        minMovesTxt.setBounds( 0,  200, 800, 50);
        
        Label minMoves = new Label( String.valueOf(min), null);  
        minMoves.setBounds( 0,  250, 800, 50);
        
        Label tryHarder = new Label("Next time try harder!", null);  
        tryHarder.setBounds( 0,  300, 800, 50);
        
        Label goodJob = new Label("OPTIMAL SOLUTION !!!", null);  
        goodJob.setBounds( 0,  300, 800, 50);
        
        Label timeTxt = new Label("Your time: ", null);  
        timeTxt.setBounds( 0,  350, 800, 50);
        
        Label time = new Label( DigitalTimer.getTimeText() + " s.", null);  
        time.setBounds( 0,  400, 800, 50);
        
        restartButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        
        
        restartButton.setText("");
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gameOverButtonActionPerformed(evt);
            }
        });

        exitButton.setText("");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/exit.png")));
        exitButton.setBounds(517, 450, 180, 50);
        exitButton.setBorder(null);
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        
        restartButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/restart.png")));
        restartButton.setBounds(133, 450, 180, 50);
        restartButton.setBorder(null);
        restartButton.setOpaque(false);
        restartButton.setContentAreaFilled(false);
        
        this.add(gameOver);
        this.add(numMovesTxt);
        this.add(numMoves);
        this.add(minMovesTxt);
        this.add(minMoves);
        if (Hanoi.moves > min) {
            this.add(tryHarder);
        }else if(Hanoi.moves == min){
            this.add(goodJob);
        }
        this.add(timeTxt);
        this.add(time);
        this.add(exitButton);
        this.add(restartButton);
    }
    
    private void gameOverButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        this.setVisible(false);
        Hanoi.main(null);
    }                                              

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        System.exit(0);
    }
}

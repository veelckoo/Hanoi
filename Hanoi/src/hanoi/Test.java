/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoi;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class Test implements MouseListener, MouseMotionListener {
 
   public static void main(String[] args) {
      new Test();
   }
 
   JLabel sprite = new JLabel("drag me");
   JFrame frame = new JFrame("drag demo");
   JPanel panel = new JPanel();
 
   Test() {
      
      frame.setLayout(null);
      frame.setMinimumSize(new Dimension(500, 500));
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Dimension panelsize = new Dimension(400,400);
      panel.setPreferredSize(panelsize);
      panel.setBackground(Color.WHITE);
      panel.setBounds(30, 10, 400, 400);
      sprite.setBounds(10, 10, 80, 20);
      sprite.addMouseListener(this);
      sprite.addMouseMotionListener(this);
      panel.addMouseListener(this);
      panel.setLayout(null);
      frame.add(sprite);
      frame.add(panel);
 
      frame.setVisible(true);
   }
 
   int startDragX, startDragY;
   boolean inDrag = false;
 
   @Override
   public void mouseEntered(MouseEvent e) {
      // not interested
   }
 
   @Override
   public void mouseExited(MouseEvent e) {
      // not interested
   }
 
   @Override
   public void mousePressed(MouseEvent e) {
      startDragX = e.getX();
      startDragY = e.getY();
   }
 
   @Override
   public void mouseReleased(MouseEvent e) {
      if (inDrag) {
         System.out.println("Sprite dragged to " + sprite.getX() + ", " + sprite.getY());
         inDrag = false;
      }
   }
 
   @Override
   public void mouseClicked(MouseEvent e) {
       if (e.getSource()==panel){
       int clickx = e.getX();
       int clicky = e.getY();
       JLabel gate1 = new JLabel("testing");
       gate1.setBounds(clickx, clicky, 100, 50);
       gate1.addMouseMotionListener(this);
       gate1.addMouseListener(this);

       panel.add(gate1);
       panel.repaint();
       }
   }
 
   @Override
   public void mouseDragged(MouseEvent e) {
      int newX = sprite.getX() + (e.getX() - startDragX);
      int newY = sprite.getY() + (e.getY() - startDragY);
      sprite.setLocation(newX, newY);
      inDrag = true;
   }
 
   @Override
   public void mouseMoved(MouseEvent arg0) {
      // not interested
   }
 
}
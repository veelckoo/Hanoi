package hanoi;

import java.util.Stack;

public class Peg {
    private Stack<Disc> discs;
    private int middle;
    
    public Peg(){
        discs = new Stack<Disc>();
    }
	
    public Stack<Disc> getDiscs(){
        return discs;
    }
	
    public void addDisc(Disc d){
        discs.add(d);
    }
	
    public void removeDisc(){
        discs.pop();
    }
	
    public Object peek(){
        return discs.peek();
    }
	
    public int getStackSize(){
        return discs.size();
    }
	
    public boolean isEmpty(){
        return discs.empty();
    }
	
    public int getMiddle(){
        return middle;
    }
	
    public void setMiddle(int m){
        middle = m;
    }

}

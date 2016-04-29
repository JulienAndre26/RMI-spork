package classserver.deliverable;


public class Counter {
    
    private int count;
    
    public Counter(){
        count = 0;
    }
    
    public synchronized int up() {
        System.out.println("Before : " + count);
        count++;
        System.out.println("After : " + count);
        return count;
    }

}

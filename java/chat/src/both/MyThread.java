/*
 * Created on Sep 21, 2005
 *
 */
package both;

public class MyThread extends Thread {

    protected boolean active;
    public MyThread() {
        super();
        active=true;
    }
    
    
    public void exit(){
        active=false;
        this.interrupt();
    }
}

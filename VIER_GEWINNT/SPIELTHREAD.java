public class SPIELTHREAD implements Runnable
{
    private int size; 
    private int sp1;
    private int sp2;
    String serverIP;
    int hauptport;

    public SPIELTHREAD(int size, int sp1, int sp2, String serverIP, int hauptport) {
        {   
            this.size = size;
            this.sp1 = sp1;
            this.sp2 = sp2;
            this.serverIP = serverIP;
            this.hauptport = hauptport;
        }
    }

    public void run() {
        OBERCONTROLLER controll = new OBERCONTROLLER(size, sp1, sp2, serverIP, hauptport);
    }

}

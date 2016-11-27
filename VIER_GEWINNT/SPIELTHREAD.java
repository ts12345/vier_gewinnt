public class SPIELTHREAD implements Runnable {
    private int size; 
    private int spieler;
    String serverIP;
    int hauptport;

    public SPIELTHREAD(int size, int spieler, String serverIP, int hauptport) {
        {   
            this.size = size;
            this.spieler = spieler;
            this.serverIP = serverIP;
            this.hauptport = hauptport;
        }
    }

    public void run() {
        OBERCONTROLLER controll = new OBERCONTROLLER(size, spieler, serverIP, hauptport);
    }
}

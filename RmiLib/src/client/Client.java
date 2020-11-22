/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.net.MalformedURLException;
import server.Server;
import server.RMIinterface;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author iago-
 */
public abstract class Client implements Runnable{
    
    private RMIinterface rmiInterface;
    private int player;
    
    abstract public void decideFromServerMessage(String msg);
    
    public Client(String host){
        this.locateServer(host);
    }
    
    private void locateServer(String server){
        try {
            this.rmiInterface = (RMIinterface)Naming.lookup("//localhost/"+server);
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }
    }

    
    @Override
    public void run() {
        while(true){
            this.getFromServer();
        }
    }
    
    public void getFromServer(){
        String msg;
        try{
            while( (msg = this.rmiInterface.read(this.player)) != null){
                this.decideFromServerMessage(msg);
                
            }
        }catch(RemoteException e){
            System.out.println("Error: "+e);
        }
    }
    
}

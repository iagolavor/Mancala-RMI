/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author iago-
 */
public class Server extends UnicastRemoteObject implements RMIinterface{
    
    private boolean[] clients = {false, false};
    private int players = 0;
    private ArrayList<String> messages;
    
    public Server() throws RemoteException{
        super();
        this.messages = new ArrayList<String>();
        System.out.println("Servidor criado.");
    }

    @Override
    public void sendToOne(String msg, int player) throws RemoteException {
        this.messages.set(player, msg);
    }

    @Override
    public void sendToAll(String msg) throws RemoteException {
        for(int i=0; i<this.messages.size(); i++){
            this.messages.set(i, msg);
        }
    }

    @Override
    public String read(int player) throws RemoteException {
        String temp;
        int index;
        if(player == 1){
            index = 0;
        }else{
            index = 1;
        }
        synchronized(this.messages){
            while(this.messages.get(index).isBlank()){
                try {
                    this.messages.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            temp = this.messages.get(index);
        }
        this.messages.set(index, "");
        return temp;
    }
    
}

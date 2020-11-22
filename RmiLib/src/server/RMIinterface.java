/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author iago-
 */
public interface RMIinterface extends Remote{
    
    public void sendToOne(String msg, int player) throws RemoteException;
    
    public void sendToAll(String msg) throws RemoteException;
    
    public String read(int player) throws RemoteException;
}

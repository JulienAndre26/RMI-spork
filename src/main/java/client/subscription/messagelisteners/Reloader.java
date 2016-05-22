package client.subscription.messagelisteners;

import client.Client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.rmi.RemoteException;

/**
 * Message listener reloader
 */
public class Reloader implements MessageListener {
    private Client client;
    private String[] tmp;
    private String key, msg;
    public Reloader(Client client) {
        this.client = client;
    }

    @Override
    public void onMessage(Message message) {
        try {
            msg = ((TextMessage) message).getText();
            System.out.println(client.getName() + "> " + msg);
            tmp= msg.split(" ");
            key = tmp[tmp.length - 1];
            client.getDistantObject(key);
            System.out.println(client.getName() + "> Object " + key + " reloaded.");
        } catch (JMSException e) {
            System.out.println("Error in casting message in TextMessage.");
            e.printStackTrace();
        } catch (RemoteException e) {
            System.out.println("Error reloading " + key + ".");
            e.printStackTrace();
        }
    }
}

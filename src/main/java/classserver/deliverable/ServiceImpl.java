package classserver.deliverable;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ServiceImpl extends UnicastRemoteObject implements Service {

    protected ServiceImpl() throws RemoteException
    {
        super();
    }

    private static final long serialVersionUID = -3155557586483036992L;

    @Override
    public Multiplicator createMultiplicator() throws RemoteException
    {
        return new Multiplicator();
    }

    @Override
    public Counter createCounter() throws RemoteException
    {
        return new Counter();
    }

}

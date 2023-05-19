import java.rmi.Remote;
import java.rmi.RemoteException;
public interface RemoteUtil extends Remote{
    public String getName() throws RemoteException;
    public String getAbvName() throws RemoteException;
    public String getDescription() throws RemoteException;
    public double getMarketCap() throws RemoteException;
    public double getTradingVolume() throws RemoteException;
    public double getPrice() throws RemoteException;
    
}
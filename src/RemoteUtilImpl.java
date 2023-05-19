import java.rmi.Remote;
import java.rmi.RemoteException;
public class RemoteUtilImpl implements RemoteUtil{

    public String name;
    public String abvname;
    public String description;
    public double marketcap;
    public double tradingvolume;
    public double price;

    public  RemoteUtilImpl(String name, String abvname, String description, double marketcap, double tradingvolume, double price) throws RemoteException{
        this.name = name;
        this.abvname = abvname;
        this.description = description;
        this.marketcap = marketcap;
        this.tradingvolume = tradingvolume;
        this.price = price;
    }

    public RemoteUtilImpl(String name){
        this.name = name;
    }

    public String getName() throws RemoteException{
        return this.name;
    }

    public String getAbvName() throws RemoteException{
        return this.abvname;
    }

    public String getDescription() throws RemoteException{
        return this.description;
    }

    public double getMarketCap() throws RemoteException{
        return this.marketcap;
    }

    public double getTradingVolume() throws RemoteException{
        return this.tradingvolume;
    }

    public double getPrice() throws RemoteException{
        return this.price;
    }

}
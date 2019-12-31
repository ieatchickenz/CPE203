public class BUY{
    //variables
    private String sessionID;
    private String productID;
    private double price;
    private double quantity;
    private int numSession;


    //constructors
    public BUY(){}

    //set
    public BUY(String sessionID, String productID, double price, double quantity){
	this.sessionID = sessionID;
	this.productID = productID;
	this.price = price;
	this.quantity = quantity;
	this.numSession = 0;
    } 


    //methods
    public String getSessionID(){
        return this.sessionID;
    }

    public String getProductID(){return this.productID;}

    public double getPrice(){return this.price;}

    public double getQuantity(){return this.quantity;}

    public int addSession(){return numSession += 1;}

    public int getSession(){return numSession;}
}

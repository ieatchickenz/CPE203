public class VIEW{
   //variables
   private String sessionId;
   private String productId;
   private double price;


   //constructor 
   //default
   public VIEW(){}
   //set
   public VIEW(String sessionId, String productId, double price){
	this.sessionId = sessionId;
	this.productId = productId;
	this.price = price;
   }

   //methods
   public String getSessionId(){return this.sessionId;}

   public String getProductId(){return this.productId;}

   public double getPrice(){return this.price;}

}

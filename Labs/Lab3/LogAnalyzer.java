import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LogAnalyzer {
   //constants to be used when pulling data out of input
   //leave these here and refer to them to pull out values
   private static final String START_TAG = "START";
   private static final int START_NUM_FIELDS = 3;
   private static final int START_SESSION_ID = 1;
   private static final int START_CUSTOMER_ID = 2;
   private static final String BUY_TAG = "BUY";
   private static final int BUY_NUM_FIELDS = 5;
   private static final int BUY_SESSION_ID = 1;
   private static final int BUY_PRODUCT_ID = 2;
   private static final int BUY_PRICE = 3;
   private static final int BUY_QUANTITY = 4;
   private static final String VIEW_TAG = "VIEW";
   private static final int VIEW_NUM_FIELDS = 4;
   private static final int VIEW_SESSION_ID = 1;
   private static final int VIEW_PRODUCT_ID = 2;
   private static final int VIEW_PRICE = 3;
   private static final String END_TAG = "END";
   private static final int END_NUM_FIELDS = 2;
   private static final int END_SESSION_ID = 1;

   //a good example of what you will need to do next
   //creates a map of sessions to customer ids
   private static void processStartEntry(
           final String[] words,
           final Map<String, List<String>> sessionsFromCustomer) {
      if (words.length != START_NUM_FIELDS) {
         return;
      }

      //check if there already is a list entry in the map
      //for this customer, if not create one
      List<String> sessions = sessionsFromCustomer
              .get(words[START_CUSTOMER_ID]);
      if (sessions == null) {
         sessions = new LinkedList<>();
         sessionsFromCustomer.put(words[START_CUSTOMER_ID], sessions);
      }

      //now that we know there is a list, add the current session
      sessions.add(words[START_SESSION_ID]);
   }

   //similar to processStartEntry, should store relevant view
   //data in a map - model on processStartEntry, but store
   //your data to represent a view in the map (not a list of strings)
   private static void processViewEntry(
           final String[] words,
           final Map<String, List<VIEW>> viewsFromSessions) {
      if (words.length != VIEW_NUM_FIELDS) {
         return;
      }

      List<VIEW> views = viewsFromSessions.get(words[VIEW_SESSION_ID]);
      if (views == null) {
         views = new LinkedList<>();
         viewsFromSessions.put(words[VIEW_SESSION_ID], views);
      }
      VIEW view = new VIEW(words[VIEW_SESSION_ID], words[VIEW_PRODUCT_ID], new Double(words[VIEW_PRICE]));
      views.add(view);
   }

   //similar to processStartEntry, should store relevant purchases
   //data in a map - model on processStartEntry, but store
   //your data to represent a purchase in the map (not a list of strings)
   private static void processBuyEntry(
           final String[] words,
           final Map<String, List<BUY>> buysFromSessions
   ) {

      if (words.length != BUY_NUM_FIELDS) {
         return;
      }

      List<BUY> buys = buysFromSessions.get(words[BUY_SESSION_ID]);
      if (buys == null) {
         buys = new LinkedList<>();
         buysFromSessions.put(words[BUY_SESSION_ID], buys);
      }
      BUY buy = new BUY(words[BUY_SESSION_ID], words[BUY_PRODUCT_ID], new Double(words[BUY_PRICE]), new Double(words[BUY_QUANTITY]));
      buys.add(buy);
   }

   private static void processEndEntry(final String[] words, final Map<String, List<String>> sessionsFromEnd) {
      if (words.length != END_NUM_FIELDS) {
         return;
      }

      List<String> ends = sessionsFromEnd.get(words[END_SESSION_ID]);
      if (ends == null) {
         ends = new LinkedList<>();
         sessionsFromEnd.put(words[END_SESSION_ID], ends);
      }

      //now that we know there is a list, add the current session
      ends.add(words[END_SESSION_ID]);
   }

   //this is called by processFile below - its main purpose is
   //to process the data using the methods you write above
   private static void processLine(
           final String line,
           final Map<String, List<String>> sessionsFromCustomer,
           final Map<String, List<VIEW>> viewsFromSessions,
           final Map<String, List<BUY>> buysFromSessions,
           final Map<String, List<String>> sessionsFromEnd
           /* add parameters as needed */
   ) {
      final String[] words = line.split("\\h");

      if (words.length == 0) {
         return;
      }

      switch (words[0]) {
         case START_TAG:
            processStartEntry(words, sessionsFromCustomer);
            break;
         case VIEW_TAG:
            processViewEntry(words, viewsFromSessions /* add arguments as needed */);
            break;
         case BUY_TAG:
            processBuyEntry(words, buysFromSessions /* add arguments as needed */);
            break;
         case END_TAG:
            processEndEntry(words, sessionsFromEnd /* add arguments as needed */);
            break;
      }
   }

   //write this after you have figured out how to store your data
   //make sure that you understand the problem
   private static void printSessionPriceDifference(
           final Map<String, List<String>> sessionsFromCustomer,
           final Map<String, List<VIEW>> viewsFromSession,
           final Map<String, List<BUY>> buysFromSession
           /* add parameters as needed */
   ) {

      System.out.println("Price Difference for Purchased Product by Session");

      for (Map.Entry<String, List<String>> cust : sessionsFromCustomer.entrySet()) {
         List<String> sess = cust.getValue();
         for (String s : sess) {
            double view_tot = 0;
            int VIEW_COUNT = 0;
            List<VIEW> views = viewsFromSession.get(s);
            List<BUY> buys = buysFromSession.get(s);
            if(views != null) {
                for (VIEW view : views) {
                    view_tot += view.getPrice();
                    VIEW_COUNT += 1;

                }
            }
            if (buys != null){
               System.out.println(s);
               double avg = view_tot/VIEW_COUNT;
               for (BUY buy : buys){
                  System.out.println("\t" + buy.getProductID() + " " + (buy.getPrice()-avg));
               }
            }
         }
         }
   }

   //write this after you have figured out how to store your data
   //make sure that you understand the problem
   private static void printCustomerItemViewsForPurchase(
           final Map<String, List<String>> sessionsFromCustomer,
           final Map<String, List<VIEW>> viewsFromSession,
           final Map<String, List<BUY>> buysFromSession
           /* add parameters as needed */
   ) {
      System.out.println("Number of Views for Purchased Product by Customer");

      for (Map.Entry<String, List<String>> cust : sessionsFromCustomer.entrySet()) {

         System.out.println(cust.getKey());
         List<BUY> buy_master = new ArrayList<BUY>();
         List<String> sess = cust.getValue();
         List<String> sess1 = cust.getValue();
         for (String s1 : sess1) {
            List<BUY> buys = buysFromSession.get(s1);
            if (buys != null) {
               for (BUY temp2 : buys) {
                  buy_master.add(temp2);
               }
            }
         }
               for (String s : sess) {
                  List<VIEW> views = viewsFromSession.get(s);
                  for (BUY buy : buy_master) {
                      if(views != null) {
                          for (VIEW view : views) {
                              if (buy.getProductID().equals(view.getProductId())) {
                                  buy.addSession();
                                  break;
                              }
                          }
                      }
                  }
               }
               for (BUY buyers : buy_master){
                  System.out.println("\t" + buyers.getProductID() + " " + buyers.getSession());
               }
            }
         }

   //write this after you have figured out how to store your data
   //make sure that you understand the problem
   private static void printStatistics(
           final Map<String, List<String>> sessionsFromCustomer,
           final Map<String, List<VIEW>> viewsFromSessions,
           final Map<String, List<BUY>> buysFromSessions,
           final Map<String, List<String>> sessionsFromEnd
           /* add parameters as needed */
   ) {
      printOutExample(sessionsFromCustomer, viewsFromSessions, buysFromSessions);
      System.out.println();
      printSessionPriceDifference(sessionsFromCustomer, viewsFromSessions, buysFromSessions /*add arguments as needed */);
      System.out.println();
      printCustomerItemViewsForPurchase(sessionsFromCustomer, viewsFromSessions, buysFromSessions);

      /* This is commented out as it will not work until you read
         in your data to appropriate data structures, but is included
         to help guide your work - it is an example of printing the
         data once propogated
       */

   }

   /* provided as an example of a method that might traverse your
      collections of data once they are written 
      commented out as the classes do not exist yet - write them! */

   private static void printOutExample(
           final Map<String, List<String>> sessionsFromCustomer,
           final Map<String, List<VIEW>> viewsFromSession,
           final Map<String, List<BUY>> buysFromSession) {
      //for each customer, get their sessions
      //for each session compute views
      double totNumSession = 0;
      double numViews = 0;
      double numCustomer = 0;

      for (Map.Entry<String, List<String>> cust : sessionsFromCustomer.entrySet()) {
         numCustomer += 1;
         List<BUY> buy_master = new ArrayList<BUY>();
         List<String> sess = cust.getValue();
         for (String s : sess) {
            List<VIEW> views = viewsFromSession.get(s);
            List<BUY> buys = buysFromSession.get(s);
            if (buys != null) {
               for (BUY temp : buys) {
                  buy_master.add(temp);
               }
               for (VIEW view : views) {
                  for (BUY buy : buy_master) {
                     String prod = buy.getProductID();
                     if (prod.equals(view.getProductId())) {
                        numViews += 0;
                     } else {
                        numViews += 1;
                     }
                  }
               }
            }
         }

      }

      double tot = numViews / numCustomer;
      System.out.println("Average Views without Purchase: " + tot);

   }


      //called in populateDataStructures
   private static void processFile(
      final Scanner input,
      final Map<String, List<String>> sessionsFromCustomer,
      final Map<String, List<VIEW>> viewsFromSessions,
      final Map<String, List<BUY>> buysFromSessions,
      final Map<String, List<String>> sessionsFromEnd
      /* add parameters as needed */
      )
   {
      while (input.hasNextLine())
      {
         processLine(input.nextLine(), sessionsFromCustomer, viewsFromSessions, buysFromSessions, sessionsFromEnd
            /* add arguments as needed */ );
      }
   }

      //called from main - mostly just pass through important data structures
   private static void populateDataStructures(
      final String filename,
      final Map<String, List<String>> sessionsFromCustomer,
      final Map<String, List<VIEW>> viewsFromSessions,
      final Map<String, List<BUY>> buysFromSessions,
      final Map<String, List<String>> sessionsFromEnd
      /* add parameters as needed */
      )
      throws FileNotFoundException
   {
      try (Scanner input = new Scanner(new File(filename)))
      {
         processFile(input, sessionsFromCustomer, viewsFromSessions, buysFromSessions, sessionsFromEnd
                 /* add arguments as needed */ );
      }
   }

   private static String getFilename(String[] args)
   {
      if (args.length < 1)
      {
         System.err.println("Log file not specified.");
         System.exit(1);
      }

      return args[0];
   }

   public static void main(String[] args)
   {
      /* Map from a customer id to a list of session ids associated with
       * that customer.
       */
      final Map<String, List<String>> sessionsFromCustomer = new HashMap<>();
      final Map<String, List<VIEW>> viewsFromSessions = new HashMap<>();
      final Map<String, List<BUY>> buysFromSessions = new HashMap<>();
      final Map<String, List<String>> sessionsFromEnd = new HashMap<>();

      /* create additional data structures to hold relevant information */
      /* they will most likely be maps to important data in the logs */

      final String filename = getFilename(args);

      try
      {
         populateDataStructures(filename, sessionsFromCustomer, viewsFromSessions, buysFromSessions, sessionsFromEnd
            /* add parameters as needed */
            );
         printStatistics(
             sessionsFromCustomer, viewsFromSessions, buysFromSessions, sessionsFromEnd
            /* add parameters as needed */
            );
      }
      catch (FileNotFoundException e)
      {
         System.err.println(e.getMessage());
      }
   }
}

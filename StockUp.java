import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import utility.*;
import objects.*;

public class StockUp {
    private static Scanner input = new Scanner(System.in);
    private static Ink ink = new Ink();
    private static Validator validator = new Validator();
    private static Market market = new Market();
    // private static Portfolio portfolio = new Portfolio(); why did this work?
    private static Portfolio portfolio;

    private static int min = 1;
    private static int max = 8;
    private static boolean isDone = false;
    private static boolean goBack = false;


    public static void main(String[] args) {

        ink.printWelcome();

        System.out.println("How much money is your initial deposit? \n");
        double initialDeposit = validator.depositValidation(ink, input);
        portfolio = new Portfolio(initialDeposit);
        ink.printInitialDeposit(initialDeposit);
        
        seedStocks();
        portfolio.updateStockBalance();
        seedMarket();

        ink.printBalances(portfolio.getNetworth(), 
            portfolio.getCashBalance(), 
            portfolio.getStockBalance());

        while(!isDone) { // can also say while(isDone == false)
            int choice = validator.selValidation(ink, input, min, max); // pass in the opjects ink, input etc

            switch(choice) {
                case 1: // print Portfolio
                    ink.printPortfolio(portfolio.getStocks());
                    ink.printBalances(portfolio.getNetworth(), 
                        portfolio.getCashBalance(), 
                        portfolio.getStockBalance());
                    break;
                
                case 2: // market data
                    ink.printMarket(market.getStocks());
                    goBack = !goBack;
                    break;
                
                case 3: // buy stock
                    ink.printMarket(market.getStocks());
                    System.out.println("\nWhich stock would you like to buy?: ");

                    int buyIdx = input.nextInt();
                    Stock stock = market.getStock(buyIdx - 1);
                    ink.printBuyStock(stock);
                    int buyQTY = input.nextInt();
                    System.out.println("Purchasing " + buyQTY + " unit(s) of " + stock.getName());
                    boolean issuccess = market.buyStocks(stock, buyQTY, portfolio.getCashBalance());
                    if(issuccess) {
                        double purchaseAmount = stock.getPrice() * buyQTY;
                        portfolio.buyStock(stock, buyQTY, purchaseAmount);
                        ink.printPortfolio(portfolio.getStocks());
                        ink.printBalances(portfolio.getNetworth(), 
                        portfolio.getCashBalance(), 
                        portfolio.getStockBalance());
                    }
                    else {
                        System.out.println("\nFailed to buy stock. Insufficient funds.\n");
                    }   
                    break;

                case 4: // sell stock
                    if (portfolio.getStocks().isEmpty()) {
                        System.out.println("You have no stocks to sell.");
                    }
                    else{    
                        System.out.println("Choose a stock to sell: \n");
                        ink.printPortfolio(portfolio.getStocks());


                        int sellIdx = input.nextInt();
                        Stock sellStock = portfolio.getStock(sellIdx - 1);
                        ink.printSellStock(sellStock);
                        int qty = input.nextInt();
                        if (qty <= sellStock.getQty()) {
                            portfolio.sellStock(sellStock, qty);
                            portfolio.getStockBalance();

                            System.out.println("The stock has been sold, you're cash balance has been updated");
                            ink.printBalances(portfolio.getNetworth(), 
                                portfolio.getCashBalance(), 
                                portfolio.getStockBalance());
                            ink.printPortfolio(portfolio.getStocks());
                        }
                        else {
                            System.out.println("Insufficient quantity to sell");
                        }
                    }
                    break;
                    
                case 5: // add funds
                    double amount = validator.fundValidation(ink, input, portfolio.getCashBalance());
                    portfolio.addFunds(amount);
                    // print the new balance
                    System.out.printf("New Cash Balance: $%.2f\n", portfolio.getCashBalance());
                    break;

                case 6: // withdrawal funds
                    amount = validator.withdrawalValidation(ink, input, portfolio.getCashBalance());
                    portfolio.withdrawFunds(amount);
                    // print the new balance
                    System.out.printf("New Cash Balance: $%.2f\n", portfolio.getCashBalance());
                    break;
                
                case 7: // close account
                    portfolio.closeAccount();
                    System.out.println("Your account has been closed. All stocks have been sold.");
                    ink.printBalances(portfolio.getNetworth(), 
                        portfolio.getCashBalance(), 
                        portfolio.getStockBalance());
                    break;

                case 8: // exit
                    isDone = !isDone;
                    break;
            } // switch  
        } // while
         // resets goBack to false, toggling back to main state
        ink.printGoodbye();
    } // main

    public static void seedStocks() {
        // the purpose is to create some test stocks
        Stock stock = new Stock("Microsoft", "MSFT", 420, 100);
        portfolio.addStock(stock);
        stock = new Stock("Uber", "UBR", 120, 50);
        portfolio.addStock(stock);
        stock = new Stock("Nvidia", "NVD", 900, 90);
        portfolio.addStock(stock);
    } // seedStocks

    public static double randomPrice(double minPrice, double maxPrice) {
        Random rand = new Random();
        return minPrice + (maxPrice - minPrice) * rand.nextDouble();
    }

    public static void seedMarket() {
        ArrayList<Stock> stocks = new ArrayList<>();
        // the purpose is to create some TEST stocks for the Market
        Stock stock = new Stock("Adobe", "ADB", randomPrice(18, 22), 0);
        stocks.add(stock);
        stock = new Stock("Netflix", "NFX", randomPrice(118, 122), 0);
        stocks.add(stock);
        stock = new Stock("Apple", "APL", randomPrice(245, 255), 0);
        stocks.add(stock);
        stock = new Stock("Disney", "MOUSE", randomPrice(1200, 1300), 0);
        stocks.add(stock);
        stock = new Stock("Microsoft", "MSFT", randomPrice(415, 425), 0);
        stocks.add(stock);
        stock = new Stock("Uber", "UBR", randomPrice(100, 130), 0);
        stocks.add(stock);
        stock = new Stock("Nvidia", "NVD", randomPrice(850, 950), 0);
        stocks.add(stock);
        market.setStocks(stocks);
    }
} // class

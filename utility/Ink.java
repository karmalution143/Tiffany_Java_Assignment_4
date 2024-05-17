package utility;
import objects.*;
import java.util.ArrayList;
import java.util.Comparator;

public class Ink {

    public Ink() {
        // do nothing
    }

    public void printWelcome() {
        System.out.println("Welcome to StockUp beta ***\n");
    }

    public void printGoodbye() {
        System.out.println("Richer Every Day!");
    }

    public void printInitialDeposit(double initialDeposit) {
        System.out.printf("Initial Deposit: $%.2f\n", initialDeposit);
    }

    public void printBalances(double networth, double cashBalance, double stockBalance) {
        System.out.printf("\nNetworth: $%.2f\n", networth);
        System.out.printf("Bank balance: $%.2f\n", cashBalance);
        System.out.printf("Stock Value: $%.2f\n", stockBalance);
    }

    public void printBuyStock(Stock stock) {
        System.out.printf("Name: %s\nSymbol: %s\nPrice: $%.2f \n",
        stock.getName(), stock.getSymbol(), stock.getPrice());
        System.out.println("\nHow many units of this stock would you like to buy? \n");
    }
    public void printSellStock(Stock stock) {
        System.out.printf("Name: %s\nSymbol: %s\nPrice: $%.2f\n",
        stock.getName(), stock.getSymbol(), stock.getPrice());
        System.out.println("\nHow many units of this stock would you like to sell?");
    }

    public void printPortfolio(ArrayList<Stock> stocks) {
        stocks.sort(Comparator.comparing(Stock::getName));
        for(int i = 0; i < stocks.size(); i++) {
            System.out.printf("(%d) Name: %-10s Symbol: %-5s Price: $%7.2f Qty: %d\n",
            i + 1,
            stocks.get(i).getName(),
            stocks.get(i).getSymbol(),
            stocks.get(i).getPrice(),
            stocks.get(i).getQty());
        }
    }

    public void printMarket(ArrayList<Stock> stocks) {
        stocks.sort(Comparator.comparing(Stock::getName));
        for(int i = 0; i < stocks.size(); i++) {
            System.out.printf("\n(%d) Name: %-10s Symbol: %-5s Price: $%7.2f\n",
            i + 1,
            stocks.get(i).getName(),
            stocks.get(i).getSymbol(),
            stocks.get(i).getPrice());
        }
    } // for

    public void printMenu() {
        System.out.println("\n(1) Portfolio");
        System.out.println("(2) Market Data");
        System.out.println("(3) Buy Stock");
        System.out.println("(4) Sell Stock");
        System.out.println("(5) Deposit Funds");
        System.out.println("(6) Withdraw Funds");
        System.out.println("(7) Close Account");
        System.out.println("(8) Exit");
    }

    public void printStockDetail(Stock stock) {
        System.out.printf("Name: %s Symbol: %s Price: %d Qty: %d",
            stock.getName(), stock.getSymbol(),
            stock.getPrice(), stock.getQty());
    }

    public void printAddFunds(double cashBalance) {
        System.out.printf("Current cash balance: $%.2f\nAmount to add?: ",
        cashBalance);
    }

    public void printWithdrawalFunds(double cashBalance) {
        System.out.printf("Current cash balance: $%.2f\nAmount to withdraw?: ",
        cashBalance);
    }
} // class
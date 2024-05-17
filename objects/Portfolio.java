package objects;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Portfolio {

    private Calendar openDate = Calendar.getInstance();
    private Calendar closeDate = Calendar.getInstance();
    private double cashBalance;
    private double stockBalance;
    private double networth;
    
    // composition
    private ArrayList<Stock> stocks = new ArrayList<>(); //putting one other object inside the class makes it a composition

    public Portfolio(double deposit) {
        this.cashBalance = deposit;
    } // constructor

    // GETTERS
    public String getOpenDate() {
        return this.openDate.toString();
    }
    public String getCloseDate() {
        return this.closeDate.toString();
    }
    public double getCashBalance() {
        return this.cashBalance;
    }
    public double getStockBalance() {
        updateStockBalance();
        return this.stockBalance;
    }
    public double getNetworth() {
        return calculateStockBalance() + cashBalance;
    }
    public Stock getStock(int idx) {
        return stocks.get(idx);
      }
    public ArrayList<Stock> getStocks() {
        return this.stocks;
    }

    // SETTERS
    public void setCloseDate() {
        this.closeDate = Calendar.getInstance();
    }
    public void addFunds(double amount) {
        if(amount > 0)
            this.cashBalance += amount;
    }
    public void withdrawFunds(double amount) {
        if(amount > 0)
            this.cashBalance -= amount;
    }
    
    //============>>
    // STOCKS
    public void addStock (Stock stock) {
        stocks.add(stock);
    }
    public void removeStock(Stock stock) {
        stocks.remove(stock);
    }
    public void updateStockBalance() {
        this.stockBalance = calculateStockBalance();
    }
    public void updateNetworth() {
        this.networth = getStockBalance() + getCashBalance();
    }
    public void buyStock(Stock stock, int qty, double purchaseAmount) {
        // take the purchaseAmount out of our balance
        this.cashBalance -= purchaseAmount;
    
        boolean stockExists = false;
        for (Stock existingStock : stocks) {
            if (existingStock.getSymbol().equals(stock.getSymbol())) {
                existingStock.setQty(existingStock.getQty() + qty);
                stockExists = !stockExists;
                break;
            }
        }
        if (!stockExists) {
            stock.setQty(qty);
            stocks.add(stock);
        }
        updateStockBalance();
        updateNetworth();
    }
    public void sellStock(Stock stock, int qty) {
        if(qty > 0 && qty <= stock.getQty()) {
            double price = stock.getPrice();
            double saleAmount = price * qty;
            this.addFunds(saleAmount);
            stock.setQty(stock.getQty() - qty);
            if (stock.getQty() == 0) {
                stocks.remove(stock);
                updateStockBalance();
                updateNetworth();
            }
        }
        else {
            System.out.println("Invalid quantity to sell");
        }
    }

    public void closeAccount() {
        Iterator<Stock> iterator = stocks.iterator();
        while (iterator.hasNext()) {
            Stock stock = iterator.next();
            int qty = stock.getQty();
            if (qty > 0) {
                iterator.remove(); // Safe removal using iterator
                sellStock(stock, qty);
            }
        }
    }
    //============>>
    // HELPERS

    private double calculateStockBalance() {
        double totalValue = 0.0;
        for (Stock stock : stocks) {
            totalValue += stock.getPrice() * stock.getQty();
        }
        return totalValue;
    }

} // class

// plural form - stocks - it is a collection
// singular form - stock - it is not a collection

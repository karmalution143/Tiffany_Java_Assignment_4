package objects;

public class Stock {

    private String name;
    private String symbol;
    private double price;
    private int qty;

    public Stock(String name, String symbol, double price, int qty) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.qty = qty;
    }

    // ============>>
    // GETTERS
    public String getName() {
        return this.name;
    }
    public String getSymbol() {
        return this.symbol;
    }
    public double getPrice() {
        return this.price;
    }
    public int getQty() {
        return this.qty;
    }

    //============>>
    // SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
} // class
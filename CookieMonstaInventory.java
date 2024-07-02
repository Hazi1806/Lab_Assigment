/**
 * Cookie Monsta Inventory
 *
 * Programmer: Fiqar, Haziq, Syaheer, Aqil, Irsan
 * Date: 27 June 2024
 */
//must have track inventory levels, manage stock, generate report

import javax.swing.JOptionPane;
public class CookieMonstaInventory extends MainFrame
{
    //instance variable
    private String itemName;
    private String levelCategory;
    private double price;
    private int quantity;
    
    public CookieMonstaInventory()//const without parameter
    {
        this.itemName = null;
        this.levelCategory = null;
        this.price = 0.0;
        this.quantity = 0;
    }
    
    public CookieMonstaInventory( String itemName, String levelCategory, double price, int quantity )//const with parameter
    {
        this.itemName = itemName;
        this.levelCategory = levelCategory;
        this.price = price;
        this.quantity = quantity;        
    }
    
    public void setItemDetails( String itemName, String levelCategory, double price, int quantity )//mutator with parameter
    {
        this.itemName = itemName;
        this.levelCategory = levelCategory;
        this.price = price;
        this.quantity = quantity;        
    }
    //mutator: set
    public void setItemName( String itemName ) 
    {
        this.itemName = itemName;
    }
    
    public void levelCategory( String levelCategory )
    {
        this.levelCategory = levelCategory;
    }
    
    public void price( double price )
    {
        this.price = price;
    }
    
    public void quantity( int quantity )
    {
        this.quantity = quantity;
    }
    //mutator: get
    public String getItemName()
    {
        return itemName;
    }
    
    public String getlevelCategory()
    {
        return levelCategory;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    //method add stock
    public void addNewStock( int amount)
    {
        this.quantity += amount;
    }
    //method renew stock
    public void deductedStock( int amount )
    {
        try
        {
            if ( amount <= 0)
            {
                throw new IllegalArgumentException(" Invalid decuction amount! ");
            }
            this.quantity -= amount;
        }
        catch ( IllegalArgumentException e)
        {
           JOptionPane.showMessageDialog( null, "Issues regarding deduct stock: " + e.getMessage()); 
        }
    }
    //method to calc
    public double calculateTotalValue()
    {
        return this.price * this.quantity;
    }
    //method to display
    public String displayDetails() 
    {
        return "Name: " + itemName + 
                ", \nCategory: " + levelCategory +
                ", \nPrice: RM" + price + 
                ", \nQuantity: " + quantity + 
                ", \nTotal Value: " + calculateTotalValue();
    }

    public static void main(String[] args) 
    {
        CookieMonstaInventory product = new CookieMonstaInventory();
        System.out.println(product.displayDetails());
    }
}
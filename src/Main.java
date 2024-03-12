import java.util.ArrayList;
//item class
class Item {
    private String name;
    private double price;
    //item method
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
//item order class
class ItemOrder {
    private Item item;
    private int quantity;
//item order method
    public ItemOrder(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double calculateTotal() {
        return item.getPrice() * quantity;
    }
}
//shopping cart class
class ShoppingCart {
    private ArrayList<ItemOrder> cart;
    //shopping cart method
    public ShoppingCart() {
        cart = new ArrayList<>();
    }

    public void addItemOrder(ItemOrder itemOrder) {
        cart.add(itemOrder);
    }

    public void removeItemOrder(ItemOrder itemOrder) {
        cart.remove(itemOrder);
    }
    //set up item order
    public ItemOrder searchItemOrder(String itemName) {
        for (ItemOrder itemOrder : cart) {
            if (itemOrder.getItem().getName().equals(itemName)) {
                return itemOrder;
            }
        }
        return null;
    }
    //calculate total price with double value
    public double calculateTotalPrice() {
        double total = 0;
        for (ItemOrder itemOrder : cart) {
            total += itemOrder.calculateTotal();
        }
        return total;
    }

    public ArrayList<ItemOrder> getCart() {
        return cart;
    }
}



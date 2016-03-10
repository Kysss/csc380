
 //* @author Yingying Xia
public class LinkedList {

    private String name;
    private Restaurant restaurant;
    private LinkedList next;

    LinkedList(String key, Restaurant value) {
        this.name = key;
        this.restaurant = value;
        this.next = null;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant value) {
        this.restaurant = value;
    }

    public String getResName() {
        return name;
    }

    public LinkedList getNext() {
        return next;
    }

    public void setNext(LinkedList next) {
        this.next = next;
    }
}

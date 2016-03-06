
 //* @author Yingying Xia
public class LinkedList {

    private String key;
    private Restaurant value;
    private LinkedList next;

    LinkedList(String key, Restaurant value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    public Restaurant getValue() {
        return value;
    }

    public void setValue(Restaurant value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public LinkedList getNext() {
        return next;
    }

    public void setNext(LinkedList next) {
        this.next = next;
    }
}

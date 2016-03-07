
import java.util.Set;
import java.util.HashSet;

//@author Yingying Xia
public class HashMap {

    private final static int capacity = 512;
    LinkedList[] table;
    private Set<String> keySet = new HashSet();

    HashMap() {
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = null;
        }
    }

    public Restaurant get(String name) {

        int hash = name.hashCode();

        if (table[hash] == null) {
            return null;
        } else {
            LinkedList LinkedEntry = table[hash];
            while (LinkedEntry != null && !LinkedEntry.getResName().equals(name)) {
                LinkedEntry = LinkedEntry.getNext();
            }
            if (LinkedEntry == null) {
                return null;
            } else {
                return LinkedEntry.getRestaurant();
            }
        }
    }

    //put and update
    public void put(String name, Restaurant restaurant) {

        int hash = name.hashCode();
        if (table[hash] == null) {
            table[hash] = new LinkedList(name, restaurant);
            keySet.add(name);

        } else {
            LinkedList entry = table[hash];
            while (entry.getNext() != null && entry.getResName() != name) {
                entry = entry.getNext();
            }

            if (entry.getResName().equals(name)) {
               return; //do nothing
            } else {
                LinkedList newkey = new LinkedList(name, restaurant);

                entry.setNext(newkey);
                keySet.add(name);

            }
        }
    }

    public void remove(String name) {
        int hash = name.hashCode();

        if (table[hash] != null) {
            LinkedList prevEntry = null;
            LinkedList entry = table[hash];
            while (entry.getNext() != null && !entry.getResName().equals(name)) {
                prevEntry = entry;
                entry = entry.getNext();
            }
            if (entry.getResName().equals(name)) {
                if (prevEntry == null) {
                    table[hash] = entry.getNext();
                } else {

                    prevEntry.setNext(entry.getNext());
                }
            }
        }
    }

    public Set<String> getAllKeys() {
        return keySet;
    }

    public boolean contains(String name) {
        int hash = name.hashCode();

        if (table[hash] == null) {
            return false;
        } else {
            LinkedList entry = table[hash];
            while (entry.getNext() != null && !entry.getResName().equals(name)) {
                entry = entry.getNext();
            }
            if (entry.getResName().equals(name)) {
                return true;
            } else {
                return false;
            }
        }
    }

}

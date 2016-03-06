
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

    public Restaurant get(String key) {

        int hash = key.hashCode();

        if (table[hash] == null) {
            return null;
        } else {
            LinkedList LinkedEntry = table[hash];
            while (LinkedEntry != null && !LinkedEntry.getKey().equals(key)) {
                LinkedEntry = LinkedEntry.getNext();
            }
            if (LinkedEntry == null) {
                return null;
            } else {
                return LinkedEntry.getValue();
            }
        }
    }

    //put and update
    public void put(String key, Restaurant value) {

        int hash = key.hashCode();
        if (table[hash] == null) {
            table[hash] = new LinkedList(key, value);
            keySet.add(key);

        } else {
            LinkedList entry = table[hash];
            while (entry.getNext() != null && entry.getKey() != key) {
                entry = entry.getNext();
            }

            if (entry.getKey().equals(key)) {
               return; //do nothing
            } else {
                LinkedList newkey = new LinkedList(key, value);

                entry.setNext(newkey);
                keySet.add(key);

            }
        }
    }

    public void remove(String key) {
        int hash = 0;
        char ch[];
        ch = key.toCharArray();
        int keylength = key.length();
        int i, sum;
        for (sum = 0, i = 0; i < keylength; i++) {
            sum += ch[i];
            hash = sum % capacity;
        }

        if (table[hash] != null) {
            LinkedList prevEntry = null;
            LinkedList entry = table[hash];
            while (entry.getNext() != null && !entry.getKey().equals(key)) {
                prevEntry = entry;
                entry = entry.getNext();
            }
            if (entry.getKey().equals(key)) {
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

    public boolean contains(String key) {
        int hash = 0;
        char ch[];
        ch = key.toCharArray();
        int keylength = key.length();
        int i, sum;
        for (sum = 0, i = 0; i < keylength; i++) {
            sum += ch[i];
            hash = sum % capacity;
        }

        if (table[hash] == null) {
            return false;
        } else {
            LinkedList entry = table[hash];
            while (entry.getNext() != null && !entry.getKey().equals(key)) {
                entry = entry.getNext();
            }
            if (entry.getKey().equals(key)) {
                return true;
            } else {
                return false;
            }
        }
    }

}

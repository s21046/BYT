import javax.management.AttributeNotFoundException;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;

public class UniqueIDGenerator<K> {
    TreeMap<Integer,K> idList;
    TreeSet<Integer> idPool;
    int naturalOrder;

    public UniqueIDGenerator(){
        idPool = new TreeSet<>();
        idList = new TreeMap<>();
        naturalOrder = 0;
        idPool.add(naturalOrder);
    }

    public int generateId(K newObject){
        if(idList.containsValue(newObject)) throw new KeyAlreadyExistsException("Object already has an id");
        int id = idPool.first();
        idList.put(id,newObject);
        idPool.remove(id);

        checkForNaturalOrder(id);

        return id;
    }

    public int getObjectsId(K targetObject) throws AttributeNotFoundException {
        for (Map.Entry<Integer, K> entry : idList.entrySet()){
            if(entry.getValue()==targetObject) return entry.getKey();
        }
        throw new AttributeNotFoundException("There is no id for this object");
    }

    private void checkForNaturalOrder(int id){
        if(id == naturalOrder) {
            naturalOrder = getNextFreeId(naturalOrder);
            idPool.add(naturalOrder);
        }
    }

    private int getNextFreeId(int id){
        int nextId = ++id;
        if(idList.get(nextId)!=null) nextId = getNextFreeId(nextId);
        return nextId;
    }

    public void setId(int previousId,int targetId){
        if(idList.get(previousId) == null) throw new NoSuchElementException("No object with such id.");
        else if(idList.get(targetId) != null) throw new KeyAlreadyExistsException("Object with this id already exists.");
        else {
            K targetObject = idList.get(previousId);
            idList.remove(previousId);
            idPool.add(previousId);
            idList.put(targetId,targetObject);
            checkForNaturalOrder(targetId);
        }
    }
}


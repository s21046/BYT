import ApplicationExceptions.ValueAlreadyExistsException;

import javax.management.AttributeNotFoundException;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.*;

public class UniqueIdGenerator<K> {
    TreeMap<Integer,K> idList;
    TreeSet<Integer> idPool;
    int naturalOrder;

    public UniqueIdGenerator(){
        idPool = new TreeSet<>();
        idList = new TreeMap<>();
        naturalOrder = 0;
        idPool.add(naturalOrder);
    }

    /**
     * Generates,stores and returns an id for the newObject
     * @param newObject Object for which the id is generated
     * @return id generated for the object
     * @throws KeyAlreadyExistsException Object already has an id assigned to it in this generator
     */
    public int generateId(K newObject) throws ValueAlreadyExistsException {
        if(idList.containsValue(newObject)) throw new ValueAlreadyExistsException("Object already has an id");
        int id = idPool.first();
        idList.put(id,newObject);
        idPool.remove(id);

        checkForNaturalOrder(id);

        return id;
    }

    /**
     * Method returns id  stored in this generator belonging to the targetObject.
     * If targetObject has no id stored an exception is thrown.
     * @param targetObject Object for which the id should be returned
     * @return id of the target object
     * @throws AttributeNotFoundException Object has no id assigned to it in this generator
     */
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

    /**
     * Overrides id of the object with a new one.
     * @param previousId Id of target object
     * @param targetId New id for target object
     * @return targetId
     * @throws NoSuchElementException If there is no object with such id
     * @throws KeyAlreadyExistsException If there is already an object with such id
     */
    public int setId(int previousId,int targetId){
        if(idList.get(previousId) == null) throw new NoSuchElementException("No object with such id.");
        else if(idList.get(targetId) != null) throw new KeyAlreadyExistsException("Object with this id already exists.");
        else {
            K targetObject = idList.get(previousId);
            idList.remove(previousId);
            idPool.add(previousId);
            idList.put(targetId,targetObject);
            checkForNaturalOrder(targetId);
            return targetId;
        }
    }
}


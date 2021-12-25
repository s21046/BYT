import ApplicationExceptions.StringTooShortException;
import ApplicationExceptions.ValueAlreadyExistsException;
import org.junit.Before;
import org.junit.Test;

import javax.management.AttributeNotFoundException;
import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class UniqueIdGeneratorTest {
    UniqueIdGenerator<int[]> uig;
    UniqueIdGenerator<int[]> uig1;
    int[] id1;
    int[] id2;

    @Before
    public void setUp() throws StringTooShortException {
        uig = new UniqueIdGenerator<>();
        uig1 = new UniqueIdGenerator<>();
        id1 = new int[2];
        id2 = new int[2];
    }

    /**
     * New unique id is generated.
     * Class fetches free id from the pool and returns it.
     * Corner cases:
     * - Object already has an id
     */
    @Test
    public void testGenerateId() throws ValueAlreadyExistsException {
        id1[0] = uig.generateId(id1);
        assertEquals(0,id1[0]);
        id2[0] = uig.generateId(id2);
        assertEquals(1,id2[0]);
    }
    @Test(expected = ValueAlreadyExistsException.class)
    public void testGenerateIdValueAlreadyExists() throws ValueAlreadyExistsException {
        uig.generateId(id1);
        uig.generateId(id1);
    }

    /**
     * Set the {id} value of the object.
     * Object {id} is set anew and returned.
     * Corner cases:
     * - no object witch {previousId}.
     * - {targetId} is already taken.
     */

    @Test
    public void testSetId() throws ValueAlreadyExistsException {
        id1[0] = uig.generateId(id1);
        id2[0] = uig.generateId(id2);
        assertEquals(3, uig.setId(id1[0],3));
        assertEquals(0, uig.setId(id2[0],0)); //test that id = 0 is back in the pool
    }

    @Test(expected = NoSuchElementException.class)
    public void testSetIdNoSuchElement(){
        uig.setId(0,1);
    }

    @Test(expected = KeyAlreadyExistsException.class)
    public void testSetIdKeyAlreadyExists() throws ValueAlreadyExistsException {
        id1[0] = uig.generateId(id1);
        id2[0] = uig.generateId(id2);
        assertEquals(0, uig.setId(id2[0],0));
    }

    /**
     * {id} of {targetObjet} is returned.
     * UniqueIdGenerator finds {targetObject} and returns its {id}
     * Corner cases:
     * - Object is not registered in the generator
     */

    @Test
    public void testGetObjectsId() throws ValueAlreadyExistsException, AttributeNotFoundException {
        id1[0] = uig.generateId(id1);
        assertEquals(id1[0], uig.getObjectsId(id1));
    }

    @Test(expected = AttributeNotFoundException.class)
    public void testGetObjectsIdAttributeNotFound() throws AttributeNotFoundException {
        uig.getObjectsId(id1);
    }
}

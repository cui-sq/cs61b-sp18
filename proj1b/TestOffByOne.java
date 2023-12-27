import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        assertFalse(offByOne.equalChars('a', 'z'));
        assertTrue(offByOne.equalChars('c', 'b'));
        assertTrue(offByOne.equalChars('b', 'c'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertTrue(offByOne.equalChars('A', 'B'));
    }

}

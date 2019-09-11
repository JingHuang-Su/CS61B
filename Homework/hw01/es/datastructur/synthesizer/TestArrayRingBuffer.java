package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void isEmptyTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        Integer[] expected = new Integer[10];
        assertEquals(true, arb.isEmpty());

    }
    @Test
    public void isFullTest(){
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        arb.enqueue(33.1); // 33.1 null null  null
        arb.enqueue(44.8); // 33.1 44.8 null  null
        arb.enqueue(62.3); // 33.1 44.8 62.3  null
        arb.enqueue(-3.4);// 33.1 44.8 62.3 -3.4
        assertTrue(arb.isFull());
    }
    @Test
    public void Testenqueue(){
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        Integer[] expected = new Integer[4];
        expected[0] = 33;
        arb.enqueue(33);
        assertEquals(expected[0], arb.peek());
    }
    @Test
    public void Testpeek(){
        ArrayRingBuffer arb = new ArrayRingBuffer(4);
        Integer[] expected = {33};
        arb.enqueue(33);
        assertEquals(expected[0], arb.peek());
    }

}

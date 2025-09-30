package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    ArrayRingBuffer arb = new ArrayRingBuffer(10);


    @Test(expected = RuntimeException.class)
    public void testOverflow() {
        ArrayRingBuffer<Integer> buf = new ArrayRingBuffer<>(2);
        buf.enqueue(10);
        buf.enqueue(20);
        buf.enqueue(30); // 这里会抛出 "Ring Buffer Overflow"
    }
    @Test(expected = RuntimeException.class)
    public void testUnderflow() {
        ArrayRingBuffer<Integer> buf = new ArrayRingBuffer<>(2);
        buf.dequeue(); // 这里会抛出 "Ring Buffer Underflow"
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 

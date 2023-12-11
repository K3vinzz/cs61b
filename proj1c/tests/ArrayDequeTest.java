import deque.ArrayDeque;
import deque.Deque;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDequeTest {
    @Test
    public void iteratorTest(){
        Deque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 100; i += 1){
            a.addLast(i);
        }
        for (Integer s : a){
            System.out.println(s);
        }
    }

    @Test
    public void equalsTest(){
        Deque<String> a = new ArrayDeque<>();
        a.addLast("front");
        a.addLast("middle");
        a.addLast("back");

        Deque<String> b = new ArrayDeque<>();
        b.addLast("front");
        b.addLast("middle");
        b.addLast("back");

        assertThat(b.equals(a)).isTrue();

    }

}

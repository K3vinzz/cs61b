import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;

import static com.google.common.truth.Truth.assertThat;
public class LinkedListTest {
    @Test
    public void iteratorTest(){
        Deque<String> lld1 = new LinkedListDeque<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        assertThat(lld1).containsExactly("front", "middle", "back");
        for (String s : lld1){
            System.out.println(s);
        }
    }

    @Test
    public void equalsTest(){
        Deque<String> lld1 = new LinkedListDeque<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        Deque<String> lld2 = new LinkedListDeque<>();
        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1.equals(lld2)).isTrue();
    }

}

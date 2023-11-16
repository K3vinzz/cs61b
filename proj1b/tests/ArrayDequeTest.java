import jh61b.utils.Reflection;
import org.apache.hc.client5.http.impl.routing.SystemDefaultRoutePlanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

     @Test
     @DisplayName("ArrayDeque has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
     public void addFirstTest(){
         Deque<Integer> a = new ArrayDeque<>();
         a.addFirst(2);
         a.addFirst(1);
     }

     @Test
     public void getTest(){
         Deque<Integer> a = new ArrayDeque<>();
         a.addFirst(2);
         a.addFirst(1);
         assertThat(a.get(0)).isEqualTo(1);
         assertThat(a.get(1)).isEqualTo(2);
     }

     @Test
    public void toListTest(){
         Deque<Integer> a = new ArrayDeque<>();
         a.addFirst(4);
         a.addFirst(3);
         a.addFirst(2);
         a.addFirst(1);
         a.addLast(5);
         a.addLast(6);
         a.addLast(7);
         a.addLast(8);
         assertThat(a.toList()).containsExactly(1,2,3,4,5,6,7,8).inOrder();
     }

     @Test
    public void resizeTest(){
         Deque<Integer> a = new ArrayDeque<>();
         for (int i = 1; i < 36; i += 1){
             a.addLast(i);
         }
         System.out.println(a.toList());
         System.out.println(a.size());
     }

    @Test
    public void removeFirstTest(){
        Deque<Integer> a = new ArrayDeque<>();
        a.addFirst(4);
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
        a.addLast(9);
        a.addLast(10);
        a.removeFirst();
        System.out.println(a.toList());
    }

    @Test
    public void removeLastTest(){
        Deque<Integer> a = new ArrayDeque<>();
        a.addFirst(4);
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addLast(8);
        a.addLast(9);
        a.addLast(10);
        for (int i = 0; i < 9; i += 1){
            a.removeLast();
        }
        System.out.println(a.toList());
    }

    @Test
    public void resizeDowntest(){
        Deque<Integer> a = new ArrayDeque<>();
        for (int i = 0; i < 33; i++){
            a.addLast(i);
        }
        for (int i = 0; i < 27; i++){
            a.removeLast();
        }
        System.out.println(a.toList());
    }
}

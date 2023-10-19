import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Deque;
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
         ArrayDeque<Integer> a = new ArrayDeque<>();
         a.addFirst(1);
         a.addFirst(2);
         a.addFirst(3);
         assertThat(a.toList()).containsExactly(3, 2, 1).inOrder();
     }

     @Test
     public void addLastTest(){
         ArrayDeque<Integer> a = new ArrayDeque<>();
         a.addLast(1);
         a.addLast(2);
         a.addLast(3);
         assertThat(a.toList()).containsExactly(1,2,3).inOrder();
     }

     @Test
     public void resizeTest(){
         ArrayDeque<Integer> a = new ArrayDeque<>();
         a.addFirst(1);
         a.addLast(2);
         a.addLast(4);
         a.addLast(6);
         a.addLast(7);
         a.addFirst(3);
         a.addFirst(5);
         a.addFirst(8);
         a.addFirst(100);
         a.addFirst(234);
         a.addLast(567);
         assertThat(a.toList()).containsExactly(234,100,8,5,3,1,2,4,6,7,567).inOrder();
     }

     @Test
    public void getTest(){
         ArrayDeque<Integer> a = new ArrayDeque<>();
         a.addFirst(1);
         a.addLast(2);
         a.addFirst(3);
         a.addLast(4);
         a.addFirst(5);
         a.addLast(6); // ArrayDeque: [nextFirst, 5, 3, 1, 2, 4, 6, nextLast]
                          //      items: [1, 2, 4, 6, nextLast, nextFirst, 5, 3]
         assertThat(a.get(0)).isEqualTo(5);
         assertThat(a.get(5)).isEqualTo(6);
//         assertThat(a.get(6)).isNull();
     }

     @Test
     public void sizeTest(){
         ArrayDeque<String> a = new ArrayDeque<>();
         a.addFirst("abcs");
         a.addLast("qwiduh");
         a.addLast("21easd");
         assertThat(a.size()).isEqualTo(3);
     }

     @Test
    public void isEmptyTest(){
         ArrayDeque<String> a = new ArrayDeque<>();
         assertThat(a.isEmpty()).isTrue();
         a.addLast("qwe");
         a.addFirst("12312");
         assertThat(a.isEmpty()).isFalse();
     }

     @Test
    public void removeFirstTest(){
         ArrayDeque<Integer> a = new ArrayDeque<>();
         a.addFirst(1);
         a.addLast(2);
         a.addFirst(3);
         a.addLast(4);
         a.addFirst(5);
         a.addLast(6); // [5, 3, 1, 2, 4, 6]
         a.removeFirst(); // [3, 1, 2, 4, 6]
         assertThat(a.toList()).containsExactly(3, 1, 2, 4, 6).inOrder();

     }


}

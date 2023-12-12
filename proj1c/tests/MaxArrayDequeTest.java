import deque.MaxArrayDeque;
import org.junit.jupiter.api.*;
import deque.Deque;
import deque.ArrayDeque;
import deque.LinkedListDeque;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {
    public class Dog implements Comparable<Dog>{
        public String name;
        private int size;
        public Dog(String n, int s){
            name = n;
            size = s;
        }

        @Override
        public int compareTo(Dog o){
            return this.size - o.size;
        }

        public static class NameComparator implements Comparator<Dog>{
            @Override
            public int compare(Dog o1, Dog o2){
                return o1.name.compareTo(o2.name);
            }
        }

        public static Comparator<Dog> getNameComparator(){
            return new NameComparator();
        }
    }

    @Test
    public void sizeTest(){
        Dog d1 = new Dog("Elyse", 3);
        Dog d2 = new Dog("Sture", 9);
        Dog d3 = new Dog("Benjamin", 15);

        MaxArrayDeque<Dog> dogsArray = new MaxArrayDeque<>();
        dogsArray.addLast(d1);
        dogsArray.addLast(d2);
        dogsArray.addLast(d3);
        System.out.println(dogsArray.max(Dog.getNameComparator()).name);
    }

}

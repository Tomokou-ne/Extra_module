import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        //создаем лист с целыми цислами, для дальнейшей работы в stream
        List<Integer> list = new ArrayList<>();
        list.add(20);
        list.add(-58);
        list.add(451);
        list.add(-66);
        list.add(3);
        list.add(-485);

        Stream<Integer> stream = list.stream();
        findMinMax(
                stream,
                Integer::compareTo,
                (x, y) -> System.out.println(String.format("min: %s, max: %s", x, y)));
        stream.close();

        Stream<Integer> stream1 = list.stream();
        findEvenNumbers(stream1);
    }
    //метод findMinMax для определения минимального и максимального целочисленного ззначения в стриме
    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> list = stream.sorted(order).collect(Collectors.toList());
        var min = list.get(0);
        var max = list.get(list.size() - 1);
        if (!list.isEmpty()) {
            minMaxConsumer.accept(min, max);
        } else {
            minMaxConsumer.accept(null, null);
        }
    }
    //метод для определения четных чисел в стриме
    public static <T> void findEvenNumbers(
            Stream<? extends T> stream
    ) {
        List<T> listEven = stream.filter(x -> (int) x % 2 == 0).collect(Collectors.toList());
        long quantityEvenNumbers = listEven.size();
        System.out.println("quantity of even numbers: " + quantityEvenNumbers + ", they're " + listEven);
    }
}
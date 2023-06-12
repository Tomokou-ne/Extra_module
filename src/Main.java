import javax.sound.midi.Soundbank;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static javax.swing.text.html.HTML.Tag.U;

public class Main {
    public static void main(String[] args) {

        //создаем объект predicate для реализации одноименного функционального интерфейса
        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer >= 0;
            }
        };
        //тест интерфейса
        System.out.println(predicate.test(25));
        System.out.println(predicate.test(-44));

        //выполним проверку посредством predicate, но уже в виде лямбда-выражения
        Predicate<Integer> predicateLyambda = x -> x >= 0;
        System.out.println(predicateLyambda.test(-33));
        System.out.println(predicateLyambda.test(67));

        System.out.println("--------------------------------");

        //создаем объект consumer
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Greetings, " + s + "!");
            }
        };
        consumer.accept("Murat");

        Consumer<String> consumerLyambda = s -> System.out.println("Greetings, " + s + "!");
        consumerLyambda.accept("Marina");

        System.out.println("--------------------------------");

        //создаем объект function
        Function<Double, Long> function = new Function<>() {
            @Override
            public Long apply(Double aDouble) {
                return aDouble.longValue();
            }
        };

        System.out.println(function.apply(2584686.48165));

        Function<Double, Long> functionLyambda = d -> d.longValue();
        System.out.println(functionLyambda.apply(8459.5462));

        System.out.println("--------------------------------");

        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return (int)(Math.random() * 100);
            }
        };
        System.out.println(supplier.get());

        Supplier<Integer> supplierLyambda = () -> (int)(Math.random() * 100);
        System.out.println(supplierLyambda.get());

        System.out.println("--------------------------------");
        }
    Predicate<Object> condition = Objects::isNull;
    Function<Object, String> ifTrue = obj -> "null";
    Function<Object, String> ifFalse = Object::toString;
    Function<Object, String> objectToCharSequence =
            ternaryOperator(condition, ifTrue, ifFalse);

    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {
        return t -> condition.test(t) ? ifTrue.apply(t) : ifFalse.apply(t);
    }
}
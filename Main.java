import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@FunctionalInterface
interface Function<T, P> {
    P apply(T t);
}
@FunctionalInterface
interface Filter<T>{
    boolean test(T t);
}
@FunctionalInterface
interface Union<T>{
    T apply(T a, T b);
}
public class Main {

    public static <T, P> List<P> transform(List<T> inputList, Function<T, P> func) {
        List<P> newList = new ArrayList<>();
        for (T item : inputList) {
            newList.add(func.apply(item));
        }
        return newList;
    }
    public static <T> List<T> filter(List<T> list, Filter<T> filter) {
        List<T> newList = new ArrayList<>();
        for (T value : list) {
            if (filter.test(value)) {
                newList.add(value);
            }
        }
        return newList;
    }
    public static <T> T reduce(List<T> list, T identity, Union<T> union) {
        T ans = identity; // Начальное значение
        for (T value : list) {
            ans = union.apply(ans, value);
        }
        return ans;
    }
    public static List<int[]> inputArrays() {
        Scanner scanner = new Scanner(System.in);
        List<int[]> list = new ArrayList<>();

        System.out.print("Введите количество массивов: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Введите длину массива " + (i + 1) + ": ");
            int arrayLength = scanner.nextInt();
            int[] array = new int[arrayLength];
            System.out.println("Введите элементы массива:");
            for (int j = 0; j < arrayLength; j++) {
                array[j] = scanner.nextInt();
            }

            list.add(array);
        }

        return list;
    }
    public static void main(String[] args) {
        int len;
        List<String> strings = Arrays.asList("qwerty", "asdfg", "zx");
        List<Integer> numbers = Arrays.asList(1, -3, 7);
        List<int[]> arrays = Arrays.asList(new int[]{1, 2, 3}, new int[]{-1, -2, -3}, new int[]{5, 4, 3});
        System.out.println("Введите номер задачи(в формате: 1.1, где " +
                "первая цифра номер задачи, а вторая - номер подзадачи):");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        switch (s){
            case "1.1":
                Box<Integer> intBox = new Box<>();
                intBox.putItem(3);
                Integer v = intBox.getItem();
                System.out.println("Извлеченное значение: " + v);
                System.out.println("Коробка пуста: " + intBox.isFull());
                break;
            case "3.1":

                List<Integer> lengths = transform(strings, String::length);
                System.out.println("Длины строк: " + lengths); // [6, 5, 2]

                List<Integer> absoluteValues = transform(numbers, n -> Math.abs(n));
                System.out.println("Модули чисел: " + absoluteValues);

                arrays = inputArrays();


                List<Integer> maxValues = transform(arrays, arr -> {
                    int max = Integer.MIN_VALUE;
                    for (int num : arr) {
                        if (num > max) {
                            max = num;
                        }
                    }
                    return max;
                });
                System.out.println("Максимальные значения: " + maxValues);
                break;
            case "3.2":
                List<String> filteredStrings = filter(strings, value -> value.length() >= 3);
                System.out.println("Строки, имеющие не менее 3 элементов: " + filteredStrings);
                List<Integer> filteredIntegers = filter(numbers, value -> value <= 0);
                System.out.println("Отрицательные числа: " + filteredIntegers);
                arrays = inputArrays();
                List<int[]> filteredArrays = filter(arrays, array -> {
                    for (int num : array) {
                        if (num > 0) {
                            return false;
                        }
                    }
                    return true;
                });
                System.out.println("Списки без положительных элементов:");
                for (int[] array : filteredArrays) {
                    for (int num : array) {
                        System.out.print(num + " ");
                    }
                    System.out.println();
                }
                break;
            case "3.3":
                String concatenatedString = reduce(strings, "", (a, b) -> a + b);
                System.out.println("Склеенные строки: " + concatenatedString);

                Integer sum = reduce(numbers, 0, (a, b) -> a + b);
                System.out.println("Сумма элементов: " + sum);
                arrays = inputArrays();
                List<Integer> lengthsOfArrays = transform(arrays, a -> a.length);
                int countOfElems = reduce(lengthsOfArrays, 0, (a, b) -> a + b);
                System.out.println("Сумма длин массивов: " + countOfElems);
                break;
        }

    }
}
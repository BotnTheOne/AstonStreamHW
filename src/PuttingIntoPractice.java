import Entity.Trader;
import Entity.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/*
-->Дмитрий Борзунов
-> Домашнее задание по стримам и лямбдам
1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
2. Вывести список неповторяющихся городов, в которых работают трейдеры.
3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
5. Выяснить, существует ли хоть один трейдер из Милана.
6. Вывести суммы всех транзакций трейдеров из Кембриджа.
7. Какова максимальная сумма среди всех транзакций?
8. Найти транзакцию с минимальной суммой.
 */

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        //    1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
        List<Transaction> transactions2011 = transactions.
                stream().
                filter(t -> t.getYear() == 2011).
                sorted(Comparator.comparingInt(Transaction::getValue)).
                toList();
        System.out.println("1. Все транзакции за 2011 год, отсортироватнные по сумме (от меньшей к большей):\n" + transactions2011);

//    2. Вывести список неповторяющихся городов, в которых работают трейдеры.
        List<String> cities = transactions.
                stream().
                map(t -> t.getTrader().getCity()).
                distinct().
                toList();
        System.out.println("\n2. Список неповторяющихся городов, в которых работают трейдеры:\n" + cities);

//    3. Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        List<Trader> traders = transactions.
                stream().
                map(Transaction::getTrader).
                filter(t -> t.getCity().equals("Cambridge")).
                distinct().
                sorted(Comparator.comparing(Trader::getName)).
                toList();
        System.out.println("\n3. Все трейдеры из Кембриджа, отсортированные по их именам:\n" + traders);

//    4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        String names = transactions.
                stream().
                map(t -> t.getTrader().getName()).
                distinct().
                sorted().
                toList().
                toString();
        System.out.println("\n4. Строка со всеми именами трейдеров, отсортированными в алфавитном порядке:\n" + names);

//    5. Выяснить, существует ли хоть один трейдер из Милана.
        boolean isMilan = !transactions.
                stream().
                map(Transaction::getTrader).
                filter(t -> t.getCity().equals("Milan")).
                toList().
                isEmpty();
        System.out.println("\n5. Существует ли хоть один трейдер из Милана? Ответ:\n" + isMilan);

//     6. Вывести суммы всех транзакций трейдеров из Кембриджа.
        Integer sumCambridge = transactions.
                stream().
                filter(t -> t.getTrader().getCity().equals("Cambridge")).
                mapToInt(Transaction::getValue).
                sum();
        System.out.println("\n6. Суммы всех транзакций трейдеров из Кембриджа:\n" + sumCambridge);

//      7. Какова максимальная сумма среди всех транзакций?
        Integer maxSum = transactions.
                stream().
                mapToInt(Transaction::getValue).
                max().
                orElseThrow();
        System.out.println("\n7. Максимальная сумма среди всех транзакций:\n" + maxSum);

//        8. Найти транзакцию с минимальной суммой.
        Transaction minSumTransaction = transactions.
                stream().
                min(Comparator.comparing(Transaction::getValue)).
                orElseThrow();
        System.out.println("\n8. Транзакция с минимальной суммой:\n" + minSumTransaction);
    }
}

package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = { 7, 13, 17 })
    public void isPrimeForPrimes(int number) {
        boolean result1 = numberWorker.isPrime(number);
        boolean result2 = numberWorker.isPrime(number);
        boolean result3 = numberWorker.isPrime(number);
        Assertions.assertTrue(result1);
        Assertions.assertTrue(result2);
        Assertions.assertTrue(result3);
    }

    @ParameterizedTest
    @ValueSource(ints = { 4, 16, 81 })
    public void isPrimeForNotPrimes(int number) {
        boolean result1 = numberWorker.isPrime(number);
        boolean result2 = numberWorker.isPrime(number);
        boolean result3 = numberWorker.isPrime(number);
        Assertions.assertFalse(result1);
        Assertions.assertFalse(result2);
        Assertions.assertFalse(result3);
    }

    @ParameterizedTest
    @ValueSource(ints = { -7, 0, 1 })
    public void isPrimeForIncorrectPrimes(int number) {
        Assertions.assertThrows(IllegalNumberException.class, () -> {
            numberWorker.isPrime(number);
        });
        Assertions.assertThrows(IllegalNumberException.class, () -> {
            numberWorker.isPrime(number);
        });
        Assertions.assertThrows(IllegalNumberException.class, () -> {
            numberWorker.isPrime(number);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void digitSumTest(int num, int sum) {
        Assertions.assertEquals(sum, numberWorker.digitSum(num));
        Assertions.assertEquals(sum, numberWorker.digitSum(num));
        Assertions.assertEquals(sum, numberWorker.digitSum(num));
        Assertions.assertEquals(sum, numberWorker.digitSum(num));
        Assertions.assertEquals(sum, numberWorker.digitSum(num));
        Assertions.assertEquals(sum, numberWorker.digitSum(num));
        Assertions.assertEquals(sum, numberWorker.digitSum(num));
        Assertions.assertEquals(sum, numberWorker.digitSum(num));
        Assertions.assertEquals(sum, numberWorker.digitSum(num));
        Assertions.assertEquals(sum, numberWorker.digitSum(num));
    }
}

package edu.school21.numbers;

public class NumberWorker {

    public boolean isPrime(int n) {
            if (n <= 1)
                throw new IllegalNumberException();

            // Check from 2 to n-1
            for (int i = 2; i < n; i++)
                if (n % i == 0)
                    return false;

            return true;
    }



    public int digitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum = sum + n % 10;
            n = n / 10;
        }
        return sum;
    }
}


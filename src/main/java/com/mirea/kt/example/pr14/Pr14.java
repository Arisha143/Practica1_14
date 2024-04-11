/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mirea.kt.example.pr14;

/**
 *
 * @author arrri
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Pr14 {
    public static void main(String[] args) {
        System.out.println("1.14 Селянкина  Арина Николаевна РИБО-01-22 Вариант 5 \n");
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        Thread generatorThread = new Thread(new NumberGenerator(numbers, random));
        generatorThread.start();

        Thread sortThread = new Thread(new NumberSorter(numbers));
        sortThread.start();

        try {
            generatorThread.join();
            sortThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(numbers);
    }

    static class NumberGenerator implements Runnable {
        private final List<Integer> numbers;
        private final Random random;

        public NumberGenerator(List<Integer> numbers, Random random) {
            this.numbers = numbers;
            this.random = random;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                numbers.add(random.nextInt(1000));
            }
        }
    }

    static class NumberSorter implements Runnable {
        private final List<Integer> numbers;

        public NumberSorter(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public void run() {
            Collections.sort(numbers);
        }
    }
}

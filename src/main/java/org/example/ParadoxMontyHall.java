package org.example;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ParadoxMontyHall {
    static final int DORS_COUNT = 3; // Количество дверей.
    static final int ATTEMPTS = 1000; // Количество попыток.
    static Map<Integer, Boolean> resultsChangeSelection; // Статистика игрока не изменяющего выбор.
    static Map<Integer, Boolean> resultsNotChangeSelection; // Статистика игрока изменяющего выбор.

    public static void main(String[] args) {
        resultsChangeSelection = new HashMap<>();
        resultsNotChangeSelection = new HashMap<>();

        for (int i = 0; i < ATTEMPTS; i++) {
            start(i);
        }

        int win = 0;
        for (Map.Entry<Integer, Boolean> entry : resultsChangeSelection.entrySet()) {
            if (entry.getValue()) {
                win++;
            }
        }
        System.out.println("\nПарадокс Монти Холла\n");
        System.out.println("Количество попыток: " + ATTEMPTS);
        System.out.printf("Процент выгрышей для игрока не меняющего свой выбор: %.2f\n", (float) win / (ATTEMPTS / 100));

        win = 0;
        for (Map.Entry<Integer, Boolean> entry : resultsNotChangeSelection.entrySet()) {
            if (entry.getValue()) {
                win++;
            }
        }
        System.out.printf("Процент выгрышей для игрока изменяющего свой выбор: %.2f\n", (float) win / (ATTEMPTS / 100));
    }

    private static void start(int numRound) {
        int success = new Random().nextInt(DORS_COUNT);
        int firstChoice = new Random().nextInt(DORS_COUNT);
        int freeOpenDoor = -1;
        int secondChoice = -1;

        for (int i = 0; i < DORS_COUNT; i++) {
            if (i != success && i != firstChoice) {
                freeOpenDoor = i;
            }
        }

        for (int i = 0; i < DORS_COUNT; i++) {
            if (i != freeOpenDoor && i != firstChoice) {
                secondChoice = firstChoice;
                break;
            }
        }
//        System.out.println("Не изменил решение: попытка № " + numRound + ", рзультат - " + (success == secondChoice));
        resultsChangeSelection.put(numRound, success == secondChoice);

        for (int i = 0; i < DORS_COUNT; i++) {
            if (i != freeOpenDoor && i != firstChoice) {
                secondChoice = i;
            }
        }
//        System.out.println("Изменил решение   : попытка № " + numRound + ", рзультат - " + (success == secondChoice));
        resultsNotChangeSelection.put(numRound, success == secondChoice);
    }
}
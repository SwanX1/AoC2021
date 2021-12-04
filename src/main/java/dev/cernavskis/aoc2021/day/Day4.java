package dev.cernavskis.aoc2021.day;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import dev.cernavskis.aoc2021.util.Utils;

import java.io.*;

public class Day4 {
  public static void main(String[] args) {
    try {
      System.out.println("Part 1: " + solvePart1(Utils.getBufferedReader("day4/input.txt")));
      System.out.println("Part 2: " + solvePart2(Utils.getBufferedReader("day4/input.txt")));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static int solvePart1(BufferedReader reader) throws IOException {
    ParsedInput input = getParsedInput(reader);
    List<Integer> numbers = input.numbers;
    List<BingoCard> cards = input.cards;

    BingoCard winningCard = null;
    int winningNumber = 0;

    // Feeding numbers to cards
    numberLoop: for (int i = 0; i < numbers.size(); i++) {
      for (BingoCard card : cards) {
        card.addChecked(numbers.get(i));
        if (card.isComplete()) {
          winningCard = card;
          winningNumber = numbers.get(i);
          break numberLoop;
        }
      }
    }

    return winningNumber * winningCard.getUnchecked().stream().collect(Collectors.summingInt(Integer::intValue));
  }


  public static int solvePart2(BufferedReader reader) throws IOException {
    ParsedInput input = getParsedInput(reader);
    List<Integer> numbers = input.numbers;
    List<BingoCard> cards = input.cards;
    List<BingoCard> potentialLosingCards = new ArrayList<>();
    for (BingoCard card : cards) {
      potentialLosingCards.add(card);
    }

    BingoCard losingCard = null;
    int losingNumber = 0;

    // Feeding numbers to cards
    numberLoop: for (int i = 0; i < numbers.size(); i++) {
      for (int j = 0; j < cards.size(); j++) {
        BingoCard card = cards.get(j);
        if (potentialLosingCards.contains(card)) {
          card.addChecked(numbers.get(i));
          if (card.isComplete()) {
            if (potentialLosingCards.size() > 1) {
              potentialLosingCards.remove(card);
            } else {
              losingCard = card;
              losingNumber = numbers.get(i);
              break numberLoop;
            }
          }
        }
      }
    }

    return losingNumber * losingCard.getUnchecked().stream().collect(Collectors.summingInt(Integer::intValue));
  }

  public static ParsedInput getParsedInput(BufferedReader reader) throws IOException {
    String line;

    // The first line is the numbers
    line = reader.readLine();
    List<Integer> numbers = Arrays.asList(line.split(",")).stream().map((Function<String, Integer>) Integer::parseInt).collect(Collectors.toList());
    List<BingoCard> cards = new LinkedList<>();
    
    while ((line = reader.readLine()) != null) {
      int[][] card = new int[5][5];
      for (int i = 0; i < 5; i++) {
        line = reader.readLine();
        String[] row = line.trim().split(" +");
        for (int j = 0; j < 5; j++) {
          card[i][j] = Integer.parseInt(row[j]);
        }
      }

      cards.add(new BingoCard(card));
    }

    return new ParsedInput(numbers, cards);
  }

  public static record ParsedInput(List<Integer> numbers, List<BingoCard> cards) {}

  public static class BingoCard {
    public int[][] card;
    public Set<Integer> checked = new LinkedHashSet<>();

    public BingoCard(int[][] card) {
      this.card = card;
    }

    public List<Integer> getUnchecked() {
      List<Integer> unchecked = new LinkedList<>();
      for (int[] row : card) {
        for (int i : row) {
          if (!checked.contains(i)) {
            unchecked.add(i);
          }
        }
      }
      return unchecked;
    }

    public boolean addChecked(int number) {
      return checked.add(number);
    }

    public boolean isComplete() {
      for (int i = 0; i < 5; i++) {
        if (isRowComplete(i) || isColumnComplete(i)) return true;
      }

      return false;
    }

    public boolean isRowComplete(int i) {
      for (int number : card[i]) {
        if (!checked.contains(number)) return false;
      }
      return true;
    }

    public boolean isColumnComplete(int i) {
      for (int[] row : card) {
        if (!checked.contains(row[i])) return false;
      }
      return true;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("BingoCard(");
      sb.append(hashCode());
      sb.append(") [card=\n");
      
      for (int[] row : card) {
        for (int number : row) {
          sb.append(number).append(" ");
        }
        sb.append("\n");
      }

      sb.append("]");
      return sb.toString();
    }
  }
}

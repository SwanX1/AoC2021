/*
 * Copyright 2021 Kārlis Čerņavskis
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.cernavskis.aoc2021.day;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dev.cernavskis.aoc2021.util.Utils;

public class Day3 {
  public static void main(String[] args) {
    try {
      System.out.println("Part 1: " + solvePart1(Utils.getBufferedReader("day3/input.txt")));
      System.out.println("Part 2: " + solvePart2(Utils.getBufferedReader("day3/input.txt")));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static int solvePart1(BufferedReader reader) throws IOException {
    int[] bitFrequencies = new int[12];

    String line;
    while ((line = reader.readLine()) != null) {
      char[] charArray = line.toCharArray();
      for (int i = 0; i < charArray.length; i++) {
        bitFrequencies[i] += charArray[i] == '1' ? 1 : -1;
      }
    }

    String gammaBits = "";
    String epsilonBits = "";

    for (int i = 0; i < 12; i++) {
      if (bitFrequencies[i] < 0) {
        gammaBits += "0";
        epsilonBits += "1";
      } else {
        gammaBits += "1";
        epsilonBits += "0";
      }
    }

    int gamma = Integer.parseInt(gammaBits, 2);
    int epsilon = Integer.parseInt(epsilonBits, 2);

    return gamma * epsilon;
  }

  private static int solvePart2(BufferedReader reader) throws IOException {
    List<String> input = new ArrayList<>();
    String line;
    while ((line = reader.readLine()) != null) {
      input.add(line);
    }

    List<String> oxygenLines = new ArrayList<>(input.size());
    for (String inputLine : input) oxygenLines.add(inputLine);
    
    for (int i = 0; i < 12; i++) {
      if (oxygenLines.size() == 1) break;
      int zeroes = 0;
      int ones = 0;

      for (int j = 0; j < oxygenLines.size(); j++) {
        char[] charArray = oxygenLines.get(j).toCharArray();
        if (charArray[i] == '0') {
          zeroes++;
        } else {
          ones++;
        }
      }

      final int lI = i; // Variables in lambdas must be final
      if (zeroes > ones) {
        oxygenLines.removeIf(filter -> filter.charAt(lI) == '1');
      } else {
        oxygenLines.removeIf(filter -> filter.charAt(lI) == '0');
      }
    }

    List<String> co2Lines = new ArrayList<>(input.size());
    for (String inputLine : input) co2Lines.add(inputLine);
    
    for (int i = 0; i < 12; i++) {
      if (co2Lines.size() == 1) break;
      int zeroes = 0;
      int ones = 0;

      for (int j = 0; j < co2Lines.size(); j++) {
        char[] charArray = co2Lines.get(j).toCharArray();
        if (charArray[i] == '0') {
          zeroes++;
        } else {
          ones++;
        }
      }

      final int lI = i; // Variables in lambdas must be final
      if (zeroes > ones) {
        co2Lines.removeIf(filter -> filter.charAt(lI) == '0');
      } else {
        co2Lines.removeIf(filter -> filter.charAt(lI) == '1');
      }
    }

    int oxygenRating = Integer.parseInt(oxygenLines.get(0), 2);
    int co2Rating = Integer.parseInt(co2Lines.get(0), 2);
    
    return oxygenRating * co2Rating;
  }
}

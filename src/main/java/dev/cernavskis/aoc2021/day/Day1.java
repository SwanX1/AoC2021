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

public class Day1 {
  public static void main(String[] args) {
    try {
      System.out.println("Part 1: " + solvePart1(Utils.getBufferedReader("day1/input.txt")));
      System.out.println("Part 2: " + solvePart2(Utils.getBufferedReader("day1/input.txt")));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static int solvePart1(BufferedReader reader) throws IOException {
    int previous = -1; // Setting this to -1 should be okay since we shouldn't even get it.
    int increasedTimes = -1; // We start at -1 because the first number will always be larger than -1.
    
    String line;
    while ((line = reader.readLine()) != null) {
      int current = Integer.parseInt(line);
      if (current > previous) {
        increasedTimes++;
      }
      previous = current;
    }

    return increasedTimes;
  }

  private static int solvePart2(BufferedReader reader) throws IOException {
    List<Integer> input = new ArrayList<>(); // Yes, I know that not specifying an initial capacity is slow.
    
    String line;
    while ((line = reader.readLine()) != null) {
      input.add(Integer.parseInt(line));
    }
    
    int increasedTimes = -1;
    
    int lastSum = 0;
    for (var i = 0; i < (input.size() - 2); i++) {
      input.set(i, input.get(i) + input.get(i + 1) + input.get(i + 2));
      if (input.get(i) > lastSum) {
        increasedTimes++;
      }
      lastSum = input.get(i);
    }
    
    return increasedTimes;
  }
}

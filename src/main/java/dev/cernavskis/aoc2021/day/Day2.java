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

public class Day2 {
  public static void main(String[] args) {
    try {
      System.out.println("Part 1: " + solvePart1(Utils.getBufferedReader("day2/input.txt")));
      System.out.println("Part 2: " + solvePart2(Utils.getBufferedReader("day2/input.txt")));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static int solvePart1(BufferedReader reader) throws IOException {
    int depth = 0;
    int horizontal = 0;

    String line;
    while ((line = reader.readLine()) != null) {
      String[] commandParts = line.split(" ", 2);
      String command = commandParts[0];
      int amount = Integer.parseInt(commandParts[1]);

      if (command.equals("forward")) {
        horizontal += amount;
      } else if (command.equals("down")) {
        depth += amount;
      } else if (command.equals("up")) {
        depth -= amount;
      } else {
        System.err.println("Unknown command: " + command);
      }
    }

    return depth * horizontal;
  }

  private static int solvePart2(BufferedReader reader) throws IOException {
    int depth = 0;
    int horizontal = 0;
    int aim = 0;

    String line;
    while ((line = reader.readLine()) != null) {
      String[] commandParts = line.split(" ", 2);
      String command = commandParts[0];
      int amount = Integer.parseInt(commandParts[1]);

      if (command.equals("forward")) {
        horizontal += amount;
        depth += aim * amount;
      } else if (command.equals("down")) {
        aim += amount;
      } else if (command.equals("up")) {
        aim -= amount;
      } else {
        System.err.println("Unknown command: " + command);
      }
    }

    return depth * horizontal;
  }
}

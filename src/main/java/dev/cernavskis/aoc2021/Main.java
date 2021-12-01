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

package dev.cernavskis.aoc2021;

import java.lang.reflect.Method;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("Usage: ./gradlew run --args=\"<day>\"");
      System.exit(1);
    }

    try {
      Class<?> clazz = Class.forName("dev.cernavskis.aoc2021.day.Day" + args[0]);

      // Remap args to remove the day number
      String[] arguments = new String[args.length - 1];
      for (int i = 1; i < args.length; i++) {
        arguments[i - 1] = args[i];
      }

      Method method = clazz.getMethod("main", String[].class);
      method.invoke(null, (Object) args);
    } catch (ClassNotFoundException e) {
      System.err.println("Day " + args[0] + " not found");
      System.exit(1);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}

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

package dev.cernavskis.aoc2021.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class Utils {
  public static BufferedReader getBufferedReader(String filename) {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(filename);
    return new BufferedReader(new InputStreamReader(is));
  }
}

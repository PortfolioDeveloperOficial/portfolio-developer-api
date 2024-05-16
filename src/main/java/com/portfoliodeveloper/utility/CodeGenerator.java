package com.portfoliodeveloper.utility;

import static com.portfoliodeveloper.utility.Utility.RANDOM;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeGenerator {

  public static String generate() {
    return Utility.padLeftZeros(String.valueOf(RANDOM.nextLong()).substring(0, 6), 6);
  }
}

package com.portfoliodeveloper.utility;

import java.util.Random;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utility {
  public static final Random RANDOM = new Random();

  public static String padLeftZeros(String input, int length) {
    if (input.length() >= length) {
      return input;
    }
    StringBuilder sb = new StringBuilder();
    while (sb.length() < length - input.length()) {
      sb.append('0');
    }
    sb.append(input);

    return sb.toString();
  }
}

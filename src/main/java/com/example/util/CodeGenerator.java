// package com.slp.arch.util;

// public class CodeGenerator {
//   public static String sequence(String lastCode, int length) {
//     if (lastCode == null) {
//       lastCode = "0";
//     }

//     final int intLastCode = Integer.valueOf(lastCode);
//     final int intCode = intLastCode + 1;
//     final StringBuilder code = new StringBuilder(String.valueOf(intCode));
//     while (code.length() < length) {
//       code.insert(0, "0");
//     }

//     return code.toString();
//   }
// }

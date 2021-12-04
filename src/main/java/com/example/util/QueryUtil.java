/*package com.slp.arch.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class QueryUtil {

  public static List<String> obtenerConvinaciones(String dato) {
    if (StringUtils.isEmpty(dato.trim())) {
      return new ArrayList<>();
    }

   List<List<String>> convinacionePorPalabras;
   List<List<String>> convinacionesFinal = new ArrayList<>();

    dato = dato.toLowerCase();
    List<String> palabras = dividirEnPalabras(dato);

    convinacionePorPalabras = optenerConvinacionesPorPalabra(palabras);
    if(convinacionePorPalabras.size()==0){
      return new ArrayList<>();
    }
    recursivo(getResultado(convinacionePorPalabras), convinacionePorPalabras.get(0),convinacionePorPalabras,convinacionesFinal);

    return devolverPalabrasCovinadasFinal(convinacionesFinal);
  }

  private static List<String> devolverPalabrasCovinadasFinal( List<List<String>> convinacionesFinal){
    List<String> listaFinal = new ArrayList<>();
    for (List<String> strings : convinacionesFinal) {
      listaFinal.add(String.join(" ", strings));
    }
    return listaFinal;
  }

 private static void recursivo(List<String> resultado, List<String> listToIncrement, List<List<String>> convinacionePorPalabras, List<List<String>> convinacionesFinal) {
    int convinacionesIndex = convinacionePorPalabras.indexOf(listToIncrement);
   for (String s : listToIncrement) {
     resultado.set(convinacionesIndex, s);

     if (convinacionesIndex < convinacionePorPalabras.size() - 1) {
       recursivo(resultado, convinacionePorPalabras.get(convinacionesIndex + 1),  convinacionePorPalabras,convinacionesFinal);
     } else if (convinacionesIndex == convinacionePorPalabras.size() - 1) {
       List<String> aux = new ArrayList<>(resultado);
       convinacionesFinal.add(aux);
     } else {
       break;
     }
   }
  }

  private static List<String> getResultado(List<List<String>> convinacionePorPalabras) {
    List<String> resultado = new ArrayList<>();
    for (int i = 0; i < convinacionePorPalabras.size(); i++) {
      resultado.add("");
    }
    return resultado;
  }

  private static List<List<String>> optenerConvinacionesPorPalabra(List<String> palabras) {
    List<List<String>> convinacionesPorPalabras = new ArrayList<>();
    for (String palabra : palabras) {
      convinacionesPorPalabras.add(convinarPalabra(palabra));
    }
    return convinacionesPorPalabras;
  }

  private static List<String> dividirEnPalabras(String dato) {
    List<String> palabras = new ArrayList<>();
    for (String s : dato.split(" ")) {
      if (!s.equals("")) {
        palabras.add(s);
      }
    }
    return palabras;
  }

  private static List<String> convinarPalabra(String dato) {
    List<String> convinaciones = new ArrayList<>();

    if (tieneTilde(dato)) {
      convinaciones.add(dato);
      return convinaciones;
    } else {
      convinaciones.add(dato);
    }

    String vocales = "aeiou";
    String vocalesContilde = "áéíóú";

    for (int i = 0; i < vocales.length(); i++) {
      for (int j = 0; j < dato.length(); j++) {
        if (vocales.charAt(i) == dato.charAt(j)) {
          var texto = dato.toCharArray();
          texto[j] = vocalesContilde.charAt(i);
          convinaciones.add(String.copyValueOf(texto));
        }
      }
    }
    return convinaciones;
  }


  private static boolean tieneTilde(String dato) {
    String vocalesContilde = "áéíóú";
    for (int i = 0; i < dato.length(); i++) {
      if (vocalesContilde.indexOf(dato.charAt(i)) >= 0) {
        return true;
      }
    }
    return false;
  }


}
*/
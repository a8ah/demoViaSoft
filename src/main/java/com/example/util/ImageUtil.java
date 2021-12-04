// package com.slp.arch.util;

// import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
// import org.springframework.core.io.ClassPathResource;

// import java.io.IOException;
// import java.util.Base64;

// public class ImageUtil {
//   public static String getImageBase64(String path) {
//     final ClassPathResource res = new ClassPathResource(path);
//     final ByteArrayOutputStream content = new ByteArrayOutputStream();
//     try {
//       content.write(res.getInputStream());
//       final var bytes = content.toByteArray();

//       content.flush();
//       content.close();

//       final var format = "jpg";
//       final var encoded = new String(Base64.getEncoder().encode(bytes));
//       return String.format("data:image/%s;base64,%s", format, encoded);
//     } catch (IOException e) {
//       e.printStackTrace();
//     }

//     return null;

//   }
// }

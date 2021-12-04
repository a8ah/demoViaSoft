// package com.slp.arch.util;

// import net.sf.jasperreports.engine.*;
// import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// import net.sf.jasperreports.engine.util.JRLoader;
// import org.springframework.core.io.ClassPathResource;

// import java.io.ByteArrayOutputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.util.Collection;
// import java.util.Locale;
// import java.util.Map;

// public class JasperReportUtil {
//     private static final String BASE_PATH = "jaspers/";

//     public static byte[] createPdf(String resource, Map<String, Object> parameters) {
//         return createPdf(resource,  parameters, null);
//     }

//     public static InputStream loadLogo() {
//         try {
//             return new ClassPathResource("jaspers/logos/soledad.jpg").getInputStream();
//         } catch (IOException e) {
//             return null;
//         }
//     }

//     public static InputStream loadLogo(String path) {
//         try {
//             return new ClassPathResource("jaspers/logos/" + path).getInputStream();
//         } catch (IOException e) {
//             return null;
//         }
//     }

//     public static InputStream loadImg(String path) {
//         try {
//             return new ClassPathResource(path).getInputStream();
//         } catch (IOException e) {
//             return null;
//         }
//     }

//     public static byte[] createPdf(String resource, Map<String, Object> parameters, Collection data) {
//         parameters.put(JRParameter.REPORT_LOCALE, Locale.US);

//         final JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(data);

//         final String path = BASE_PATH + resource + ".jasper";
//         final ClassPathResource res = new ClassPathResource(path);
//         try {
//             final JasperReport jasperReport = (JasperReport)JRLoader.loadObject(res.getInputStream());
//             final ByteArrayOutputStream pdfContent = new ByteArrayOutputStream();
//             final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, datasource);
//             JasperExportManager.exportReportToPdfStream(jasperPrint, pdfContent);

//             return pdfContent.toByteArray();
//         } catch (JRException | IOException e) {
//             e.printStackTrace();
//         }
//         return null;
//     }
// }

// package com.slp.arch.util;

// import org.apache.commons.logging.Log;
// import org.apache.commons.logging.LogFactory;
// import org.springframework.util.StringUtils;

// import java.lang.reflect.Method;
// import java.util.HashMap;
// import java.util.Map;

// public class BeanUtil {
//   private static final Log logger = LogFactory.getLog(BeanUtil.class);

//   public static  Object getValue(Object o, String property) {
//     final var clazz = o.getClass();
//     var value = o;

//     final var properties = property.split("\\.");
//     for (var p: properties) {
//       try {
//         final var method = clazz.getMethod("get" + StringUtils.capitalize(p));
//         value = method.invoke(value);
//       } catch (Exception e) {
//         e.printStackTrace();
//       }
//     }

//     return value;
//   }

//   private static Map<String, Method> extractMethods(Class<?> clazz, String prefix) {
//     final Map<String, Method> methods = new HashMap<>();
//     while (!clazz.getName().equals(Object.class.getName())) {
//       for (var method: clazz.getDeclaredMethods()) {
//         if (method.getName().startsWith(prefix) && !methods.containsKey(method.getName())) {
//           methods.put(method.getName(), method);
//         }
//       }

//       clazz = clazz.getSuperclass();
//     }

//     return methods;
//   }

//   public static <T> T copy(Object source, T target) {
//     final Map<String, Method> sourceMethods = extractMethods(source.getClass(), "get");
//     final Map<String, Method> targetMethods = extractMethods(target.getClass(), "set");

//     for (var methodName: sourceMethods.keySet()) {
//       try {
//         final var sourceMethod = sourceMethods.get(methodName);
//         final var targetMethodName = methodName.replace("get", "set");
//         final var targetMethod = targetMethods.get(targetMethodName);

//         if (targetMethod == null) {
//           continue;
//         }

//         if (!sourceMethod.canAccess(source) || !targetMethod.canAccess(target)) {
//           continue;
//         }

//         final var value = sourceMethod.invoke(source);
//         targetMethod.invoke(target, value);
//       } catch (Exception e) {
//         logger.error(e);;
//       }
//     }

//     return target;
//   }

//   public static <T> T copyReWrite(Object source, T target) {
//     final Map<String, Method> sourceMethods = extractMethods(source.getClass(), "get");
//     final Map<String, Method> targetMethods = extractMethods(target.getClass(), "set");

//     for (var methodName: sourceMethods.keySet()) {
//       try {
//         final var sourceMethod = sourceMethods.get(methodName);
//         final var targetMethodName = methodName.replace("get", "set");
//         final var targetMethod = targetMethods.get(targetMethodName);

//         if (targetMethod == null) {
//           continue;
//         }

//         if (!sourceMethod.canAccess(source) || !targetMethod.canAccess(target)) {
//           continue;
//         }

//         final var value = sourceMethod.invoke(source);
//         if(value!=null) {
//           targetMethod.invoke(target, value);
//         }
//       } catch (Exception e) {
//         logger.error(e);;
//       }
//     }

//     return target;
//   }
// }

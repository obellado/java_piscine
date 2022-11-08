package app;

import java.util.*;

import Classeses.*;
import java.lang.reflect.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Object[] o = new Object[2];
        o[0] = new User();
        o[1] = new Car();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Classes:");
        System.out.println(o[0].getClass().getSimpleName());
        System.out.println(o[1].getClass().getSimpleName());
        System.out.println("----------------\nEnter class name:");
        String className = scanner.nextLine();
        for (Object value : o) {
            if (value.getClass().getSimpleName().equals(className)) {
                printClass(value);
                Object out = createObject(value, scanner);
                changeObject(out, scanner);
                methodRun(out, scanner);
            }
        }
    }

    public static Object createObject(Object o, Scanner scanner) {
        System.out.println("----------------\nLet's create an object:");
        Constructor<?>[] constructors = o.getClass().getDeclaredConstructors();
        Field[] fields = null;
        Object out = null;
        try {
            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount() == o.getClass().getDeclaredFields().length) {
                    fields = o.getClass().getDeclaredFields();

                    List<Object> newParameters = new ArrayList<>();
                    for (Field p : fields) {
                        System.out.println(p.getName() + ":");
                        System.out.println("Enter " + p.getType().getSimpleName() + " value:");
                        switch (p.getType().getSimpleName()) {
                            case "String": {
                                newParameters.add(scanner.nextLine());
                                break;
                            }
                            case "int":
                            case "Integer": {
                                newParameters.add(scanner.nextInt());
                                scanner.nextLine();
                                break;
                            }
                            case "Boolean": {
                                newParameters.add(scanner.nextBoolean());
                                scanner.nextLine();
                                break;
                            }
                            default:
                                throw new RuntimeException("I dunno this type");
                        }
                    }
                    try {
                        out = constructor.newInstance(newParameters.toArray());
                        System.out.println("Object created: " + out);
                        return out;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static void changeObject(Object out, Scanner scanner) {
        if (out != null) {
            System.out.println("----------------\nEnter name of the field for changing:");
            String fieldName = scanner.nextLine();
            try {
                Field[] fields = out.getClass().getDeclaredFields();
                for (Field p : fields) {
                    if (p.getName().equals(fieldName)) {
                        p.setAccessible(true);
                        System.out.println("Enter " + p.getType().getSimpleName() + " value:");
                        switch (p.getType().getSimpleName()) {
                            case "String": {
                                p.set(out, scanner.nextLine());
                                break;
                            }
                            case "int":
                            case "Integer": {
                                p.set(out, scanner.nextInt());
                                scanner.nextLine();
                                break;
                            }
                            case "Boolean": {
                                p.set(out, scanner.nextBoolean());
                                break;
                            }
                            default:
                                throw new RuntimeException("I dunno this type");
                        }
                        System.out.println("Object changed: " + out);
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void methodRun(Object out, Scanner scanner) {
        System.out.println("----------------\nEnter name of the method for call:");
        Method[] methods = out.getClass().getDeclaredMethods();
        String methodName = scanner.nextLine();
        try {
            for (Method m : methods) {
                String params = null;
                Class<?>[] parameters = m.getParameterTypes();
                for (Class<?> parameter : parameters) {
                    if (params == null)
                        params = parameter.getSimpleName();
                    else
                        params = params + ", " + parameter.getSimpleName();
                }
                if (params == null)
                    params = "";
                String method = m.getName() + '(' + params + ')';
                if (methodName.equals(method)) {
                    List<Object> constructor = new ArrayList<>();
                    Parameter[] params1 = m.getParameters();
                    for (Parameter pam : params1) {
                        System.out.print("Enter " + pam.getType().getSimpleName() + " value:\n");
                        constructor.add(scanParam(pam, scanner));
                    }
                    m.setAccessible(true);
                    if (m.getReturnType().getSimpleName().equals("void")) {
                        m.invoke(out, constructor.toArray());
                    } else {
                        System.out.println("Method return: " + m.invoke(out, constructor.toArray()));
                    }

                }
            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object scanParam(Parameter parameter, Scanner scanner) {
        String paramName = parameter.getType().getSimpleName().toUpperCase(Locale.ROOT);
        try {
            switch (paramName) {
                case "STRING":
                    return scanner.nextLine();
                case "INT":
                case "INTEGER":
                    return scanner.nextInt();
                case "BOOLEAN":
                    return scanner.nextBoolean();
                case "LONG":
                    return scanner.nextLong();
                case "DOUBLE":
                    return scanner.nextDouble();
                default:
                    System.err.println("Error");
                    System.exit(-1);
            }
        } catch (InputMismatchException error) {
            System.err.println("Error");
            System.exit(-1);
        }
        return null;
    }
    public static void printClass(Object o) {
        Class<?> c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        Method[] methods = c.getDeclaredMethods();
        if (fields.length > 0) {
            System.out.println("fields:");
            for (Field f : fields) {
                System.out.println("\t" + f.getType().getSimpleName() + " " + f.getName());
            }
        }
        if (methods.length > 0) {
            System.out.println("methods:");
            for (Method m : methods) {
                System.out.print("\t" + m.getReturnType().getSimpleName() + " " + m.getName() + "(");
                Class<?>[] parameters = m.getParameterTypes();
                for (int i = 0; i < parameters.length; i++) {
                    System.out.print(parameters[i].getSimpleName());
                    if (i < parameters.length - 1)
                        System.out.print(", ");
                }
                System.out.println(")");
            }
        }
    }
}

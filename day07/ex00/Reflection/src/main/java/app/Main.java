package app;

import java.util.*;

import Classeses.*;
import java.lang.reflect.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Object[] o = new Object[2];
        o[0] = new User();
        o[1] = new Car();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Classes:");
        System.out.println(o[0].getClass().getSimpleName());
        System.out.println(o[1].getClass().getSimpleName());
        System.out.println("----------------\nEnter class name:");
        String className = scanner.nextLine();
        for (int i = 0; i < o.length; i++) {
            if (o[i].getClass().getSimpleName().equals(className)) {
                printClass(o[i]);
                createObject(o[i], scanner);
            }
        }
    }
    public static void createObject(Object o, Scanner scanner) {
        System.out.println("----------------\nLet's create an object:");
        Constructor<?>[] constructors = o.getClass().getDeclaredConstructors();
        Parameter[] parameters = null;
        for (int i = 0; i < constructors.length; i++) {
            if (constructors[i].getParameterCount() == o.getClass().getDeclaredFields().length) {
                parameters = constructors[i].getParameters();
                List<Object> newParameters = new ArrayList<>();
                for (Parameter p : parameters) {
                    System.out.println(p.getName() + ":");
                    System.out.println(p.getType().getSimpleName());
                    switch (p.getType().getSimpleName()) {
                        case "String": {
                            newParameters.add(scanner.nextLine());
                            break;
                        }
                        case "int":
                        case "Integer": {
                            newParameters.add(scanner.nextInt());
                            break;
                        }
                        case "Boolean":{
                            newParameters.add(scanner.nextBoolean());
                            break;
                        }
                        default:
                            throw new RuntimeException("I dunno this type");
                    }
                }
                try {
                    Object out = constructors[i].newInstance(newParameters.toArray());
                    System.out.println("Object created: " + out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break ;
            }
        }
    }
    public static void printClass(Object o) {
        Class<?> c = o.getClass();
        Field[] fields = c.getDeclaredFields();
        Method[] methods = c.getDeclaredMethods();
        if (fields.length > 0) {
            System.out.println("fields:");
            for (Field f : fields) {
                System.out.println(f.getType().getSimpleName() + " " + f.getName());
            }
        }
        if (methods.length > 0) {
            System.out.println("methods:");
            for (Method m : methods) {
                System.out.print(m.getReturnType().getSimpleName() + " " + m.getName() + "(");
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

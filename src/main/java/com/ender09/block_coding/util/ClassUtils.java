package com.ender09.block_coding.util;

import org.reflections.Reflections;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class ClassUtils {

    public static <T> Set<Class<? extends T>> findSubclasses(Class<T> clazz) {
        Set<Class<? extends T>> subclasses = new HashSet<>();
        String packageName = clazz.getPackageName();

        Reflections reflections = new Reflections(packageName);
        Set<Class<? extends T>> allClasses = reflections.getSubTypesOf(clazz);

        for (Class<? extends T> discoveredClass : allClasses) {
            if (!Modifier.isAbstract(discoveredClass.getModifiers())) {
                subclasses.add(discoveredClass);
            }
        }

        return subclasses;
    }
}


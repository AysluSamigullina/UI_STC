package part01.lesson09;

import javax.tools.*;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Основной класс
 */
public class Main {

    public static void main(String[] args) throws Exception {
        String path = "src/part01/lesson09/SomeClass.java";
        //Path path1 = Paths.get("part01/lesson09/SomeClass.java");
        //Path rootPath = Paths.get("out/production/UI_STC/part01/lesson09");
        Path rootPath = Paths.get("src/part01/lesson09");

//        Path file = MyClass.write(path, MyClass.read());
//        Path classFile = MyClass.compileSource(file);
//        MyClass.runClass(classFile);

        MyClass.write(path, MyClass.read());

        JavaCompiler jc = ToolProvider.getSystemJavaCompiler();
        jc.run(null, null, null, "src/part01/lesson09/SomeClass.java");

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{rootPath.toUri().toURL()});
        Class<?> cls = Class.forName("part01.lesson09.SomeClass", true, classLoader);
        Worker instance = (Worker) cls.newInstance();
        instance.doWork();
//
//        Class<?> codeGenTest = classLoader.loadClass("CodeGenTest");
//        Method main = codeGenTest.getMethod("main", String[].class);
//        main.invoke(null, new Object[]{null});

//        Class<?> kindClass = myClass.loadClass("part01/lesson09/SomeClass.class");
//        kindClass.getMethod("doWork").invoke(kindClass.newInstance());
//        SomeClass sm = (SomeClass) kindClass.newInstance();
//        sm.doWork();

    }


}

// System.out.println("hello");



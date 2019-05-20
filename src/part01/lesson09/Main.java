package part01.lesson09;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        // MyClass.read();
        String path = "SomeClass.java";
        MyClass myClass = new MyClass();
        MyClass.write(path, MyClass.read());

        //myClass.loadClass(path);


    }
}

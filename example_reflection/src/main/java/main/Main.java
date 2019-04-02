package main;

import adnotari.InjectValue;
import model.Persoana;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws Exception{
        /*Persoana p = new Persoana();
        p.setNume("John");
        p.maresteSalariu();*/

        Class<Persoana> persoanaClass = Persoana.class;
        Constructor<Persoana> constructor = persoanaClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        Persoana p = constructor.newInstance();
        System.out.println(p);

        Field numeField = persoanaClass.getDeclaredField("nume");
        numeField.setAccessible(true);
        numeField.set(p, "john");


        if(numeField.isAnnotationPresent(InjectValue.class)) {
            Annotation declaredAnnotation = numeField.getAnnotation(InjectValue.class);
            String numeNou  = ((InjectValue) declaredAnnotation).numeP();
            numeField.set(p,numeNou);
        }

        Field salariuField = persoanaClass.getDeclaredField("salariu");
        salariuField.setAccessible(true);
        salariuField.set(p, 2000f);

        Method msMethod = persoanaClass.getDeclaredMethod("maresteSalariu");
        msMethod.setAccessible(true);
        msMethod.invoke(p);



        System.out.println(p);

    }
}

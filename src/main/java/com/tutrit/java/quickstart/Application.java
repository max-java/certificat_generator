package com.tutrit.java.quickstart;

import com.tutrit.java.quickstart.bean.Course;
import com.tutrit.java.quickstart.bean.Student;
import com.tutrit.java.quickstart.service.CertPrinter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {
    private static Logger log = LoggerFactory.getLogger("main");

    public static void main(String[] args) throws Exception {
        List<Student> students = List.of(
                new Student("Denis",  "Abramov"),
                new Student("Денис",  "Кайдунов"),
                new Student("Екатерина",  "Булова"),
                new Student("Владислав",  "Коляда"),
                new Student("Александр",  "Мацкевич"),
                new Student("Alexandr",  "Tulai"),
                new Student("Максим",  "Бучинский"),
                new Student("Сергей",  "Асанов"),
                new Student("Александр",  "Каспирович")
        );

        Course course = new Course("jis7", "Java Intensive",
                "enterprise webapp development",
                "09.03.2021",
                "24.07.2021");

        CertPrinter certPrinter = new CertPrinter(course, students);
        certPrinter.run();
    }
}


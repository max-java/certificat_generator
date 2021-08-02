package com.tutrit.java.quickstart.service;

import com.tutrit.java.quickstart.bean.Course;
import com.tutrit.java.quickstart.bean.Student;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.List;
import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.fop.svg.PDFTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class CertPrinter {
    private final Course course;
    private final List<Student> studentList;
    String svgFileName = "./%s.svg";
    String pdfFileName = "./%s.pdf";
    private Logger log = LoggerFactory.getLogger(CertPrinter.class);

    public CertPrinter(Course course, List<Student> studentList) {
        this.course = course;
        this.studentList = studentList;
    }

    public  void run() {
        final String cert = getCert();

        studentList.forEach(student -> {
            String certFile = makeSvg(cert, student);
            convertSvgToPdf(certFile, student);
        });
    }

    private  String getCert() {
        try {
            return new String(new ClassPathResource("JGJavaIntensive.tpl.svg").getInputStream().readAllBytes());
        } catch (Exception e) {
            log.error("error reading cert template", e);
            return null;
        }
    }

    private String makeSvg(String tmpl, Student student) {
        String certFileName = svgFileName.formatted(student.getNameForFile());

        tmpl = tmpl.replace("${dateFrom}", course.getDateFrom());
        tmpl = tmpl.replace("${dateTo}", course.getDateTo());
        tmpl = tmpl.replace("${fullName}", (student.getFullName()));
        tmpl = tmpl.replace("${courseName}", course.getCourseName());
        tmpl = tmpl.replace("${courseTopic}", course.getCourseTopic());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(certFileName))) {
            writer.write(tmpl);
            log.info("Certificate {} has been created", certFileName);
        } catch (Exception e) {
            log.error("error writing cert {}", certFileName, e);
        }
        return certFileName;
    }

    private  void convertSvgToPdf(String sertName, Student student) {
        String certFileName = pdfFileName.formatted(student.getNameForFile());
        try {
            Transcoder transcoder = new PDFTranscoder();
            TranscoderInput transcoderInput = new TranscoderInput(new FileInputStream(new File(sertName)));
            TranscoderOutput transcoderOutput = new TranscoderOutput(new FileOutputStream(new File(certFileName)));
            transcoder.transcode(transcoderInput, transcoderOutput);
            log.info("Certificate {} has been converted", certFileName);
        } catch (Exception e) {
            log.error("error converting cert {}", sertName, e);
        }
    }
}

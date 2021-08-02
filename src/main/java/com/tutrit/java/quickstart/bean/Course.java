package com.tutrit.java.quickstart.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    String group;
    String courseName;
    String courseTopic;
    String dateFrom;
    String dateTo;
}

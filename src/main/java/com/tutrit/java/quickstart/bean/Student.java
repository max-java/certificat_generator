package com.tutrit.java.quickstart.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    String firstName;
    String lastName;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String getNameForFile() {
        return String.format("%s_%s", firstName, lastName);
    }
}

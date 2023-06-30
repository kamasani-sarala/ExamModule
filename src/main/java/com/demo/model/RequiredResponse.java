package com.demo.model;

import java.util.List;

import com.demo.entity.Exam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequiredResponse {
 
	
	private Exam exam;
	private List<Certification> certification;
}

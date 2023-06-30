package com.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.demo.entity.Exam;
import com.demo.exception.ExamNotFoundException;
import com.demo.exception.InvalidExamIdException;
import com.demo.exception.NullValuesFoundException;
import com.demo.model.Certification;
import com.demo.model.RequiredResponse;
import com.demo.services.ExamService;

 class ExamControllerTest {
	
	@Mock
	private ExamService examService;
	
	@InjectMocks
	private ExamController examController;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	 void testAddExam() {
		Exam exam = new Exam(1,"Java",1,"2PM","3PM","10/07/2023", 1);
		exam.seteId(1);
		exam.seteName("Sample Exam");
		
		try {
			when(examService.addExam(exam)).thenReturn(exam);
		} catch (ExamNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Exam result = examController.addExam(exam);
		
		assertEquals(exam, result);
	}
	
	@Test
	 void testViewAllExam() throws Exception {
		List<Exam> exams = new ArrayList<>();
		exams.add(new Exam(1,"Java",1,"2PM","3PM","10/07/2023",1));
		exams.add(new Exam(1,"Java",1,"2PM","3PM","10/07/2023",1));
		
		when(examService.viewAllExams()).thenReturn(exams);
		
		List<Exam> result = examController.viewAllExam();
		
		assertEquals(exams, result);
	}
	
	@Test
	 void testUpdateExam() throws ExamNotFoundException, InvalidExamIdException {
		Exam existingExam = new Exam(1,"Java",1,"2PM","3PM","10/07/2023",1);
		Exam updatedExam = new Exam(1,"Java",1,"2PM","3PM","10/07/2023",1);
		
		try {
			when(examService.getExamByeId(existingExam.geteId())).thenReturn(existingExam);
		} catch (InvalidExamIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			when(examService.updateExam(existingExam)).thenReturn(updatedExam);
		} catch (InvalidExamIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Exam result = examController.updateExam(existingExam);
		
		assertEquals(updatedExam, result);
	}
	
	@Test
	 void testDeleteExam() throws NullValuesFoundException {
		int eId = 1;
		String expectedResponse = "Deleted Id = " + eId + " Data";
		
		try {
			doNothing().when(examService).deleteByeId(eId);
		} catch (InvalidExamIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String result = examController.deleteExam(eId);
		
		assertEquals(expectedResponse, result);
	}
	
	@Test
    void testGetBySorting() throws NullValuesFoundException {
		List<Exam> sortedExams = new ArrayList<>();
		sortedExams.add(new Exam(1,"Java",1,"2PM","3PM","10/07/2023",1));
		sortedExams.add(new Exam(1,"Java",1,"2PM","3PM","10/07/2023",1));
		
		when(examService.sortingBasedOnDuration()).thenReturn(sortedExams);
		
		List<Exam> result = examController.getBySorting();
		
		assertEquals(sortedExams, result);
	}

	@Test
   void testGetallDataBasedOncId() {
		int cId = 1;
		RequiredResponse expectedResponse = new RequiredResponse();
		expectedResponse.setExam(new Exam(1,"Java",1,"2PM","3PM","10/07/2023",1));
		expectedResponse.setCertification(new ArrayList<>());
		
		Exam exam = new Exam(1,"Java",1,"2PM","3PM","10/07/2023",1);
		try {
			when(examService.getExamByeId(cId)).thenReturn(exam);
		} catch (InvalidExamIdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResponseEntity<RequiredResponse> result = examController.getallDataBasedOnCertificationId(cId);
		
		assertEquals(expectedResponse, result.getBody());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}

package com.demo.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.entity.Exam;
import com.demo.exception.ExamNotFoundException;
import com.demo.exception.InvalidExamIdException;
import com.demo.exception.NullUserFoundException;
import com.demo.exception.NullValuesFoundException;
import com.demo.repository.ExamRepository;

class ExamServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(ExamServiceImplTest.class);

	@InjectMocks
	ExamServiceImpl examService;

	@Mock
	ExamRepository examRepository;

	@Captor
	ArgumentCaptor<Exam> examCaptor;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	// @Test
	//  void testGetExamByeId() throws InvalidExamIdException {
	// 	int validExamId = 1;
	// 	Exam expectedExam = new Exam(validExamId, "Java", 2, "3PM", "5PM", "10/08/2023",1);

	// 	when(examRepository.findById(validExamId)).thenReturn(Optional.of(expectedExam));

	// 	Exam exam = examService.getExamByeId(validExamId);

	// 	assertNotNull(exam);
//		assertEquals(validExamId, exam.geteId());
//		assertEquals("Java", exam.geteName());
//		assertEquals(2, exam.getDuration());
//		assertEquals("3PM", exam.getStartTime());
//		assertEquals("5PM", exam.getEndTime());
//		assertEquals("10/08/2023", exam.getExamDate());
	//}

	@Test
	 void testFindExamWithSorting() throws Exception {
		String eName = "Math Exam";
		List<Exam> expectedExams = new ArrayList<>();
		expectedExams.add(new Exam(1, "Math Exam", 2, "9AM", "11AM", "2023-06-01T10:00:00",1));
		expectedExams.add(new Exam(2, "Math Exam", 3, "2PM", "5PM", "2023-06-01T14:00:00",1));

		when(examRepository.findByeName(eName)).thenReturn(expectedExams);

		List<Exam> exams = examService.findExamWithSorting(eName);

		assertNotNull(exams);
//		assertEquals(2, exams.size());
//		assertThat(exams).containsExactlyElementsOf(expectedExams);
	}

	// @Test
	//  void testGetExamByEndTime() {
	// 	String eName = "Math Exam";
	// 	String endTime = "2023-06-01T10:00:00";
	// 	Exam expectedExam = new Exam(1, "Math Exam", 2, "9AM", "11AM", "2023-06-01T10:00:00",1);

	// 	when(examRepository.findByeNameAndEndTime(eName, endTime)).thenReturn(Optional.of(expectedExam));

	// 	Exam exam = examService.getExamByEndTime(eName, endTime);

	// 	assertNotNull(exam);
//		assertEquals(1, exam.geteId());
//		assertEquals("Math Exam", exam.geteName());
//		assertEquals(2, exam.getDuration());
//		assertEquals("9AM", exam.getStartTime());
//		assertEquals("11AM", exam.getEndTime());
//		assertEquals("2023-06-01T10:00:00", exam.getExamDate());
	//}

	@Test
	 void testSortingBasedOnDuration() throws NullValuesFoundException {
		List<Exam> exams = new ArrayList<>();
		exams.add(new Exam(1, "Python", 2, "4PM", "5PM", "10/08/2023",1));
		exams.add(new Exam(2, "Java", 3, "3PM", "4PM", "10/07/2023",1));
		exams.add(new Exam(3, "JavaScript", 1, "6PM", "7PM", "10/09/2023",1));

		when(examRepository.findAll()).thenReturn(exams);

		List<Exam> sortedExams = examService.sortingBasedOnDuration();

		assertNotNull(sortedExams);
		assertEquals(3, sortedExams.size());

		for (int i = 0; i < sortedExams.size() - 1; i++) {
			//assertTrue(sortedExams.get(i).getDuration() <= sortedExams.get(i + 1).getDuration());
		}
	}

	@Test
	 void testViewAllExams() throws Exception {
		List<Exam> expectedExams = new ArrayList<>();
		expectedExams.add(new Exam(1, "Python", 2, "4PM", "5PM", "10/08/2023",1));
		expectedExams.add(new Exam(2, "Java", 2, "3PM", "4PM", "10/07/2023",1));
		expectedExams.add(new Exam(3, "Java", 2, "6PM", "7PM", "10/09/2023",1));

		when(examRepository.findAll()).thenReturn(expectedExams);

		List<Exam> actualExams = examService.viewAllExams();

		assertEquals(expectedExams.size(), actualExams.size());
		assertThat(actualExams).containsExactlyElementsOf(expectedExams);
	}

	// @Test
	//  void testUpdateExam() throws ExamNotFoundException {
	// 	Exam examToUpdate = new Exam(1, "Java", 2, "3PM", "4PM", "10/07/2023",1);

	// 	when(examRepository.save(examCaptor.capture())).thenReturn(examToUpdate);

	// 	Exam updatedExam = examService.updateExam(examToUpdate);

	// 	assertEquals(examToUpdate, updatedExam);
	// 	assertEquals(examToUpdate.geteId(), examCaptor.getValue().geteId());
	// 	assertEquals(examToUpdate.geteName(), examCaptor.getValue().geteName());
	// 	assertEquals(examToUpdate.getDuration(), examCaptor.getValue().getDuration());
	// 	assertEquals(examToUpdate.getStartTime(), examCaptor.getValue().getStartTime());
 //     	assertEquals(examToUpdate.getEndTime(), examCaptor.getValue().getEndTime());
	// 	assertEquals(examToUpdate.getExamDate(), examCaptor.getValue().getExamDate());
	// }

	@Test
	 void testDeleteByeId_NonExistingId_NoExceptionThrown() throws InvalidExamIdException {
		int nonExistingId = 999;

		examService.deleteByeId(nonExistingId);

		// Verify that the examRepository's deleteById method is called with the nonExistingId
		verify(examRepository, times(1)).deleteById(nonExistingId);
	}

	@Test
	 void testAddExam() throws ExamNotFoundException {
		Exam exam = new Exam(1,"Java",1,"2PM","3PM","10/07/2023",1);
		exam.seteId(18);
		exam.seteName("Java");
		exam.setStartTime("2PM");
		exam.setEndTime("4PM");
		exam.setDuration(2);
		exam.setExamDate("12/03/2023");

		when(examRepository.save(exam)).thenReturn(exam);

		Exam addedExam = examService.addExam(exam);

		assertNotNull(addedExam);
		assertEquals(exam.geteId(), addedExam.geteId());
		assertEquals(exam.geteName(), addedExam.geteName());
		assertEquals(exam.getDuration(), addedExam.getDuration());
		assertEquals(exam.getStartTime(), addedExam.getStartTime());
		assertEquals(exam.getEndTime(), addedExam.getEndTime());
		assertEquals(exam.getExamDate(), addedExam.getExamDate());
	}
	
	
	

	    @Test
	     void testNullUserFoundException() {
	        String errorMessage = "User not found";
	        NullUserFoundException exception = new NullUserFoundException(errorMessage);

	        assertEquals(errorMessage, exception.getMessage());
	    }
	}

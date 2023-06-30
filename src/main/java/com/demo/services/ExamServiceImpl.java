package com.demo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.demo.entity.Exam;
import com.demo.exception.ExamNotFoundException;
import com.demo.exception.InvalidExamIdException;
import com.demo.exception.NullValuesFoundException;
import com.demo.repository.ExamRepository;
import com.demo.sorting.SortingBasedOnDuration;


@Service
public class ExamServiceImpl implements ExamService{
 
	@Autowired
	ExamRepository examRepository;
	
		

	@Override
	public List<Exam> viewAllExams() throws ExamNotFoundException{

		List<Exam> allExam = examRepository.findAll(); // Note : same as save
		return allExam;
		
	}

	@Override
	public Exam updateExam(Exam exam)throws NumberFormatException {
	
		Exam e = exam;
		try {
			e = getExamByeId(exam.geteId());
		} catch (InvalidExamIdException e1) {
			
			e1.printStackTrace();
		}
        if (e== null) {
            throw new NumberFormatException("No Exam Exits with same this eid: " + exam.geteId());
        }

 

        return examRepository.save(exam);
    }
	

	@Override
	public void deleteByeId(int eId)throws InvalidExamIdException {

		examRepository.deleteById(eId);
	}
		

	@Override
	public Exam addExam(Exam exams) throws NumberFormatException {
		return examRepository.save(exams);
		
        
	}

	

	@Override
	public Exam getExamByeId(int eId) throws InvalidExamIdException {

		if(eId == 0) {
			throw new InvalidExamIdException("invalid examId is not excepted");
		}
		
		return examRepository.getExambyeId(eId) ;
	}
	

	@Override
	public List<Exam> getExamByDuration(int duration) throws NullValuesFoundException{

		if (duration <= 0) {
            throw new NullValuesFoundException("Duration must be a positive integer.");
        }
        
        List<Exam> matchingExams = new ArrayList<>();

        for (Exam exam : matchingExams) {
            if (exam.getDuration() == duration) {
                matchingExams.add(exam);
            }
        }

        return matchingExams;
   
	}
	

	 List<Exam> getAllExams() {
	    // Replace this with your actual implementation to fetch all exams from a repository or service
	    List<Exam> allExams = new ArrayList<>();
	    // Add your logic to fetch exams and populate the allExams list
	    return allExams;
	}
		

	
	public Exam getExamByEndTime(String eName, String endTime) {
		
		List<Exam> exams = new ArrayList<>();
        for (Exam exam : exams) {
            if (exam.geteName().equals(eName) && exam.getEndTime().equals(endTime)) {
                return exam;
            }
        }
        return null; 
	}
	
	@Override
	public List<Exam> findExamWithSorting(String eName) throws ExamNotFoundException {
	    List<Exam> matchingExams = new ArrayList<>();

	    // Find exams with matching name
	    for (Exam exam : matchingExams) {
	        if (exam.geteName().equals(eName)) {
	            matchingExams.add(exam);
	        }
	    }

	    // Sort exams based on start time using lambda expression
	    matchingExams.sort((exam1, exam2) -> exam1.getStartTime().compareTo(exam2.getStartTime()));

	    return matchingExams;
	}

	@Override
	public List<Exam> sortingBasedOnDuration() throws NullValuesFoundException{
		List<Exam> list = examRepository.findAll();


		Collections.sort(list, new SortingBasedOnDuration());

		return list;
	       
	}

	@Override
	public Object getExams() {
		return null;
	}

	
	}




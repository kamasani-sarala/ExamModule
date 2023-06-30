package com.demo.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.demo.entity.Exam;
import com.demo.exception.ExamNotFoundException;
import com.demo.exception.InvalidExamIdException;
import com.demo.exception.NullValuesFoundException;
import com.demo.model.Certification;
import com.demo.model.RequiredResponse;
import com.demo.repository.ExamRepository;
import com.demo.services.ExamService;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/Exam")
public class ExamController {
	
    private final Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    ExamService examService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    ExamRepository examRepo;

    @GetMapping("/")
    public String defaultMessage() {
        logger.info("Welcome to Exam Page");
        return "Welcome to Exam Page";
    }
//http://localhost:8089/Exam/registerExam
    @PostMapping("/registerExam")
    public Exam addExam(@RequestBody Exam exam) {
        logger.info("Registering a new exam");
        try {
            return examService.addExam(exam);
        } catch (Exception e) {
            logger.error("Failed to register exam", e);
            e.printStackTrace();
        }
        return exam;
    }
  //http://localhost:8089/Exam/allExam
    @GetMapping("/allExam")
    public List<Exam> viewAllExam() throws ExamNotFoundException {
        logger.info("Fetching all exams");
        return examService.viewAllExams();
    }

    @PutMapping("/updateExam")
    public Exam updateExam(@RequestBody Exam existingExam2) throws ExamNotFoundException, InvalidExamIdException {
        Exam existingExam = examService.getExamByeId(existingExam2.geteId());
        if (existingExam == null) {
            throw new ExamNotFoundException("Exam not found");
        }

        // Update the properties of the existing exam
        existingExam.seteName(existingExam2.geteName());
        existingExam.setStartTime(existingExam2.getStartTime());
        existingExam.setEndTime(existingExam2.getEndTime());
        existingExam.setDuration(existingExam2.getDuration());
        existingExam.setExamDate(existingExam2.getExamDate());

        // Call the updateExam() method from the examService to save the changes
        return examService.updateExam(existingExam);
    }


    @DeleteMapping("/delete/{eId}")
    public String deleteExam(@PathVariable int eId)  {
    	logger.info("Deleting exam with ID: {}", eId);

        try {
            examService.deleteByeId(eId);
        } catch (Exception e) {
            logger.error("Failed to delete exam", e);
            e.printStackTrace();
        }
        logger.info("Deleted exam with ID: {}", eId);

        return "Deleted Id = " + eId + " Data";
    }

    @GetMapping("/sort/duration") 
    public List<Exam> getBySorting() throws NullValuesFoundException {
        return examService.sortingBasedOnDuration();
    }
//http://localhost:8089/Exam/cId/1
    @GetMapping("/cId/{cId}")
    public ResponseEntity<RequiredResponse> getallDataBasedOnCertificationId(@PathVariable int cId) {
    	logger.info("Fetching data for ID: {}", cId);

        RequiredResponse requiredResponse = new RequiredResponse();

        Exam exam = examRepo.findById(cId).get();
        requiredResponse.setExam(exam);
        List<Certification> listofcertifications = restTemplate.getForObject("http://CERTIFICATION/certification/cId/"+cId, List.class);
        requiredResponse.setCertification(listofcertifications);

        logger.info("Fetched data for ID: {}", cId);

        return new ResponseEntity<RequiredResponse>(requiredResponse, HttpStatus.OK);
    }
}

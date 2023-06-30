package com.demo.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demo.dto.ExamDto;
import com.demo.entity.Exam;
import com.demo.exception.ExamNotFoundException;
import com.demo.exception.InvalidExamIdException;
import com.demo.exception.NullValuesFoundException;

@Service
public interface ExamService {

    Logger logger = LoggerFactory.getLogger(ExamService.class);

    Exam updateExam(Exam existingExam) throws InvalidExamIdException;

    Exam addExam(Exam exam) throws ExamNotFoundException;

    Exam getExamByeId(int eId) throws InvalidExamIdException;

    List<Exam> getExamByDuration(int duration) throws NullValuesFoundException;

    List<Exam> findExamWithSorting(String eName) throws ExamNotFoundException;

    Exam getExamByEndTime(String eName, String endTime);

    void deleteByeId(int eId) throws InvalidExamIdException;

    List<Exam> viewAllExams() throws ExamNotFoundException;

    List<Exam> sortingBasedOnDuration() throws NullValuesFoundException;

    Object getExams();

    default void logInfo(String message) {
        logger.info(message);
    }

    default void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}

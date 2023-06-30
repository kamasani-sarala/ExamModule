package com.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.entity.Exam;


@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {


	@Query("from Exam e where e.eId=:eId")
	public Exam getExambyeId(@Param(value="eId")Integer eId );

	
	@Query("from Exam e where e.startTime=:startTime and e.endTime=:endTime")
	public Exam findExamBystartTimeEndTime(@Param(value="startTime") String startTime,@Param(value="endTime") String endTime);


	public Object findByeName(String eName);


	public Object findByeNameAndEndTime(String eName, String endTime);

	
}
	

	





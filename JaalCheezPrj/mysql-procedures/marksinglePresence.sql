CREATE DEFINER=`root`@`localhost` PROCEDURE `markSinglePresence`(
IN locationTagId VARCHAR(15), 
IN studentTagId VARCHAR(15), 
IN sweepTime TIME,
IN attShortName varchar(1), 
OUT marked varchar(15))
BEGIN
DECLARE varStudentId varchar(15);
DECLARE varRoomId varchar(15);
DECLARE varPeriodId varchar(15);
DECLARE varCoursePeriodId varchar(15);
DECLARE varTeacherId varchar(15);
DECLARE varAttendanceCode varchar(15);
DECLARE varMarkingPeriodId varchar(15);
DECLARE varDate DATE;
Select student_id into varStudentId from edudb.students where custom_1=studentTagId;
Select room_id into varRoomId from edudb.rooms where tag = locationTagId;
Select course_period_id, period_id into varCoursePeriodId, varPeriodId from edudb.course_period_var where room_id=varRoomId  and sweepTime between edudb.course_period_var.start_time and edudb.course_period_var.end_time;
Select teacher_id into varTeacherId from edudb.course_periods where course_period_id=varCoursePeriodId;
Select marking_period_id into varMarkingPeriodId from edudb.course_periods where course_period_id=varCoursePeriodId;
Select id into varAttendanceCode from edudb.attendance_codes where short_name= attShortName;
Select varAttendanceCode into marked;
Select CURDATE() into varDate;
Insert into attendance_period values(varStudentId, curDate(), varPeriodId, varAttendanceCode, varTeacherId, null, 'Y', varCoursePeriodId, varMarkingPeriodId, null, CURTIME(), null);
END
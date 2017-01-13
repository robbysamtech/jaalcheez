CREATE DEFINER=`root`@`localhost` PROCEDURE `markSinglePresence`(
IN locationTagId VARCHAR(15), 
IN studentTagId VARCHAR(15), 
IN sweepTime TIME,
IN attShortName varchar(1), 
OUT retValue varchar(15))
procYesPath: BEGIN
`DECLARE varRoomId varchar(15);
DECLARE varPeriodId varchar(15);
DECLARE varCoursePeriodId varchar(15);
DECLARE varTeacherId varchar(15);
DECLARE varAttendanceCode varchar(15);
DECLARE varMarkingPeriodId varchar(15);
DECLARE varDoesAttendance varchar(3);
DECLARE varDate DATE;
Select student_id into varStudentId from educatent.students where custom_1=studentTagId;
Select room_id into varRoomId from educatent.rooms where tag = locationTagId;
Select course_period_id, period_id, does_attendance into varCoursePeriodId, varPeriodId, varDoesAttendance from educatent.course_period_var where room_id=varRoomId  and sweepTime between educatent.course_period_var.start_time and educatent.course_period_var.end_time;

if varDoesAttendance <> 'Y' THEN
Select 'FAILURE : 1' into retValue;
LEAVE procYesPath;
end if;

Select teacher_id into varTeacherId from educatent.course_periods where course_period_id=varCoursePeriodId;
Select marking_period_id into varMarkingPeriodId from educatent.course_periods where course_period_id=varCoursePeriodId;
Select id into varAttendanceCode from educatent.attendance_codes where short_name= attShortName;
Insert into attendance_period values(varStudentId, curDate(), varPeriodId, varAttendanceCode, varTeacherId, null, 'Y', varCoursePeriodId, varMarkingPeriodId, null, CURTIME(), null);
Select 'SUCCESS' into retValue;
END
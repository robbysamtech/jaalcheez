package com.jaalsheez.school.base;

import org.json.JSONObject;

public interface IAttendanceManager {
	public JSONObject markSinglePresence();
	public JSONObject markBulkPresence();
	public JSONObject markSingleAbsence();
	public JSONObject markBulkAbsence();
}

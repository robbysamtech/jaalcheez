package com.jaalcheez.school.ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.json.JSONException;
import org.json.JSONObject;

import com.jaalsheez.school.base.ApplicationContext;

@Path("/attndsrvc")
public class Attndsrvc {

	  @POST
	  @Produces("application/json")
	  @Path("/mps")
	  public Response markPresentSingle(
			  @QueryParam("locationTagId") String locationTagId, 
			  @QueryParam("studentTagId") String studentTagId, 
			  @QueryParam("sweepTime") String sweepTime,
			  @QueryParam("mode") String mode) throws JSONException {
		 
		  
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("Status ", "ok"); 
		String result = "@Produces(\"application/json\") Output: Receiving " + jsonObject;
		Connection conn = null;
		try {
			System.out.println(" location tag id : "+locationTagId);
			System.out.println(" student tag id: "+studentTagId);
			System.out.println(" sweeptime : "+sweepTime);
			System.out.println(" mode : "+mode);
			conn = ApplicationContext.getDataManager().getConnection();
			CallableStatement cs = conn.prepareCall("{call edudb.markSinglePresence(?,?,?,?,?)}");
			cs.setString(1, locationTagId);
			cs.setString(2, studentTagId);
			cs.setString(3, sweepTime);
			cs.setString(4, mode);
			cs.registerOutParameter(5, Types.CHAR);
			cs.execute();
			//conn.commit();
			System.out.println("Program ran successfully" +cs.getString(5));
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(200).entity(result+" : "+conn.toString()).build();
	  }
}


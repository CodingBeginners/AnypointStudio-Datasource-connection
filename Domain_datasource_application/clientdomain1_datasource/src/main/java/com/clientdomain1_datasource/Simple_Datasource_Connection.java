package com.clientdomain1_datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import org.enhydra.jdbc.standard.StandardDataSource;
import org.mule.api.MuleContext;
import org.mule.api.context.MuleContextAware;
import org.mule.api.annotations.expressions.Lookup;
import org.springframework.stereotype.Component;


@Component
public class Simple_Datasource_Connection implements MuleContextAware {
	
	@Lookup
	private MuleContext muleContext;
	private StandardDataSource dataSource;
	
	public StandardDataSource getconn()
	{
		return dataSource;
	}
	
	public void setConn(StandardDataSource conn)
	{
		this.dataSource=conn;
	}
	
	
	@Override
	public void setMuleContext(MuleContext context) {
		// TODO Auto-generated method stub
	this.muleContext=context;
	
	}
	
	public String retriveClientData()  throws SQLException
	//public HashMap<String, String> getClient1Data() throws SQLException
	{
		if(this.dataSource==null)
		{
			this.dataSource=this.muleContext.getRegistry().lookupObject("jdbcdatasource");
		}
		

		
		Connection conn=dataSource.getConnection();	
PreparedStatement ps = null;	
		String sql;			
	try {
			
		
/*		Write sql query as per your database. Replace xxx to  table name and column name .	   */	
		
		sql = "select  * from xxx where status='xxx'";
			   
			   ps = conn.prepareStatement(sql);
			   ResultSet rs = ps.executeQuery();
			   
			   List rowValues = new ArrayList();
			   while(rs.next())
			   {
				   
				   int i = 1;
			        while(i <= 20) {
			        	rowValues.add(rs.getString(i++));
			        	
			        	System.out.println("======"+rs.getString(i));
			        }
			
				   System.out.println("rowValues...."+rowValues);
				   
				   System.out.println("Length---"+rs.toString().length());
				   String a1=rs.getString(1);
				   String a2=rs.getString(2);
				   String a3=rs.getString(3);
				   String a4=rs.getString(4);
				   String a5=rs.getString(5);
				
				   
				   
			   System.out.println("Display Data--"+a1+"-"+a2+"-"+a3+"-"+a4+"-"+a5+"-");
				   
				
				ps.execute();
			   }
			  
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			//conn.dataSource.closeDBConnection();
		}
	return null;
	
		
	}
	

}

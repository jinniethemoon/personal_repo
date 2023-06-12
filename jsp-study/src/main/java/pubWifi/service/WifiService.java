package pubWifi.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pubWifi.model.WifiListModel;
import pubWifi.service.MariadbConnection;

public class WifiService {
	
	/**
	 * WIFI 정보 select
	 */
	public List<WifiListModel> list(String myLat, String myLnt) {

				
		List<WifiListModel> wifiList = new ArrayList<>();
//		MyHistoryModel myHistoryModel = new MyHistoryModel();		
		
		System.out.println(myLat + "," +myLnt);
		
		if(myLat !=null & myLnt != null) {
		
			MariadbConnection mariadbConnection = new MariadbConnection();
			Connection connection = mariadbConnection.getConnect();
		
			try {
				
		        String sql = " SELECT "
		        		+ "    ("
		        		+ "      6371 * acos ( "
		        		+ "      cos ( radians(?) ) "
		        		+ "      * cos( radians( lnt ) ) "
		        		+ "      * cos( radians( lat ) - radians(?) ) "
		        		+ "      + sin ( radians(?) ) "
		        		+ "      * sin( radians( lnt ) ) "
		        		+ "    )"
		        		+ " ) AS distance, mrg_no, wrdofc, main_nm, adres1,adres2,instl_floor,instl_ty,instl_mby,svc_se,cmcwr,cnstc_year, "
		        		+ " cnstc_year,inout_door,remars3,lat,lnt,work_dttm"
		        		+ " FROM tb_pb_wifi_info "
		        		+ " ORDER BY distance "
		        		+ " LIMIT 0 , 20;";
		        
		        PreparedStatement preparedStatement = null;
		        ResultSet rs = null;
		        	        
		        preparedStatement = connection.prepareStatement(sql);
		        
		        Double doubLat = Double.parseDouble(myLat);
		        Double doubLnt = Double.parseDouble(myLnt);
		        
		        preparedStatement.setDouble(1, doubLat);
		        preparedStatement.setDouble(2, doubLnt);
		        preparedStatement.setDouble(3, doubLat);
		        
		        rs = preparedStatement.executeQuery();
		        
		        System.out.println(rs);
	
	            while (rs.next()) {
	            	
	            	Double distance = rs.getDouble("distance");
	            	String mrgNo = rs.getString("mrg_no");
	            	String wrdofc = rs.getString("wrdofc");
	            	String wifiNm = rs.getString("main_nm");            	
	            	String adrMain = rs.getString("adres1");
	            	String adrSub = rs.getString("adres2");
	            	
	            	String floor = rs.getString("instl_floor");
	            	String type = rs.getString("instl_ty");
	            	String mby = rs.getString("instl_mby");
	            	
	            	String svcSe = rs.getString("svc_se");
	            	String cmcwr = rs.getString("cmcwr");
	            	
	            	int year = rs.getInt("cnstc_year");
	            	String inout = rs.getString("inout_door");
	            	String remar = rs.getString("remars3");
	            	
	            	Double lat = rs.getDouble("lat");
	                Double lnt = rs.getDouble("lnt");
	                
	                String wkDttm = rs.getString("work_dttm");
	                
	                // 거리 자릿수 변환
	                String StrDis = String.format("%4f", distance);
	    	        
	    	        WifiListModel wifiListResult = new WifiListModel();
	
	    	        wifiListResult.setDistance(StrDis);
	    	        wifiListResult.setMrgNo(mrgNo);
	    	        wifiListResult.setWrdofc(wrdofc);
	    	        wifiListResult.setMainNm(wifiNm);
	    	        wifiListResult.setAdresMn(adrMain);
	    	        wifiListResult.setAdresSb(adrSub);
	    	        wifiListResult.setInstlFloor(floor);
	    	        wifiListResult.setInstlTy(type);
	    	        wifiListResult.setInstlMby(mby);
	    	        wifiListResult.setSvcSe(svcSe);
	    	        wifiListResult.setCmcwr(cmcwr);
	    	        wifiListResult.setCnstcYear(year);
	    	        wifiListResult.setInoutDoor(inout);
	    	        wifiListResult.setRemars(remar);
	    	        wifiListResult.setLat(lat);
	    	        wifiListResult.setLnt(lnt);
	    	        wifiListResult.setWorkDttm(wkDttm);
	                
	    	        wifiList.add(wifiListResult);
	                
	            }
		        
			} catch(SQLException e) {
				
			} finally {
				
				mariadbConnection.close(null, null, connection);
			}
		}
		
		return wifiList;
		
	}

}

package pubWifi.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import pubWifi.dto.MyHistoryDtor;
import pubWifi.model.MyHistoryModel;

import pubWifi.service.MariadbConnection;

public class MyHistoryService {

	/*
	 * 조회한 내 위치 정보 히스토리 저장
	 * */
	
	public void update(String myLat, String myLnt) {
		
		MyHistoryDtor myHistoryDto = new MyHistoryDtor();
		MyHistoryModel myHistoryModel = new MyHistoryModel();
		
		myHistoryDto.setMyLat(myLat);
		myHistoryDto.setMyLnt(myLnt);	
		
		MariadbConnection mariadbConnection = new MariadbConnection();
		Connection connection = mariadbConnection.getConnect();
		
		try {
						
	        String sql = " insert into tb_my_wifi_history  (my_lat, my_lnt) "
	        		+ "values (?, ?); ";
	        
	        PreparedStatement preparedStatement = null;
	        
	        // 스트링 숫자 형변환
	        Double doubLat = Double.parseDouble(myHistoryDto.getMyLat());
	        Double doubLnt = Double.parseDouble(myHistoryDto.getMyLnt());
	        
	        preparedStatement = connection.prepareStatement(sql);
	        
	        preparedStatement.setDouble(1, doubLat);
	        preparedStatement.setDouble(2, doubLnt);;
	        

            int affected = preparedStatement.executeUpdate(); // 업데이트 쿼리 갯수 반환

            if (affected > 0) {
                System.out.println(" 저장 성공 ");
            } else {
                System.out.println(" 저장 실패 ");
            }
		} catch(SQLException e) {
			
			
		} finally {
			
			mariadbConnection.close(null, null, connection);
		}
		
		
		
	}
	
	/**
	 * 조회 히스토리 select
	 */
	public List<MyHistoryModel> list() {
				
		List<MyHistoryModel> myList = new ArrayList<>();
//		MyHistoryDto myHistoryDto = new MyHistoryDto();
//		MyHistoryModel myHistoryModel = new MyHistoryModel();		
		
		
		MariadbConnection mariadbConnection = new MariadbConnection();
		Connection connection = mariadbConnection.getConnect();
		
		try {
						
	        String sql = " select * from tb_my_wifi_history order by ID desc; ";
	        
	        PreparedStatement preparedStatement = null;
	        ResultSet rs = null;
	        	        
	        preparedStatement = connection.prepareStatement(sql);
	        
	        rs = preparedStatement.executeQuery();
	        
	        System.out.println(rs);

            while (rs.next()) {
            	
                int id = rs.getInt("ID");
                Double myLat = rs.getDouble("my_lat");
                Double myLnt = rs.getDouble("my_lnt");
                Timestamp date = rs.getTimestamp("search_date");
                
    	        System.out.println(date);
    	        
                MyHistoryModel myHistory = new MyHistoryModel();
                myHistory.setID(id);
                myHistory.setMyLat(String.valueOf(myLat));
                myHistory.setMyLnt(String.valueOf(myLnt));
                myHistory.setDate(date);
                
                myList.add(myHistory);
                
                
                System.out.println(id + ", " + myLat + ", " + myLnt + ", " + date);
                
            }
	        
	        
		} catch(SQLException e) {
			
			
		} finally {
			
			mariadbConnection.close(null, null, connection);
		}
		
		return myList;
		
	}
	
	/**
	 * 히스토리 삭제 delete
	 * */
	
	public void delete(int id) {
		
//		MyHistoryDto myHistoryDto = new MyHistoryDto();
		MyHistoryModel myHistoryModel = new MyHistoryModel();
		
		myHistoryModel.setID(id);
		
//		myHistoryDto.delete(myHistoryModel);
		
		MariadbConnection mariadbConnection = new MariadbConnection();
		Connection connection = mariadbConnection.getConnect();
		
		try {
						
	        String sql = " delete from tb_my_wifi_history "
	        		+ " where ID = ? ; ";
	        
	        PreparedStatement preparedStatement = null;
	        	        
	        preparedStatement = connection.prepareStatement(sql);
	        
	        preparedStatement.setInt(1, myHistoryModel.getID());
	        

            int affected = preparedStatement.executeUpdate(); // 업데이트 쿼리 갯수 반환

            if (affected > 0) {
                System.out.println(" 삭제 성공 ");
            } else {
                System.out.println(" 삭제 실패 ");
            }
		} catch(SQLException e) {
			
			
		} finally {
			
			mariadbConnection.close(null, null, connection);
		}
		
		
	}

}

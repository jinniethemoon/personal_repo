package pubWifi.dto;

import pubWifi.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import pubWifi.service.MariadbConnection;

public class MyHistoryDto {
	//db 커넥션 & sql 작성
	 
	
	public List<MyHistoryModel> list() {
		
		
		List<MyHistoryModel> myList = new ArrayList<>();
		
		
		MariadbConnection mariadbConnection = new MariadbConnection();
		Connection connection = mariadbConnection.getConnect();
		
		try {
						
	        String sql = " select * from tb_my_wifi_history; ";
	        
	        PreparedStatement preparedStatement = null;
	        ResultSet rs = null;
	        	        
	        preparedStatement = connection.prepareStatement(sql);
	        
	        rs = preparedStatement.executeQuery();
	        
	        System.out.println("rs================");
	        System.out.println(rs);

            while (rs.next()) {
            	
                int id = rs.getInt("ID");
                Double myLat = rs.getDouble("my_lat");
                Double myLnt = rs.getDouble("my_lnt");
                Timestamp date = rs.getTimestamp("search_date");
                
    	        System.out.println("sql 결과물================");
    	        System.out.println(date);
    	        
                MyHistoryModel myHistory = new MyHistoryModel();
                myHistory.setID(id);
                myHistory.setMyLat(String.valueOf(myLat));
                myHistory.setMyLnt(String.valueOf(myLnt));
                myHistory.setDate(date);
                
                myList.add(myHistory);
                
                
                System.out.println("DTO 까지 왔는가?-===================");
                System.out.println(id + ", " + myLat + ", " + myLnt + ", " + date);
                
            }
	        
	        
		} catch(SQLException e) {
			
			
		} finally {
			
			mariadbConnection.close(null, null, connection);
		}
		
		return myList;
		
		
		
	}
	
	// 세팅된 값 가져오기
	public void update(MyHistoryModel myHistoryModel) {

		MariadbConnection mariadbConnection = new MariadbConnection();
		Connection connection = mariadbConnection.getConnect();
		
		try {
						
	        String sql = " insert into tb_my_wifi_history  (my_lat, my_lnt) "
	        		+ "values (?, ?); ";
	        
	        PreparedStatement preparedStatement = null;
	        
	        // 스트링 숫자 형변환
	        Double doubLat = Double.parseDouble(myHistoryModel.getMyLat());
	        Double doubLnt = Double.parseDouble(myHistoryModel.getMyLnt());
	        
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
	
	
	// 세팅된 값 가져오기
	public void delete(MyHistoryModel myHistoryModel) {

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

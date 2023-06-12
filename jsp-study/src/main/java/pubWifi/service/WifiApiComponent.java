package pubWifi.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import pubWifi.dto.WifiListDto;
import pubWifi.dto.WifiListDto.WifiInfoItem;
import pubWifi.model.WifiListModel;
import pubWifi.service.MariadbConnection;


public class WifiApiComponent {
	
	public void getApiresult() {
		
		WifiListDto wifiListDto = new WifiListDto();		
	
		for (int i = 0; i < resultSize/1000+1; i++)
			getWifiApiInfo(i*1000+1,(i+1)*1000);
			
			System.out.println(total);
	}
	
	// 와이파이 정보 사이즈
	public static int resultSize = 0;
	
	// 저장된 와이파이 건수 검증용
	public static int total = 0;
	
	// API 키
	private static final String API_KEY = "464a4645796a696e3533647a4b7247";
	
	
	/**
	 * API 호출
	 */
	
	public static void getWifiApiInfo(int start, int end) {
		 
	    try {
	    	
	    	String url = "http://openapi.seoul.go.kr:8088/" + API_KEY + "/json/TbPublicWifiInfo/" + start + "/" + end + "/";
	 
	        // OkHttp 클라이언트 객체 생성
	        OkHttpClient client = new OkHttpClient();
	 
	        // GET 요청 객체 생성
	        Request.Builder builder = new Request.Builder().url(url).get();
	        
	        Request request = builder.build();
	 
	        // OkHttp 클라이언트로 GET 요청 객체 전송
	        Response response = client.newCall(request).execute();
	        if (response.isSuccessful()) {
	            // 응답 받아서 처리
	            ResponseBody body = response.body();
	            if (body != null) {
	                parseResponse(body.string());
	            }
	        }
	        else
	            System.err.println("Error Occurred");
	 
	        return;
	        
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    
	}
	
	
    public static void parseResponse(String response){
        Gson gson = new Gson();
        WifiListDto wifiListDto = gson.fromJson(response, WifiListDto.class);
        
        resultSize = wifiListDto.getTbPublicWifiInfo().getList_total_count();
        
        insertData(wifiListDto.getTbPublicWifiInfo().getRow());

    }
    
	
	/**
	 * 호출 데이터 저장
	 */
    public static void insertData(WifiInfoItem[] result) {
    	
		MariadbConnection mariadbConnection = new MariadbConnection();
		Connection connection = mariadbConnection.getConnect();
		
		PreparedStatement preparedStatement = null;
		
		int res = 0;
		
        String sql = " insert into tb_pb_wifi_info  (mrg_no, wrdofc, main_nm,"
        		+ " adres1, adres2, instl_floor, instl_ty, instl_mby, "
        		+ " svc_se, cmcwr, cnstc_year, inout_door, remars3, "
        		+ " lat, lnt, work_dttm ) "
        		+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
        
		try {

	        preparedStatement = connection.prepareStatement(sql);
	        
//	        System.out.println(result.length);

	        
    	for (int i = 0; i < result.length; i++) {
    		
	        preparedStatement.setString(1, result[i].getX_SWIFI_MGR_NO());
	        preparedStatement.setString(2, result[i].getX_SWIFI_WRDOFC());
	        preparedStatement.setString(3, result[i].getX_SWIFI_MAIN_NM());
	        
	        preparedStatement.setString(4, result[i].getX_SWIFI_ADRES1());
	        preparedStatement.setString(5, result[i].getX_SWIFI_ADRES2());
	        preparedStatement.setString(6, result[i].getX_SWIFI_INSTL_FLOOR());
	        preparedStatement.setString(7, result[i].getX_SWIFI_INSTL_TY());
	        preparedStatement.setString(8, result[i].getX_SWIFI_INSTL_MBY());
	        
	        preparedStatement.setString(9, result[i].getX_SWIFI_CMCWR());
	        preparedStatement.setString(10, result[i].getX_SWIFI_SVC_SE());
	        preparedStatement.setInt(11, result[i].getX_SWIFI_CNSTC_YEAR());
	        preparedStatement.setString(12, result[i].getX_SWIFI_INOUT_DOOR());
	        preparedStatement.setString(13, result[i].getX_SWIFI_REMARS3());
	        
	        preparedStatement.setDouble(14, result[i].getLAT());
	        preparedStatement.setDouble(15, result[i].getLNT());
	        preparedStatement.setString(16, result[i].getWORK_DTTM());
	        
	        preparedStatement.addBatch();
	        
    	}
	        int[] queryResult = preparedStatement.executeBatch();
	        
	        for (int i = 0; i<queryResult.length; i++) {
	        	if(queryResult[i]==-2) {
	        		res++;
	        	}
	        }
	        
	        if(res==result.length) {
	        	
	        	total += res;
	        	
	        	System.out.println(" 저장 성공 ");
	        	
	        } else {
	        	System.out.println(" 저장 실패 ");
	        }
	        
		} catch(SQLException e) {
			
			
		} finally {
			
			mariadbConnection.close(null, null, connection);
		}
    	
    }
    
    
//	public void insert() {
//		
//		WifiListDto wifiListDto = new WifiListDto();
//		WifiListModel wifiListModel = new WifiListModel();
//
//	}
	
}

package pubWifi.model;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyHistoryModel {
	
	// 내 와이파이 정보 검색시 저장되는 모델
	
	//입력하는 나의 위치 정보
	private String myLat;
	private String myLnt;
	private int ID;
	private Timestamp Date; 

}

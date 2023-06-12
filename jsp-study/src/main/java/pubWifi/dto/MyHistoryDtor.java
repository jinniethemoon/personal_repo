package pubWifi.dto;
import java.sql.Timestamp;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyHistoryDtor {
	
	// 내 와이파이 정보 검색시 저장되는 정보
	// 입력된 나의 위치 정보
	private String myLat;
	private String myLnt;

}

package pubWifi.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WifiListModel {
	
	//공공 와이파이 정보 저장 모델
	// 와이파이 파라미터 명 나열
	
	private String distance;
	
	private String mrgNo;
	private String wrdofc;
	
	private String mainNm;
	private String adresMn;
	private String adresSb;
	
	private String instlFloor;
	private String instlTy;
	private String instlMby;
	
	private String svcSe;
	private String cmcwr;

	private int cnstcYear;
	private String inoutDoor;
	private String remars;
	
	private Double lat;
	private Double lnt;
	private String workDttm;
	

}

package pubWifi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WifiListDto {
	
	WifiInfoResult TbPublicWifiInfo;
	public int totalCount;
		
	@Getter
	@Setter
	public class WifiInfoResult {
		
		int list_total_count;
		Object RESULT;
		public WifiInfoItem[] row;
		
	}
		
	@Getter
	@Setter
	public class WifiInfoItem{
		
		public String X_SWIFI_MGR_NO;
		public String X_SWIFI_WRDOFC;
		public String X_SWIFI_MAIN_NM;
		public String X_SWIFI_ADRES1;
		public String X_SWIFI_ADRES2;
		public String X_SWIFI_INSTL_FLOOR;
		public String X_SWIFI_INSTL_TY;
		public String X_SWIFI_INSTL_MBY;
		public String X_SWIFI_SVC_SE;
		private String X_SWIFI_CMCWR;
		public int X_SWIFI_CNSTC_YEAR;
		public String X_SWIFI_INOUT_DOOR;
		public String X_SWIFI_REMARS3;
		public Double LAT;
		public Double LNT;
		public String WORK_DTTM;
		
	}
	
}

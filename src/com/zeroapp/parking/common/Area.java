
package com.zeroapp.parking.common;

import com.zeroapp.utils.BmapPoint;
import com.zeroapp.utils.JsonTool;


public class Area {
		private int areaid;
		private int cityid;
		private String areaname;
		private BmapPoint[] bmapPointsInstance;
		private String bmapPoints;
		public Area(){
		}
		public Area(int areaid_sql,int cityid_sql,String areaname_sql,String bmapPoints_sql){
			this.areaid = areaid_sql;
			this.cityid = cityid_sql;
			this.areaname = areaname_sql;
			this.bmapPoints = bmapPoints_sql;
		}
		public int getAreaId(){
			return areaid;
		}
		public int getCityId(){
			return cityid;
		}
		public String getAreaName(){
			return areaname;
		}
		public String getBmapPoints(){
			return bmapPoints;
		}
		public BmapPoint[] getbmapPointsInstance(String bmString){
			if(bmString!=null){
				return JsonTool.getCoordinatesOfArea(bmString);
			}else{
				return null;
			}
		}
		public void setAreaId(int ai){
			areaid = ai;
		}
		public void setCityId(int ci){
			cityid = ci;
		}
		public void setAreaName(String an){
			areaname = an;
		}
		public void setBmapPoints(String bp){
			bmapPoints = bp;
		}
}

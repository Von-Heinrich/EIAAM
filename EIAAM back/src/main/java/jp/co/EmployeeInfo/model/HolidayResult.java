package jp.co.EmployeeInfo.model;

public class HolidayResult {
	 	private int holidayResult; // 0: 非假日, 1: 周末, 2: 祝日
	    private int weekDay; // 1: 周一, 2: 周二, ..., 7: 周日
		public int getHolidayResult() {
			return holidayResult;
		}
		public void setHolidayResult(int holidayResult) {
			this.holidayResult = holidayResult;
		}
		public int getWeekDay() {
			return weekDay;
		}
		public void setWeekDay(int weekDay) {
			this.weekDay = weekDay;
		}
		public HolidayResult(int holidayResult, int weekDay) {
			super();
			this.holidayResult = holidayResult;
			this.weekDay = weekDay;
		}
		


}

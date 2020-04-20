
 public interface Task {

	/**
	 * Get the name of the task
	 * 
	 * @return name of task
	 */
	 String getName();
	
	/**
	 * Used to change the name of 
	 * the task at later time
	 * 
	 * @param name - name of task
	 */
	 void setName(String name);

	/**
	 * Gets the Task type
	 *
	 * @return Type of task
	 */
	 String getType();

	/**
	 * Sets the task type
	 *
	 * @param type - type of the task
	 */
	 void setType(String type);

	/**
	 * Gets the start date of the task
	 *
	 * @return start date of the task
	 */
	 int getStartDate();

	/**
	 * Sets the start date of the task
	 *
	 * @param start - task start date
	 */
	 void setStartDate(int start);

	/**
	 * Gets the start time of the task
	 *
	 * @return start time of the task
	 */
	 double getStartTime();


	/**
	 * Sets the start time for the task
	 * 
	 * @param startTime - start time (i.e. 12.5 for 12:30PM)
	 */
	 void setStartTime(double startTime);

	/**
	 * Gets the end date of the task
	 *
	 * @return end date of the task
	 */
	 int getEndDate();

	/**
	 * Sets the end date of the task
	 *
	 * @param endDate - end date
	 */
	 void setEndDate(int endDate);

	/**
	 * Gets the duration of the task
	 *
	 * @return duration of the task
	 */
	 double getDuration();

	/**
	 * Sets the duration of the task
	 *
	 * @param duration - duration of task
	 */
	 void setDuration(double duration);

	/**
	 * Gets the frequency of the task
	 *
	 * @return frequency of the task
	 */
	 int getFrequency();

	/**
	 * Sets the frequency of the task
	 *
	 * @param frequency - frequency should be 1(daily), 7(weekly) or 30(monthly)
	 */
	 boolean setFrequency(int frequency);

	 /**
	  * Gets the start day of the task
	  *
	  * @return start day
	  */
	 int getStartDay();

	 /**
	  * Sets the start day of the task
	  *
	  * @param startDay - start day of task
	  */
	 void setStartDay(int startDay);

	 /**
	  * Gets the start month of the task
	  *
	  * @return start month
	  */
	 int getStartMonth();

	 /**
	  * Sets the start month of the task
	  *
	  * @param startMonth - start month of task
	  */
	 void setStartMonth(int startMonth);

	 /**
	  * Gets the start year of the task
	  *
	  * @return start year
	  */
	 int getStartYear();

	 /**
	  * Sets the start year of the task
	  *
	  * @param startYear - start year of task
	  */
	 void setStartYear(int startYear);

	 /**
	  * Gets the end day of the task
	  *
	  * @return end day
	  */
	 int getEndDay();

	 /**
	  * Sets the start year of the task
	  *
	  * @param endDay - end day of task
	  */
	 void setEndDay(int endDay);

	 /**
	  * Gets the end month of the task
	  *
	  * @return end month
	  */
	 int getEndMonth();

	 /**
	  * Sets the end month of the task
	  *
	  * @param endMonth - end month of task
	  */
	 void setEndMonth(int endMonth);

	 /**
	  * Gets the end year of the task
	  *
	  * @return end year
	  */
	 int getEndYear();

	 /**
	  * Sets the end year of the task
	  *
	  * @param endYear - end year of task
	  */
	 void setEndYear(int endYear);

}

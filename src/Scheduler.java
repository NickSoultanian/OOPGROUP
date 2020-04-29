
public class Scheduler {

    /* 
    *  Time will be adjusted to the nearest fifteen minutes, with the type-handling done by the constructors   
    *  Example code for working with Scheduler in Main: "setStartTime = new Scheduler(this.getStartTime());"
    */

    // Constructor handles a Task of type RecurringTask
    private Scheduler(RecurringTask rt){
        getRoundedStartTime(rt.getStartTime());
        getRoundedStartTime(rt.getEndTime());
    }

    // Constructor handles a Task of type TransientTask
    private Scheduler(TransientTask tt){        
        getRoundedStartTime(tt.getStartTime());
        getRoundedStartTime(tt.getEndTime());
    }    

    // Constructor handles a Task of type AntiTask
    private Scheduler(AntiTask at){                        
        getRoundedStartTime(at.getStartTime());
        getRoundedStartTime(at.getEndTime());
    }    

    // Round to Nearest 
    private double roundNearestFifteenMinutes(double time){
        if (startTime < 15) {
            time = 0;
        } else if (startTime < 30){
            time = 15;
        } else if (startTime < 45){
            time = 30;
        } else {
            time = 45;
        }   
    }

    // Value of Task must be modified to the nearest 15 minutes
	private double getRoundedStartTime(double startTime){  

        // Grab everything after the "." of the double value, i.e the minute value
        String startTimeString = String.valueOf(startTime);
        startTime = Double.parseDouble(startTimeString.substring(startTimeString.indexOf(".") + 1));                
        return roundNearestFifteenMinutes(startTime);        

    }

    // Value of Task must be modified to the nearest 15 minutes
	private double getRoundedEndDate(double endDate){

        // Grab everything after the "." of the double value, i.e the minute value
        String startTimeString = String.valueOf(startTime);
        startTime = Double.parseDouble(startTimeString.substring(startTimeString.indexOf(".") + 1));  
        return roundNearestFifteenMinutes(endDate);   

    }

    /* 
    *  Can be used to check for conflicting times.
    *  Takes two tasks.
    *  Iterate with a for loop in Main, check all Tasks (Recurring, Transient) in the Calendar to see if that Task is a duplicate. 
    */

    // Check newly created RecurringTask for a duplicate
    private boolean checkForDuplicateReccuringTask(RecuringTask firstTask, RecuringTask secondTask){
        if(firstTask.getStartTime() == secondTask.getStartTime()){
            if(firstTask.getEndTime() == secondTask.getEndTime()){
                if(firstTask.getDate() == secondTask.getDate()){
                    if(firstTask.getYear() == secondTask.getYear()){
                        return true;
                    }
                }
            }
        }
    }

    // Check newly created TransientTask for a duplicate
    public boolean checkForDuplicateTransientTask(TransientTask firstTask, TransientTask secondTask){
        if(firstTask.getStartTime() == secondTask.getStartTime()){
            if(firstTask.getEndTime() == secondTask.getEndTime()){
                if(firstTask.getDate() == secondTask.getDate()){
                    if(firstTask.getYear() == secondTask.getYear()){
                        return true;
                    }
                }
            }
        }
    }
}

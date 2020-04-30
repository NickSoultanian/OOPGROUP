public class Scheduler {

    List<Task> tasks = new ArrayList<Tasks>();

    List<Task> reccurringTasks = new ArrayList<Tasks>();

    /* 
    *  Time will be adjusted to the nearest fifteen minutes, with the type-handling done by the constructors   
    *  Example code for working with Scheduler in Main: "setStartTime = new Scheduler(this.getStartTime());"
    */

    // Constructor handles a Task of type RecurringTask
    private Scheduler(RecurringTask rt){ 
        addTask(rt);       
        getRoundedTime(rt.getStartTime());
        getRoundedTime(rt.getEndTime());
    }

    // Constructor handles a Task of type TransientTask
    private Scheduler(TransientTask tt){      
        addTask(tt);  
        getRoundedTime(tt.getStartTime());
        getRoundedTime(tt.getEndTime());
    }    

    // Constructor handles a Task of type AntiTask
    private Scheduler(AntiTask at){
        addTask(at);                        
        getRoundedTime(at.getStartTime());
        getRoundedTime(at.getEndTime());
    }    
    
    private List<Task> getAllTasks(){
        return tasks;
    }

    // TODO: Grab all Tasks for that Day, Month, Week
    private List<Task> getTasksForDay(){
        return tasks;        
    }

    private List<Task> getTasksForMonth(){
        return tasks;
    }

    private List<Task> getTasksForWeek(){
        return tasks;
    }    

    private List<Task> getRecurringTasks(){            
        reccuringTasks = checkForDuplicateReccuringTask(reccuringTasksTemp);    
        return reccurringTasks;
    }

    private void removeTask(Task task){
        tasks.remove(task);
    }

    private void addTask(Task task){
        tasks.add(task);
    }

    // Round to nearest fifteen minutes
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
	private double getRoundedTime(double time){  
        // Grab everything after the "." of the double value, i.e the minute value
        String timeString = String.valueOf(time);
        time = Double.parseDouble(startTimeString.substring(timeString.indexOf(".") + 1));                
        return roundNearestFifteenMinutes(time);        
    }

    /* 
    *  Can be used to check for conflicting times.
    *  Takes a Task List.
    *  Iterate with a for loop in Main, check all Tasks (Recurring, Transient) in the Calendar to see if that Task is a duplicate. 
    */

    // Check all Tasks for any duplicates
    private List<Task> checkForDuplicateReccuringTask(List<Task> tasks){
        // Empty the List each time to avoid floooding the List
        tasks.clear();
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 1; j < tasks.size(); j++) {                                       
                if(tasks.get(i).getStartTime() == secondTask.get(j).getStartTime()){
                    if(firstTask.get(i).getEndTime() == secondTask.get(j).getEndTime()){
                        if(firstTask.get(i).getDate() == secondTask.get(j).getDate()){
                            if(firstTask.get(i).getYear() == secondTask.get(j).getYear()){
                                // Make sure the same task from the duplicate tasks is not added multiple times.
                                if (!tasks.get(i).exists() || !tasks.get(j).exists()) reccuringTasks.add(tasks.get(i));                                                                         
                            }
                        }
                    }
                }
            }
        }
    }
}

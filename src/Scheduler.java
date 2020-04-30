public class Scheduler {

    List<Task> tasks = new ArrayList<Tasks>();

    List<Task> reccurringTasks = new ArrayList<Tasks>();

    /* 
    *   The following constructors can be utilized to create Tasks 
    *   of Type Recurring Task, Transient Task, and Anti-Task.
    *   The class that will create the Scheduler must fill in the constructor in this manner:
    *   "Scheduler <schedulerName> = new Scheduler(new <typeOfTask>, name, recurring, antiTask);"
    */
 
    private Scheduler(RecurringTask rt, String name, int startTime, boolean recurring, boolean antiTask){ 
        addTask(rt, name, startTime, recurring, antiTask);               
    }
    
    private Scheduler(TransientTask tt, String name, int startTime, boolean recurring, boolean antiTask){      
        addTask(tt, name, startTime, recurring, antiTask);  
    }    
 
    private Scheduler(AntiTask at, String name, int startTime, boolean recurring, boolean antiTask){
        addTask(at, name, startTime, recurring, antiTask);                        
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
        reccuringTasks = checkForDuplicateReccuringTask(reccuringTasks);    
        return reccurringTasks;
    }

    private void removeTask(Task task){
        tasks.remove(task);
    }

    private void addTask(Task task, String name, int startTime, boolean recurring, boolean antiTask){
        tasks.add(task);
        task.setName(name);
        task.setStartTime(startTime);
        getRoundedTime(task.getStartTime());
        task.setRecurring(recurring);
        task.setAntiTask(antiTask);        
    }

    // Round to nearest fifteen minutes
    private double roundNearestFifteenMinutes(double time){
        if (time < 15) {
            time = 0;
        } else if (time < 30){
            time = 15;
        } else if (time < 45){
            time = 30;
        } else {
            time = 45;
        } return time;  
    }

    // Value of Task must be modified to the nearest 15 minutes
	private void getRoundedTime(Task task){  
        // Grab everything after the "." of the time (of type double), i.e the minute value
        String timeString = String.valueOf(task.getStartTime());
        time = Double.parseDouble(startTimeString.substring(timeString.indexOf(".") + 1));                
        tasks = roundNearestFifteenMinutes(time);        
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

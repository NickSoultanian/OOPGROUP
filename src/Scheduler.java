public class Scheduler {

    private static List<Task> tasks = new ArrayList<Task>();
    private static List<Task> reccurringTasks = new ArrayList<Task>();

    /* 
    *   The following constructors can be utilized to create Tasks 
    *   of Type Recurring Task, Transient Task, and Anti-Task.
    *   The class that will create the Scheduler must fill in the constructor in this manner:
    *   "Scheduler <schedulerName> = new Scheduler(new <typeOfTask>, name, recurring, antiTask);"
    */
 
    public Scheduler(RecurringTask rt, String name, int startTime, boolean recurring, boolean antiTask){ 
        addTask(rt, name, startTime, recurring, antiTask);               
    }
    
    public Scheduler(TransientTask tt, String name, int startTime, boolean recurring, boolean antiTask){      
        addTask(tt, name, startTime, recurring, antiTask);  
    }    
 
    public Scheduler(AntiTask at, String name, int startTime, boolean recurring, boolean antiTask){
        addTask(at, name, startTime, recurring, antiTask);                        
    }    
    
    public List<Task> getAllTasks(){
        return tasks;
    }

    // TODO: Grab all Tasks for that Day, Month, Week
    public List<Task> getTasksForDay(){
        return tasks;        
    }

    public List<Task> getTasksForMonth(){
        return tasks;
    }

    public List<Task> getTasksForWeek(){
        return tasks;
    }    

    public List<Task> getRecurringTasks(){            
        checkForDuplicateReccuringTask();    
        return reccurringTasks;
    }

    public void removeTask(Task task){
        tasks.remove(task);
    }

    public void addTask(Task task, String name, double startTime, boolean recurring, boolean antiTask){ 
        // Set values in the Task object.       
        task.setName(name);
        task.setStartTime(getRoundedTime(startTime));
        task.setRecurring(recurring);
        task.setAntiTask(antiTask);     
        // Add task to the List of Tasks  
        tasks.add(task);
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

    // Value of Task must be modified to the nearest 15 minutes, e.g, 12.35 will return 12.30
	private double getRoundedTime(double startTime){    
        // Parse double to String in preparation of grabbing its substring.     
        String startTimeString = String.valueOf(startTime);
        // decimalInString is the index of the "." in the String
        int decimalInString = startTimeString.substring(startTimeString.indexOf("."));     
        // Grab everything after the "." of the time (of type double), i.e the minute value, parse to Double before calling the rounding function.
        time = Double.parseDouble(startTimeString.substring(decimalInString + 1));
        // Concatonate the number before the decimal, up until the decimal, with the rest of the value rounded to the nearest fifteen minutes               
        time = startTimeString.substring(0, (decimalInString + 1)) + String.valueOf(roundNearestFifteenMinutes(time)); 
        // Parse back to Double
        time = Double.parseDouble(time);
        return time;       
    }

    /* 
    *  Can be used to check for conflicting times.
    *  Takes a Task List.
    *  Iterate with a for loop in Main, check all Tasks (Recurring, Transient) in the Calendar to see if that Task is a duplicate. 
    */

    // Check all Tasks for any duplicates, return a List of duplicate Tasks (add Task to recurringTasks once if it is a duplicate)
    private void checkForDuplicateTask(List<Task> tasks){
        // Empty the List each time to avoid floooding the List
        tasks.clear();
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 1; j < tasks.size(); j++) {                                       
                if(tasks.get(i).getStartTime() == secondTask.get(j).getStartTime()){
                    if(firstTask.get(i).getEndTime() == secondTask.get(j).getEndTime()){
                        if(firstTask.get(i).getDate() == secondTask.get(j).getDate()){
                            if(firstTask.get(i).getYear() == secondTask.get(j).getYear()){
                                // Make sure the same Task is not added multiple times into the recurringTasks List.
                                if (!recurringTasks.get(tasks.get(i)).exists() || !recurringTasks.get(tasks.get(j)).exists()) reccuringTasks.add(tasks.get(i));                                                                         
                            }
                        }
                    }
                }
            }
        }
    }
}

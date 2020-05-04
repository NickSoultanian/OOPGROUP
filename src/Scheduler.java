import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    public List<Task> tasks = new ArrayList<Task>();

    /* 
    *   The following constructors can be utilized to create Tasks 
    *   of Type Recurring Task, Transient Task, and Anti-Task.
    *   The class that will create the Scheduler must fill in the constructor in this manner:
    *   "Scheduler <schedulerName> = new Scheduler(new <typeOfTask>, name, recurring, antiTask);"
    */


    /**
     * Method to remove task
     * @param newTask - User created task
     */
    public void antiTask(Task newTask)
    {
        //if date and name match
        for(int i =0; i<tasks.size(); i++ ) {
            if (newTask.getStartDate() == tasks.get(i).getStartDate() && tasks.get(i).getName().compareTo(newTask.getName()) == 0) {
                //if start time and duration match
                if (newTask.getStartTime() == tasks.get(i).getStartTime() && newTask.getDuration() == tasks.get(i).getDuration()) {
                    tasks.remove(i);
                }
            }
        }
    }

    /**
     * Takes user input, creates transient, anti, or recurring task.
     *
     * @param name      - identifier
     * @param type      - what kind of task
     * @param startDate - day task starts
     * @param startTime - time task starts
     * @param duration  - length of task
     * @param endDate   - day task ends
     * @param frequency - frequency of task
     * @param recurring - boolean flag for type of task
     * @param antiTask  - boolean flag for type of task
     */
    public void addTask(String name, String type, int startDate, double startTime, double duration, int endDate, int frequency, boolean recurring, boolean antiTask)
    {

        Task newTask;

        if(recurring){
            newTask = new RecurringTask(name, type, startDate, startTime, duration, endDate, frequency );
            newTask.setStartTime(getRoundedTime(startTime));
            recurringTasks(newTask);

        }

        if(antiTask){
            newTask = new AntiTask(name, startDate, startTime, duration);
            newTask.setStartTime(getRoundedTime(startTime));
            antiTask(newTask);
        }

        else{
            newTask = new TransientTask(name, type, startDate, startTime, duration);
            newTask.setStartTime(getRoundedTime(startTime));
            checkForDuplicateTask(newTask);

            //for transient tasks
        }

        newTask.setStartTime(getRoundedTime(startTime));
    }

    /**
     * Rounds minutes to appropriate values
     *
     * @param time  - just minutes of the time passed in
     * @return      - returns appropriate values of minutes
     */
    public static int roundNearestFifteenMinutes(int time)
    {
        if (time < 15) {
            time = 00;
        } else if (time < 30){
            time = 15;
        } else if (time < 45){
            time = 30;
        } else {
            time = 45;
        }
        return time;
    }

    /**
     * Takes in start time, parses minutes to ensure validity, then concatenates
     *
     * @param startTime - task start time
     * @return          - returns a valid start time
     */
    public static double getRoundedTime(double startTime)
    {
        DecimalFormat df = new DecimalFormat("##.##");
        String time;
        double finalTime;
        // Parse double to String in preparation of grabbing its substring.
        String startTimeString = String.valueOf(startTime);
        // decimalInString is the index of the "." in the String
        int decimalInString = Integer.parseInt(startTimeString.substring(startTimeString.indexOf(".")+1));
        System.out.println(decimalInString);
        time = Integer.parseInt(startTimeString.substring(0,2)) + "." + df.format(roundNearestFifteenMinutes(decimalInString));
        System.out.println(time + " = time");
        // Parse back to Double
        finalTime = Double.parseDouble(time);

        return finalTime;
    }

    /* 
    *  Can be used to check for conflicting times.
    *  Takes a Task List.
    *  Iterate with a for loop in Main, check all Tasks (Recurring, Transient) in the Calendar to see if that Task is a duplicate. 
    */


    //Check all tasks in list for any conflicts in days and times,
    //  if no conflicts, add the task to list
    //TODO : figure out what to return here to user, if task conflict arises and task is not added to list.
    private boolean checkForDuplicateTask(Task newTask) {
        //this only needs to be ran once to check if possible conflicts with new task and
        boolean flag = true;
        for (int i = 0; i < tasks.size(); i++) {
            if (fallsOnDay(newTask, i)) {
                if (fallsWithinTimeConstraint(newTask, i)) {
                    flag = false;
                }
            }
        }
        if(flag == true){
            tasks.add(newTask);
            return true;
        }
        return false;
    }


    //Flag to check if newTask day matches with any task day
    //
    private boolean fallsOnDay(Task newTask, int index)
    {
        double newTaskDay = newTask.getStartDate();
        double oldTaskDay = tasks.get(index).getStartDay();
        if(newTaskDay == oldTaskDay){
            return true;
        }
        else{
            return false;
        }
    }

    //Flag to check if newTask duration intersects with any already created task's duration
    //
    private boolean fallsWithinTimeConstraint(Task newTask, int index)
    {
        double newTaskEndTime = newTask.getDuration()+newTask.getStartTime();
        double oldTaskEndTime = tasks.get(index).getStartTime()+tasks.get(index).getDuration();

        //oldtask start < new task start && new task start < old task end
        if(tasks.get(index).getStartTime() < newTask.getStartTime() && newTask.getStartTime() < oldTaskEndTime){
            return true;
        }

        //oldtask start < new task end && new task end < old task end
        if(tasks.get(index).getStartTime()<newTaskEndTime&&newTaskEndTime<oldTaskEndTime){
            return true;
        }

        //If new task start time < old start time && new task end time > old end time
        if(newTask.getStartTime() < tasks.get(index).getStartTime() && oldTaskEndTime < newTaskEndTime){
            return true;
        }

        //otherwise, times don't conflict, return true
        return false;
    }

    //Method to create recurring tasks
    //
    private void recurringTasks(Task newTask){

        Task newTask1 = newTask;

        Task task;
        if(newTask1.getFrequency() == 1){
            while (newTask1.getStartDate() < newTask1.getEndDate()){
                task = newTask1;

                if(!checkForDuplicateTask(task)){
                    //TODO Make better way to notify
                    System.err.println("Task: " + task.getName() + " on " + task.getStartDate() + "conflicts with another task");
                }

                if(newTask1.getStartMonth() == 4 || newTask1.getStartMonth() == 6|| newTask1.getStartMonth() == 9 || newTask1.getStartMonth() == 11){
                    if(newTask1.getStartDay() == 30){
                        newTask1.setStartDay(1);
                        newTask1.setStartMonth(newTask1.getStartMonth() + 1);
                    }
                    else {
                        newTask1.setStartDay(newTask1.getStartDay() + 1);
                    }
                }

                else if(newTask1.getStartMonth() == 12){
                    if(newTask1.getStartDay() == 31){
                        newTask1.setStartDay(1);
                        newTask1.setStartMonth(1);
                        newTask1.setStartYear(newTask1.getStartYear() + 1);
                    }
                    else {
                        newTask1.setStartDay(newTask1.getStartDay() + 1);
                    }
                }

                else if(newTask1.getStartMonth() == 2){
                    if(newTask1.getStartDay() == 28){
                        newTask1.setStartDay(1);
                        newTask1.setStartMonth(newTask1.getStartMonth() + 1);
                    }
                    else {
                        newTask1.setStartDay(newTask1.getStartDay() + 1);
                    }
                }

                else{
                    if(newTask1.getStartDay() == 31){
                        newTask1.setStartDay(1);
                        newTask1.setStartMonth(newTask1.getStartMonth() + 1);
                    }
                    else {
                        newTask1.setStartDay(newTask1.getStartDay() + 1);
                    }
                }
            }
        }
        else if(newTask1.getFrequency() == 7){
            while(newTask1.getStartDate() < newTask1.getEndDate()){
                task = newTask1;

                if(!checkForDuplicateTask(task)){
                    //TODO Make better way to notify
                    System.err.println("Task: " + task.getName() + " on " + task.getStartDate() + "conflicts with another task");
                }

                if(newTask1.getStartMonth() == 4 || newTask1.getStartMonth() == 6|| newTask1.getStartMonth() == 9 || newTask1.getStartMonth() == 11){
                    if(newTask1.getStartDay() > 23){
                        newTask1.setStartDay((newTask1.getStartDay() + 7) - 30);
                        newTask1.setStartMonth(newTask1.getStartMonth() + 1);
                    }
                    else {
                        newTask1.setStartDay(newTask1.getStartDay() + 7);
                    }
                }


                else if(newTask1.getStartMonth() == 12){
                    if(newTask1.getStartDay() > 24){
                        newTask1.setStartDay((newTask1.getStartDay() + 7) - 31);
                        newTask1.setStartMonth(1);
                        newTask1.setStartYear(newTask1.getStartYear() + 1);
                    }
                    else {
                        newTask1.setStartDay(newTask1.getStartDay() + 7);
                    }
                }


                else if(newTask1.getStartMonth() == 2){
                    if(newTask1.getStartDay() > 21){
                        newTask1.setStartDay((newTask1.getStartDay() + 7) - 28);
                        newTask1.setStartMonth(newTask1.getStartMonth() + 1);
                    }
                    else {
                        newTask1.setStartDay(newTask1.getStartDay() + 7);
                    }
                }

                else{
                    if(newTask1.getStartDay() > 24){
                        newTask1.setStartDay((newTask1.getStartDay() + 7) - 31);
                        newTask1.setStartMonth(newTask1.getStartMonth() + 1);
                    }
                    else {
                        newTask1.setStartDay(newTask1.getStartDay() + 7);
                    }
                }
            }
        }
        else if (newTask1.getFrequency() == 30){
            while(newTask1.getStartDate() < newTask1.getEndDate()){
                task = newTask1;

                if(!checkForDuplicateTask(task)){
                    //TODO Make better way to notify
                    System.err.println("Task: " + task.getName() + " on " + task.getStartDate() + "conflicts with another task");
                }

                newTask1.setStartMonth(newTask1.getStartMonth() + 1);

                if(newTask1.getStartMonth() == 12){
                    newTask1.setStartMonth(1);
                    newTask1.setStartYear(newTask1.getStartYear() + 1);
                }
            }
        }
    }
}

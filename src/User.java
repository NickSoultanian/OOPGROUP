
public class User {

    private Scheduler scheduler = new Scheduler();

    public void addtransient(String name, String type, int startDate, double startTime, double duration)
    {
        scheduler.addTask(name,type,startDate,startTime,duration,startDate+1,0,false,false);

    }

    public void addrecurring(String name, String type, int startDate, double startTime, double duration,int endDate, int frequency)
    {
        scheduler.addTask(name,type,startDate,startTime,duration,endDate,frequency,true,false);
    }

    public void antitask(String name, int startDate, double startTime, double duration)
    {

        scheduler.addTask(name,"Cancellation",startDate,startTime,duration,startDate,0,false,true);
    }

    public void viewTask(String name)
    {
        int index = scheduler.tasks.indexOf(name);
        if(index!=-1)
        {
            System.out.println(scheduler.tasks.get(index).getName());
            System.out.println(scheduler.tasks.get(index).getType());
            System.out.println(scheduler.tasks.get(index).getStartDate());
            System.out.println(scheduler.tasks.get(index).getStartTime());
            System.out.println(scheduler.tasks.get(index).getDuration());
            System.out.println(scheduler.tasks.get(index).getEndDate());
            System.out.println(scheduler.tasks.get(index).getFrequency());

        }
    }

    public Task returnTask(String name)
    {
        int index = scheduler.tasks.indexOf(name);
        if(index != -1)
        {
            return scheduler.tasks.get(index);
        }
        else return null;
    }

    public void deletetask(String name)
    {
        int index = scheduler.tasks.indexOf(name);
        if(index!=-1)
        {
            scheduler.tasks.remove(index);
        }
    }


    public void modifyName(String name, String modification)
    {
        int index= scheduler.tasks.indexOf(name);
        if(index!=-1)
        {
            scheduler.tasks.get(index).setName(modification);

        }
    }

    public void modifyType(String name, String modification)
    {
        int index= scheduler.tasks.indexOf(name);
        if(index!=-1)
        {
            scheduler.tasks.get(index).setName(modification);

        }
    }

    public void modifyStartdate(String name, int modification)
    {
        int index= scheduler.tasks.indexOf(name);
        if(index!=-1)
        {
            scheduler.tasks.get(index).setStartDate(modification);

        }
    }

    public void modifystartTime(String name, double modification)
    {
        int index= scheduler.tasks.indexOf(name);
        if(index!=-1)
        {
            scheduler.tasks.get(index).setStartTime(modification);

        }
    }

    public void modifyduration(String name, double modification)
    {
        int index= scheduler.tasks.indexOf(name);
        if(index!=-1)
        {
            scheduler.tasks.get(index).setDuration(modification);

        }
    }

    public void modifyendDate(String name, int modification)
    {
        int index= scheduler.tasks.indexOf(name);
        if(index!=-1)
        {
            scheduler.tasks.get(index).setEndDate(modification);

        }
    }

    public void modifyFrequency(String name, int modification)
    {
        int index= scheduler.tasks.indexOf(name);
        if(index!=-1)
        {
            scheduler.tasks.get(index).setFrequency(modification);

        }
    }

}



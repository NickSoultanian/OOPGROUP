public class AntiTask implements Task {

    //Name of the event
    private String name = null;

    //Type of the task
    String type = null;

    //Start date split into its components for easier manipulation
    private String startDay = null;
    private String startMonth = null;
    private String startYear = null;

    // Start date as a int for required output
    private int startDate = 0;

    //Start time as a double for required output
    private double startTime = 0;

    //Duration of task as a double
    private double duration = 0;

    public AntiTask(String name, int startDate, double startTime, double duration) {

        this.name = name;
        this.type = "Cancellation";
        this.startDate = startDate;
        this.startTime = startTime;
        this.duration = duration;

        splitStartDate();

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        System.out.println(this.name + " Is an AntiTask and type must be 'Cancellation' ");
    }

    @Override
    public int getStartDate() {
        compileStartDate();
        return startDate;
    }

    @Override
    public void setStartDate(int startDate) {
        this.startDate = startDate;
        splitStartDate();
    }

    @Override
    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    @Override
    public int getEndDate() {
        return 0;
    }

    @Override
    public void setEndDate(int endDate) {

    }

    @Override
    public double getDuration() {
        return duration;
    }

    @Override
    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public int getFrequency() {
        System.out.println(this.name + " Is a Transient Task and does not use getFrequency");
        return 0;
    }

    @Override
    public boolean setFrequency(int frequency) {
        System.out.println(this.name + " Is a Transient Task and does not use getFrequency, nothing has changed");
        return false;
    }

    @Override
    public int getStartDay() {
        return Integer.parseInt(startDay);
    }

    @Override
    public void setStartDay(int startDay) {
        if (startDay > 9)
            this.startDay = String.valueOf(startDay);
        else
            this.startDay = String.format("%02d", startDay);
    }

    @Override
    public int getStartMonth() {
        return Integer.parseInt(startMonth);
    }

    @Override
    public void setStartMonth(int startMonth) {
        if (startMonth > 9)
            this.startMonth = String.valueOf(startMonth);
        else
            this.startMonth = String.format("%02d", startMonth);
    }

    @Override
    public int getStartYear() {
        return Integer.parseInt(startYear);
    }

    @Override
    public void setStartYear(int startYear) {
        this.startYear = String.valueOf(startYear);
    }

    @Override
    public int getEndDay() {
        System.out.println(this.name + " Is a  Anti-Task and does not use getEndDay");
        return 0;
    }

    @Override
    public void setEndDay(int endDay) {
        System.out.println(this.name + " Is a Anti-Task and does not use setEndDay, nothing has changed");
    }

    @Override
    public int getEndMonth() {
        System.out.println(this.name + " Is a Anti-Task and does not use getEndMonth");
        return 0;
    }

    @Override
    public void setEndMonth(int endMonth) {
        System.out.println(this.name + " Is a Anti-Task and does not use setEndMonth, nothing has changed");
    }

    @Override
    public int getEndYear() {
        System.out.println(this.name + " Is a Anti-Task and does not use getEndYear");
        return 0;
    }

    @Override
    public void setEndYear(int endYear) {
        System.out.println(this.name + " Is a Anti-Task and does not use setEndYear, nothing has changed");
    }

    private void splitStartDate() {
        String stringDate = String.valueOf(startDate);
        startYear = stringDate.substring(0, 4);
        startMonth = stringDate.substring(4, 6);
        startDay = stringDate.substring(6, 8);
    }

    private void compileStartDate() {
        String stringDate;
        stringDate = String.valueOf(startDay);
        stringDate = String.valueOf(startMonth).concat(stringDate);
        stringDate = String.valueOf(startYear).concat(stringDate);
        startDate = Integer.parseInt(stringDate);
    }

    public static void main(String args[]){

        Task task = new AntiTask("test", 20201106, 5.25, 0.25);

        System.out.println(task.getName());
        System.out.println(task.getType());
        System.out.println(task.getStartDate());
        System.out.println(task.getStartTime());
        System.out.println(task.getDuration());
        System.out.println(task.getStartMonth());
        System.out.println(task.getStartDay());
        System.out.println(task.getStartYear());

        task.setEndDay(15);
    }
}

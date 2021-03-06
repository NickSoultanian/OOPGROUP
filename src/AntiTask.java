public class AntiTask implements Task {

    //Name of the event
    private String name = null;

    //Type of task
    String type = null;

    //Start date split into its components for easier manipulation
    private String startDay = null;
    private String startMonth = null;
    private String startYear = null;

    // Start date as a int for easier output
    private int startDate = 0;

    //Start time as a double for easier output
    private double startTime = 0;

    //End date will be set to the same as start day for Anti-Task
    private String endDay = null;
    private String endMonth = null;
    private String endYear = null;

    //End date will be set to the same as start day for Anti-Task
    private int endDate = 0;

    //Duration of task
    private double duration = 0;

    //Anti-Tasks do not use frequency, this variable is to follow the interface
    private int frequency = 0;

    public AntiTask(String name, int startDate, double startTime, double duration) {

        this.name = name;
        this.type = "Cancellation";
        this.startDate = startDate;
        this.startTime = startTime;
        this.duration = duration;
        this.endDate = startDate;

        splitStartDate();
        splitEndDate();

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
        System.err.println(this.name + " Is an AntiTask, type must be 'Cancellation' ");
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
        compileEndDate();
        return endDate;
    }

    @Override
    public void setEndDate(int endDate) {
        this.endDate = endDate;
        splitEndDate();
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
        return frequency;
    }

    @Override
    public boolean setFrequency(int frequency) {
        System.out.println(this.name + " is a Anti-task and must have a frequency of 0");
        return true;
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
        return Integer.parseInt(endDay);
    }

    @Override
    public void setEndDay(int endDay) {
        if(endDay > 9)
            this.endDay = String.valueOf(endDay);
        else
            this.endDay = String.format("%02d", endDay);
    }

    @Override
    public int getEndMonth() {
        return Integer.parseInt(endMonth);
    }

    @Override
    public void setEndMonth(int endMonth) {
        if(endMonth > 9)
            this.endMonth = String.valueOf(endMonth);
        else
            this.endMonth = String.format("%02d", endMonth);
    }

    @Override
    public int getEndYear() {
        return Integer.parseInt(endYear);
    }

    @Override
    public void setEndYear(int endYear) {
        this.endYear = String.valueOf(endYear);
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

    private void splitEndDate(){
        String stringDate = String.valueOf(endDate);
        endYear = stringDate.substring(0, 4);
        endMonth = stringDate.substring(4, 6);
        endDay = stringDate.substring(6, 8);
    }

    private void compileEndDate(){
        String stringDate;
        stringDate = String.valueOf(endDay);
        stringDate = String.valueOf(endMonth).concat(stringDate);
        stringDate = String.valueOf(endYear).concat(stringDate);
        endDate = Integer.parseInt(stringDate);
    }
}

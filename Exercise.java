public class Exercise {
    private String name;
    private String muscle;
    private String[] daysOfWeek = new String[] { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            "Sunday" };
    private int setNum;
    private int repNum;
    private int day;

    public Exercise(String name, String muscle, int day, int setNum, int repNum) {
        setName(name);
        setMuscle(muscle);
        setDay(day);
        setSetNum(setNum);
        setRepNum(repNum);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscle() {
        return this.muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String[] getDaysOfWeek() {
        return this.daysOfWeek;
    }

    public void setDaysOfWeek(String[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public int getSetNum() {
        return this.setNum;
    }

    public void setSetNum(int setNum) {
        this.setNum = setNum;
    }

    public int getRepNum() {
        return this.repNum;
    }

    public void setRepNum(int repNum) {
        this.repNum = repNum;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

}

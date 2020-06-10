package JavaFX;


public class Time {
    
private int years;
private int months;
private int days;
private int hours;
private int minutes;


    public Time(int months, int days, int hours, int minutes) {
        this.months = months;
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
         if(months>12 || months<1)
        {
            throw new NullPointerException();
        }
        if(days>31 || days<1)
        {
            throw new NullPointerException();
        }
        if(hours>24 || hours<1)
        {
            throw new NullPointerException();
        }
        if(minutes>59 || minutes<0)
        {
            throw new NullPointerException();
        }
    }
    public Time(int years, int months, int days, int hours, int minutes) {
        this(months, days, hours, minutes);
        this.years=years;
        if(years<0) throw new NullPointerException();
    }
    
    @Override
    public String toString() {
        return "Time: " + years + "-" + months + "-" + days + " " + hours + ":" + minutes;
    }
    
    public int valueForComparing()
    {
        int value;
        value=years*12*31*24*60+months*31*24*60+days*24*60+hours*60+minutes;
        return value;
    }
    
    public void addHours(int hours)
    {
        int time=hours;
        this.hours=this.hours+time;
        if(this.hours>24)
        {
            this.days=this.days+this.hours/24;
            if(this.days>31)
            {
                this.months=this.months+this.days/31;
                this.days=this.days%31;
            if(this.months>12)
            {
                this.years=this.years+this.months/12;
                this.months=this.months%12;
            }
            }
            this.hours=this.hours%24;
            
        }
    }
    public void addDays(int days)
    {
        this.days=this.days+days;
        if(this.days>31)
        {
            this.months=this.months+this.days/31;
            if(this.months>12)
            {
                this.years=this.years+this.months/12;
                this.months=this.months%12;
            }
            
            this.days=this.days%31;
            
        }
    }
    public void addMonths(int months)
    {
        this.months=this.months+months;
        if(this.months>12)
        {
            this.years=this.years+this.months/12;
            this.months=this.months%12;
            
        }
    }

    public int getYears() {
        return years;
    }

    public void addYears(int tmp) {
        years+=tmp;
    }

    
    
}

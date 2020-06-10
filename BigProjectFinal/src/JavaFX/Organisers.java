package JavaFX;


public class Organisers {
    
    private int arraySize = 10;
    private String organisersName;
    private Event[] previousEvents=new Event[arraySize];
    private boolean[] previousEventsB = new boolean[arraySize];
    private int previousEventsAmount=0;
    private Event[] events=new Event[arraySize];
    private boolean[] eventsB = new boolean[arraySize];
    private int eventsAmount=0;
    private int allEventsAmount=0;
    private Event[] allEvents=new Event[arraySize];
   
    
    public Organisers(String organisersName) {
        this.organisersName = organisersName;
    }

    public boolean[] getPreviousEventsB() {
        return previousEventsB;
    }

    public void setPreviousEventsB(boolean[] previousEventsB) {
        this.previousEventsB = previousEventsB;
    }

    public boolean[] getEventsB() {
        return eventsB;
    }

    public void setEventsB(boolean[] eventsB) {
        this.eventsB = eventsB;
    }
    /**
     * Rewrites arrays with allocated memory increased by 10
     */
    private void increaseArraySizes()
    {
        Event[] tempP = new Event[this.arraySize+10];
        Event[] tempN = new Event[this.arraySize+10];
        Event[] tempA = new Event[this.arraySize+10];
        boolean[] tempNB = new boolean[this.arraySize+10];
        boolean[] tempPB = new boolean[this.arraySize+10];
        
        if(this.arraySize - this.allEventsAmount<5)
        {
            for(int sk=0; sk<this.previousEventsAmount; sk++)
            {
                tempP[sk]=this.previousEvents[sk];
                tempPB[sk]=this.previousEventsB[sk];
            }
            for(int sk=0; sk<this.eventsAmount; sk++)
            {
                tempN[sk]=this.events[sk];
                tempNB[sk]=this.eventsB[sk];
            }
            for(int sk=0; sk<this.allEventsAmount; sk++)
            {
                tempA[sk]=this.allEvents[sk];
                
            }
            this.previousEvents = tempP;
            this.previousEventsB = tempPB;
            this.events = tempN;
            this.eventsB = tempNB;
            this.allEvents = tempA;
            this.arraySize+=10;
        }
        
    }
    
    

    public int getAllEventsAmount() {
        return allEventsAmount;
    }

    public Event[] getAllEvents() {
        return allEvents;
    }

    
    
    public Event[] getPreviousEvents() {
        return previousEvents;
    }

    public void setPreviousEvents(Event[] previousEvents) {
        this.previousEvents = previousEvents;
    }

    public int getPreviousEventsAmount() {
        return previousEventsAmount;
    }

    public void setPreviousEventsAmount(int previousEventsAmount) {
        this.previousEventsAmount = previousEventsAmount;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public int getEventsAmount() {
        return eventsAmount;
    }

    public void setEventsAmount(int eventsAmount) {
        this.eventsAmount = eventsAmount;
    }
/**
 * Methods that are adding events for organizers
 * Methods are called from storage class
 * @param event 
 */
    public void addPreviousEvent(Event event)
    {
        previousEvents[previousEventsAmount]=event;
        previousEventsB[previousEventsAmount]=false;
        previousEventsAmount++;
        addAllEvent(event);
    }
    
    public void addAllEvent(Event event)
    {
        allEvents[allEventsAmount]=event;
        allEventsAmount++;
        increaseArraySizes();
    }
    
     public void addEvent(Event event)
    {
        events[eventsAmount]=event;
        eventsB[eventsAmount]=true;
        eventsAmount++;
        addAllEvent(event);
    }

    public String getOrganisersName() {
        return organisersName;
    }

    public void setOrganisersName(String organisersName) {
        this.organisersName = organisersName;
    }

    
    
    
    
}

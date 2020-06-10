
package JavaFX;

public class Storage {
    
    private int arraySize = 10;
    private int arraySizeOrganisers = 10;
   private Event[] events=new Event[arraySize];
    private int eventCount=0;
    private int oldEventCount=0;
    private int newEventCount=0;
    private Time time=new Time(2020,3,30,9,16);
    private Event[] pastEvents = new Event[arraySize];
    private Event[] newEvents = new Event[arraySize];
    Organisers[] organisers=new Organisers[arraySizeOrganisers];
    private int organisersCount=0;
    

    public Time getTime() {
        return time;
    }

    public String timeToString () {
        return time.toString();
    }
    
    /**
     * Increases Organizers array size by 10
     */
    private void increaseOrganiserArraySize()
    {
        Organisers[] temp= new Organisers[this.arraySizeOrganisers+10];
        if(this.arraySizeOrganisers-this.organisersCount<5)
        {
            for(int sk=0; sk<organisersCount; sk++)
            {
                temp[sk]=this.organisers[sk];
            }
            this.organisers=temp;
            this.arraySizeOrganisers+=10;
            System.out.println("ORGANISER ADDED");
        }
        
        
    }
    /**
     * Increases Arrays that are storing events sizes by 10
     */
   private void increaseArraySizes()
    {
        Event[] tempArr= new Event[this.arraySize+10];
        Event[] tempArrN= new Event[this.arraySize+10];
        Event[] tempArrO= new Event[this.arraySize+10];
        if(this.arraySize - this.eventCount<5)
        {
            for(int sk=0; sk<this.eventCount; sk++)
            {
                tempArr[sk]=this.events[sk];
            }
            for(int sk=0; sk<this.newEventCount; sk++)
            {
                tempArrN[sk]=this.newEvents[sk];
            }
            for(int sk=0; sk<oldEventCount; sk++)
            {
                tempArrO[sk]=this.pastEvents[sk];
            }
            
            this.events=tempArr;
        this.pastEvents=tempArrO;
        this.newEvents=tempArrN;
        this.arraySize+=10;
            
        }
        
    }
    
    /**
     * (For time simulation)
     * Refreshes organizers arrays when we change time
     */
    
    public void refreshOrganisers()
    {
       
        
        for(int sk=0; sk<organisersCount; sk++)
        {
        Event[] temp = new Event[2000];
        Event[] tempOld = new Event[1000];
        Event[] tempNew = new Event[1000];
        boolean[] tempOldB = new boolean[1000];
        boolean[] tempNewB = new boolean[1000];
        
        int oldTempCount=0;
        int newTempCount=0;
            
            
            temp=organisers[sk].getAllEvents();
            for(int sk1=0; sk1<organisers[sk].getAllEventsAmount(); sk1++)
            {
                
                if(temp[sk1].valueForComparing()>time.valueForComparing())
                {
                tempNew[newTempCount]=temp[sk1];
                tempNewB[newTempCount]=true;
                newTempCount++;
            }
            else
            {
                
                tempOld[oldTempCount]=temp[sk1];
                tempOldB[oldTempCount]=false;
                oldTempCount++;
            }
            }
        organisers[sk].setEvents(tempNew);
        organisers[sk].setEventsAmount(newTempCount);
        organisers[sk].setPreviousEvents(tempOld);
        organisers[sk].setPreviousEventsAmount(oldTempCount);
        organisers[sk].setEventsB(tempNewB);
        organisers[sk].setPreviousEventsB(tempOldB);
        }
       
    }
    
    /**
     * Adds new Event
     * @param event 
     */
    public void addEvent(Event event)
    { boolean exist = false;
        events[eventCount]=event;
        eventCount++;
        if(event.valueForComparing()<time.valueForComparing())
        {pastEvents[oldEventCount]=event;
        oldEventCount++;
        if(organisersCount==0) 
        {organisers[organisersCount]=new Organisers(event.getOrganiser());
        organisers[0].addPreviousEvent(event);
        organisersCount++;
        }
        else{
        for(int sk=0; sk<organisersCount; sk++)
        {
            if(event.getOrganiser().equals(organisers[sk].getOrganisersName()))
            {
                organisers[sk].addPreviousEvent(event);
                exist=true;
            }
        }
        if(!exist)
            {
                organisers[organisersCount]=new Organisers(event.getOrganiser());
                organisers[organisersCount].addPreviousEvent(event);
                organisersCount++;
                increaseOrganiserArraySize();
            }
        }
        }
        else
        {newEvents[newEventCount]=event;
        newEventCount++;
        if(organisersCount==0) 
        {organisers[organisersCount]=new Organisers(event.getOrganiser());
        organisersCount++;
        organisers[0].addEvent(event);
        }
        else{
        for(int sk=0; sk<organisersCount; sk++)
        {
            if(event.getOrganiser().equals(organisers[sk].getOrganisersName()))
            {
                organisers[sk].addEvent(event);
                exist=true;
            }
               }
        if(!exist)
            {organisers[organisersCount]=new Organisers(event.getOrganiser());
                organisers[organisersCount].addEvent(event);
                organisersCount++;
                increaseOrganiserArraySize();
                
            }
        }
        }
        increaseArraySizes();
    }
    
    
    
    
    public Organisers[] getOrganisers() {
        return organisers;
    }
    
     public String showEventsName(int sk)
    {
        
            return "Event " + sk + ": " + events[sk].getEventName();
        
    }
    
    public String showOldEventsName(int sk)
    {
        
            return "Event " + sk + ": " + pastEvents[sk].getEventName();
        
    }
     public String showNewEventsName(int sk)
    {
        
            return "Event " + sk + ": " + newEvents[sk].getEventName();
        
    }

    @Override
    public String toString() {
        return "Storage{" + "organisers=" + organisers + '}';
    }
    
    public int getOrganisersCount() {
        return organisersCount;
    }

    public void setOrganisersCount(int organisersCount) {
        this.organisersCount = organisersCount;
    }

    public int getEventCount() {
        return eventCount;
    }

    public int getOldEventCount() {
        return oldEventCount;
    }

    public int getNewEventCount() {
        return newEventCount;
    }
    
    
    
    
}

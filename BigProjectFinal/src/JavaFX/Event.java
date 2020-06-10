package JavaFX;

public class Event extends Time{
    
    private String eventName;
    private String eventType;
    private double eventPrice;
    private boolean isOutDoor;
    private String targetAudience;
    private boolean freeForSchoolChildren;
    private int availableSpace;
    private Time timeForComparing;
    private double votes=0;
    private int votesCount=0;
    private double rating=0;
    private Registration[] participants = new Registration[availableSpace];
    private int participantsCount=0;
    private String organiser;

    public Event(String eventName, String eventType, double eventPrice, boolean isOutDoor, String targetAudience, boolean freeForSchoolChildren, int availableSpace, int years, int months, int days, int hours, int minutes, String organiser) {
        super(years, months, days, hours, minutes);
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventPrice = eventPrice;
        this.isOutDoor = isOutDoor;
        this.targetAudience = targetAudience;
        this.freeForSchoolChildren = freeForSchoolChildren;
        this.availableSpace = availableSpace;
        this.organiser=organiser;
    }

    /**
     * Method responsible for events rating
     * @param rating 
     */
    
    public void setRating(double rating) {
        votes = votes + rating;
        votesCount++;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public String isIsOutDoor() {
        if(isOutDoor) return "Outdoor";
        else return "Indoor";
    }
    public String isFreeForChildren() {
        if(freeForSchoolChildren) return "Event is free for children";
        else return "There are no discounts for children";
    }
    

    
    @Override
    public String toString() {
        return "Event: " + eventName + "\n" + "Cost: " + eventPrice + "\n" + "Event is: " + eventType + "\n" + isIsOutDoor() + "\n" + isFreeForChildren() + "\n"
                + "Target audience: " + targetAudience + "\n" + "Available space: " + availableSpace + "\n" + "Organiser: " + organiser + "\n" + "Rating: " + getRating() + "\n" +"Votes count: "
                + getVotesCount() +"\n" + super.toString();
                          
    }
    

    
    public void addVote(int vote)
    {
        this.votes=this.votes+vote;
        votesCount++;
    }
    
    public double getRating() {
        
        this.rating=votes/votesCount*1.0;
        
        return rating;
    }
    
    public void register(String name, String Surname, int phoneNumber)
    {
        Registration participant = new Registration(name, Surname, phoneNumber);
        this.participants[participantsCount]=participant;
        this.participantsCount++;
    }
    
    public boolean isFull()
    {
        if(availableSpace>this.participantsCount) return false;
        return true;
        
    }
    

    public String getOrganiser() {
        return organiser;
    }

    public void setOrganiser(String organiser) {
        this.organiser = organiser;
    }

    public String getEventName() {
        return eventName;
    }

    @Override
    public void addHours(int tmp) {
        timeForComparing.addHours(tmp);
    }
    
    @Override
    public void addMonths(int tmp) {
        timeForComparing.addMonths(tmp);
    }
    
    
    
}

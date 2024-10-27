public class ElectricityPriceData {

    private double price; 
    private String timeStart;
    private String timeStop;
    private String priceZone; 

    // Konstruktør 
    public ElectricityPriceData(double price, String timeStart, String timeStop, String priceZone){
        this.price = price; 
        this.timeStart = timeStart;
        this.timeStop = timeStop;
        this.priceZone = priceZone;
    }

    // Gettere
    public double getPrice(){
        return price;
    }

    public String getTimeStart(){
        return timeStart;
    }

    public String getTimeStop(){
        return timeStop; 
    }

    public String getPriceZone(){
        return priceZone;
    }
    
}
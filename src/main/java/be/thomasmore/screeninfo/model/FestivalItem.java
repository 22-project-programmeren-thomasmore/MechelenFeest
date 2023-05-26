package be.thomasmore.screeninfo.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class FestivalItem {
    private String festivalName;
    private String festivalImage;
    private String backgroundColor;
    private String date;
    private String festivalLink;
    private boolean onGoing; // om manueel te zeggen dat een event bezig is

    private String busyness; // om op voorant te berekenen hoe druk he is


    public FestivalItem(Festival festival){
        festivalName = festival.getFestivalName();
        festivalImage = festival.getFestivalImage();
        backgroundColor = festival.getBackgroundColor();
        festivalLink = festival.getFestivalLink();

        onGoing = Date.from(java.time.LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()).after(festival.getStartDate());

        // voor nu debuggen
        date = "";
        LocalDate startDate = festival.getStartDate().toLocalDate();
        LocalDate endDate = festival.getEndDate().toLocalDate();
        if(startDate.getYear() != endDate.getYear()){
            date = startDate.getDayOfMonth()+"-"+ startDate.getMonthValue()+"-"+startDate.getYear() + " - ";
        }
        else if(startDate.getMonth() != endDate.getMonth()){
            date = startDate.getDayOfMonth()+"-"+ startDate.getMonthValue() + " - ";
        }
        else if(startDate.getDayOfMonth() != endDate.getDayOfMonth()){
            date = startDate.getDayOfMonth() + " - ";
        }
            date += endDate.getDayOfMonth()+"-"+endDate.getMonthValue()+"-"+endDate.getYear();

        int maxCapacity = festival.getMaxCapacity();
        int population = festival.getPopulation();

        if(maxCapacity < population){
            busyness = "FULL";
        }
        else if(maxCapacity * 0.75 < population){
            busyness = "BUSY";
        }
        else if(maxCapacity * 0.5 < population){
            busyness = "MEDIUM BUSY";
        }
        else if(maxCapacity * 0.25 < population){
            busyness = "CALM";
        }
        else {
            busyness = "EMPTY";
        }

    }

    public String getFestivalName() {
        return festivalName;
    }

    public void setFestivalName(String festivalName) {
        this.festivalName = festivalName;
    }

    public String getFestivalImage() {
        return festivalImage;
    }

    public void setFestivalImage(String festivalImage) {
        this.festivalImage = festivalImage;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setFestivalLink(String festivalLink) {
        this.festivalLink = festivalLink;
    }

    public String getFestivalLink() {
        return festivalLink;
    }

    public void setOnGoing(boolean onGoing) {
        this.onGoing = onGoing;
    }

    public boolean isOnGoing() {
        return onGoing;
    }

    public String getBusyness() {
        return busyness;
    }

    public void setBusyness(String busyness) {
        this.busyness = busyness;
    }

}

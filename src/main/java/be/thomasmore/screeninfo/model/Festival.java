package be.thomasmore.screeninfo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Festival {

    @SequenceGenerator(name = "FestSeqGen", sequenceName = "FestSeq", initialValue = 5, allocationSize = 1)
    @GeneratedValue(generator = "FestSeqGen")
    @Id
    public Integer id;
    private String festivalName;
    private String festivalImage;
    private String backgroundColor;
    private String festivalDate;
    private String festivalLink;
    private Integer maxCapacity; // dit is voor de barometer
    private Integer population; // dit is voor hoeveel man er momenteel is
    private boolean onGoing; // om manueel te zeggen dat een event bezig is

    private String festivalType;

    public Festival(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getFestivalDate() {
        return festivalDate;
    }

    public void setFestivalDate(String festivalDate) {
        this.festivalDate = festivalDate;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getFestivalLink() {
        return festivalLink;
    }

    public void setFestivalLink(String festivalLink) {
        this.festivalLink = festivalLink;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public boolean isOnGoing() {
        return onGoing;
    }

    public void setOnGoing(boolean onGoing) {
        this.onGoing = onGoing;
    }

    public String getFestivalType() {
        return festivalType;
    }

    public void setFestivalType(String festivalType) {
        this.festivalType = festivalType;
    }

}


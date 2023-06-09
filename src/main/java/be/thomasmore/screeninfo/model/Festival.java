package be.thomasmore.screeninfo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.sql.Date;

@Entity
public class Festival {

    @SequenceGenerator(name = "FestSeqGen", sequenceName = "FestSeq", initialValue = 6, allocationSize = 1)
    @GeneratedValue(generator = "FestSeqGen")
    @Id
    public Integer id;
    private String festivalName;
    private String festivalImage;
    private String backgroundColor;
    private Date startDate;
    private Date endDate;
    private String festivalLink;
    private Integer maxCapacity; // dit is voor de barometer
    private Integer population; // dit is voor hoeveel man er momenteel is

    private String festivalType;

    // voor positie op map
    private float mapLat;
    private float mapLng;

    public Festival(){
        startDate = new Date(123,00,01);
        endDate = new Date(123,00,01);
        festivalType = "Music";
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


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public String getFestivalType() {
        return festivalType;
    }

    public void setFestivalType(String festivalType) {
        this.festivalType = festivalType;
    }

    public void setMapLat(float mapLat) {
        this.mapLat = mapLat;
    }

    public void setMapLng(float mapLng) {
        this.mapLng = mapLng;
    }

    public float getMapLat() {
        return mapLat;
    }

    public float getMapLng() {
        return mapLng;
    }
}


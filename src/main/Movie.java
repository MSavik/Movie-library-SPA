package main;

import java.util.ArrayList;

public class Movie {
    private String title;
    private String genre;
    private int runningTime;
    private ArrayList<String> starring;
    private ArrayList<String> directedBy;
    private ArrayList<String> producedBy;
    private ArrayList<String> writtenBy;
    private ArrayList<String> productionCompany;
    private ArrayList<String> country;
    private String language;
    private int yearOfRelease;
    private String budget;

    public Movie(String title, String genre, int runningTime, ArrayList<String> starring, ArrayList<String> directedBy, ArrayList<String> producedBy, ArrayList<String> writtenBy, ArrayList<String> productionCompany, ArrayList<String> country, String language, int yearOfRelease, String budget) {
        this.title = title;
        this.genre = genre;
        this.runningTime = runningTime;
        this.starring = starring;
        this.directedBy = directedBy;
        this.producedBy = producedBy;
        this.writtenBy = writtenBy;
        this.productionCompany = productionCompany;
        this.country = country;
        this.language = language;
        this.yearOfRelease = yearOfRelease;
        this.budget = budget;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public ArrayList<String> getStarring() {
        return starring;
    }

    public void setStarring(ArrayList<String> starring) {
        this.starring = starring;
    }

    public ArrayList<String> getDirectedBy() {
        return directedBy;
    }

    public void setDirectedBy(ArrayList<String> directedBy) {
        this.directedBy = directedBy;
    }

    public ArrayList<String> getProducedBy() {
        return producedBy;
    }

    public void setProducedBy(ArrayList<String> producedBy) {
        this.producedBy = producedBy;
    }

    public ArrayList<String> getWrittenBy() {
        return writtenBy;
    }

    public void setWrittenBy(ArrayList<String> writtenBy) {
        this.writtenBy = writtenBy;
    }

    public ArrayList<String> getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(ArrayList<String> productionCompany) {
        this.productionCompany = productionCompany;
    }

    public ArrayList<String> getCountry() {
        return country;
    }

    public void setCountry(ArrayList<String> country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }
}

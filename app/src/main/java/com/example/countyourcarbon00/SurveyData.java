package com.example.countyourcarbon00;

public class SurveyData {

    private String schools;
    private String age;
    private String satisfaction;
    private String confidence;
    private String recycling;
    private String food;
    private String foodOptions;
    private String cups;
    private String feedback;

    public SurveyData(String schools, String age, String satisfaction, String confidence, String recycling, String food, String foodOptions, String cups, String feedback) {
        this.schools = schools;
        this.age = age;
        this.satisfaction = satisfaction;
        this.confidence = confidence;
        this.recycling = recycling;
        this.food = food;
        this.foodOptions = foodOptions;
        this.cups = cups;
        this.feedback = feedback;
    }

    public String getSchools() {
        return schools;
    }

    public void setSchools(String schools) {
        this.schools = schools;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(String satisfaction) {
        this.satisfaction = satisfaction;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getRecycling() {
        return recycling;
    }

    public void setRecycling(String recycling) {
        this.recycling = recycling;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getFoodOptions() {
        return foodOptions;
    }

    public void setFoodOptions(String foodOptions) {
        this.foodOptions = foodOptions;
    }

    public String getCups() {
        return cups;
    }

    public void setCups(String cups) {
        this.cups = cups;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


}

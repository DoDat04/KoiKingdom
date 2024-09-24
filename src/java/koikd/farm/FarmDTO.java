/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.farm;

/**
 *
 * @author ADMIN LAM
 */
public class FarmDTO {
    private int farmID;
    private String farmName;
    private String location;
    private String description;
    private String farmImageURL;
    private boolean farmStatus;

    public FarmDTO(int farmID, String farmName, String location, String description, String farmImageURL, boolean farmStatus) {
        this.farmID = farmID;
        this.farmName = farmName;
        this.location = location;
        this.description = description;
        this.farmImageURL = farmImageURL;
        this.farmStatus = farmStatus;
    }

    public int getFarmID() {
        return farmID;
    }

    public void setFarmID(int farmID) {
        this.farmID = farmID;
    }

    public String getFarmName() {
        return farmName;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFarmImageURL() {
        return farmImageURL;
    }

    public void setFarmImageURL(String farmImageURL) {
        this.farmImageURL = farmImageURL;
    }

    public boolean isFarmStatus() {
        return farmStatus;
    }

    public void setFarmStatus(boolean farmStatus) {
        this.farmStatus = farmStatus;
    }

    @Override
    public String toString() {
        return "FarmDTO{" + "farmID=" + farmID + ", farmName=" + farmName + ", location=" + location + ", description=" + description + ", farmImageURL=" + farmImageURL + ", farmStatus=" + farmStatus + '}';
    }
    
}

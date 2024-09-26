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
    private String image;
    private boolean status;

    public FarmDTO() {
    }
    
    public FarmDTO(int farmID, String farmName, String Location, String description, String Image, boolean Status) {
        this.farmID = farmID;
        this.farmName = farmName;
        this.location = Location;
        this.description = description;
        this.image = Image;
        this.status = Status;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean Status) {
        this.status = Status;
    }   

    @Override
    public String toString() {
        return "FarmDTO{" + "farmID=" + farmID + ", farmName=" + farmName + ", Location=" + location + ", description=" + description + ", Image=" + image + ", Status=" + status + '}';
    }
}

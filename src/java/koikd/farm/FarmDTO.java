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
    private String Location;
    private String description;
    private String Image;
    private boolean Status;

    public FarmDTO(int farmID, String farmName, String Location, String description, String Image, boolean Status) {
        this.farmID = farmID;
        this.farmName = farmName;
        this.Location = Location;
        this.description = description;
        this.Image = Image;
        this.Status = Status;
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
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }   

    @Override
    public String toString() {
        return "FarmDTO{" + "farmID=" + farmID + ", farmName=" + farmName + ", Location=" + Location + ", description=" + description + ", Image=" + Image + ", Status=" + Status + '}';
    }
}

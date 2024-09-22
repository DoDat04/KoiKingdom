/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koikd.koitype;

import java.io.Serializable;

/**
 *
 * @author ADMIN LAM
 */
public class KoiTypeDTO implements Serializable{
    private int koiTypeID;
    private String typeName;
    private String description;
    private String koiImageURL;
    private boolean KoiTypeStatus;

    public KoiTypeDTO(int koiTypeID, String typeName, String description, String koiImageURL, boolean KoiTypeStatus) {
        this.koiTypeID = koiTypeID;
        this.typeName = typeName;
        this.description = description;
        this.koiImageURL = koiImageURL;
        this.KoiTypeStatus = KoiTypeStatus;
    }

    public KoiTypeDTO(){
        
    }

    public int getKoiTypeID() {
        return koiTypeID;
    }

    public void setKoiTypeID(int koiTypeID) {
        this.koiTypeID = koiTypeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKoiImageURL() {
        return koiImageURL;
    }

    public void setKoiImageURL(String koiImageURL) {
        this.koiImageURL = koiImageURL;
    }

    public boolean isKoiTypeStatus() {
        return KoiTypeStatus;
    }

    public void setKoiTypeStatus(boolean KoiTypeStatus) {
        this.KoiTypeStatus = KoiTypeStatus;
    }

    @Override
    public String toString() {
        return "KoiTypeDTO{" + "koiTypeID=" + koiTypeID + ", typeName=" + typeName + ", description=" + description + ", koiImageURL=" + koiImageURL + ", KoiTypeStatus=" + KoiTypeStatus + '}';
    }
    
    
}

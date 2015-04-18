
package Business;

/**
 *
 * Material Class
 */
public class Material {
    private int materialCode; 
    private double unitPrice;
    private String URL;
    private String description;
    private double currentQuantity;

    public void setMaterialCode(int materialCode) {
        this.materialCode = materialCode;
    }
    

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getMaterialCode() {
        return materialCode;
    }

    public String getDescription() {
        return description;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getURL() {
        return URL;
    }

    public double getCurrentQuantity() {
        return currentQuantity;
    }

    public void setCurrentQuantity(double currentQuantity) {
        this.currentQuantity = currentQuantity;
    }
    
}

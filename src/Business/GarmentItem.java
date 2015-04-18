package Business;

public class GarmentItem{

	private int garmentItemCode;
	private double price;
	private double cost;
	private String URL;
        private int inStockQuantity;
        private int quantity;
	private int usedMaterialCode[];
		
	public int getGarmentItemCode(){
		return this.garmentItemCode;
	}
	
	public void setGarmentItemCode( int itemCode ){
		this.garmentItemCode = itemCode;
	}
	
	public double getPrice(){
		return this.price;
	}
	
	public void setPrice( double price ){
		this.price = price;
	}

	public double getCost(){
		return this.cost;
	}
	
	public void setCost( double cost ){
		this.cost = cost;
	}
	
	public String getURL(){
		return this.URL;
	}
	
	public void setURL( String URL ){
		this.URL = URL;
	}
	
	public void setMaterials( int usedMaterialCode[] ){
		this.usedMaterialCode = usedMaterialCode;
	}

	public int getInStockQuantity() {
            return inStockQuantity;
        }

        public void setInStockQuantity(int currentQuantity) {
            this.inStockQuantity = currentQuantity;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        } 
}
	
package address_book_features;

public class Address {
	private String city;
    private String state;
    private int zip;

    public void setAddress(String city, String state, int zip) {
         this.city = city;
         this.state = state;
         this.zip = zip;
    }

    public void setCity(String city) {
         this.city = city;
    }

    public void setState(String state) {
         this.state = state;
    }

    public void setZip(int zip) {
         this.zip = zip;
    }

    public String getCity() {
         return city;
    }

    public String getState() {
         return state;
    }

    public int getZip() {
         return zip;
    }

    public String getAddress() {
         return city + "," + state + "," + zip;
    }

    @Override
    public String toString() {
         return city + "," + state + "," + zip;
    }
}

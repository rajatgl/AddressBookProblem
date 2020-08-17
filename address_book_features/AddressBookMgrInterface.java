package address_book_features;
import java.io.File;
import java.io.IOException;
public interface AddressBookMgrInterface {
	
	public void newBook(String name) throws IOException;

    public File openBook(String fileName);

    public void showCSVFiles();	

}

package address_book_features;
import java.io.File;
import java.io.IOException;
public interface AddressBookInterface {
	
	public void addPerson(File file, Person person) throws IOException;

    public void sortByLastName(File file) throws IOException;

    public void sortByZip(File file) throws IOException;

    public void deletePerson(File file, String name) throws IOException;

    public void updatePerson(File file, String fname) throws IOException;

}

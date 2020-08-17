package address_book_features;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		AddressBookMgr manager = new AddressBookMgr();
		AddressBook book = new AddressBook();
		File openedFile = null;
		Scanner inputs = new Scanner(System.in);
        int quit = 0;
        while (quit == 0) {
             System.out.print("\n Address Book \n" +
                     "1. Create New Book\n" +
                     "2. Open Address Book\n" +
                     "3. Add Person\n" +
                     "4. Update Or Delete Person\n" +
                     "5. Sort Records\n" +
                     "6. Close Address Book\n" +
                     "7. Exit\n" +
                     "Your Choice: ");
             int choice = inputs.nextInt();
             switch (choice) {
                  case 1:
                       System.out.print("AddressBook Name: ");
                       String abName = inputs.next();
                       manager.newBook(abName);
                       break;

                  case 2:
                       if (openedFile == null) {
                            System.out.println("Select A Flie :");
                            manager.showCSVFiles();
                            System.out.print("\nAddressBook Name: ");
                            String openFile = inputs.next();
                            openedFile = manager.openBook(openFile);
                       } else {
                            System.out.println("File Already open... close it first!\n");
                       }
                       break;
                  case 3:
                       if (openedFile == null) {
                            System.out.println("No File Opened Yet...Cant Update!\n");
                       } else {
                            Person person = new Person();
                            Address address = new Address();
                            System.out.print("First Name: ");
                            person.setFirstName(inputs.next());
                            System.out.print("Last Name: ");
                            person.setLastName(inputs.next());
                            System.out.print("Phone Number: ");
                            person.setPhoneNumber(inputs.next());
                            System.out.print("City: ");
                            address.setCity(inputs.next());
                            System.out.print("State: ");
                            address.setState(inputs.next());
                            System.out.print("Zip: ");
                            address.setZip(inputs.nextInt());
                            person.setAddress(address);
                            book.addPerson(openedFile, person);
                            break;
                       }
                       break;
                  case 4:
                       if (openedFile == null) {
                            System.out.println("You Need to open file first to perform edits !\n");
                       } else {
                            System.out.print("Edit Choices\n" +
                                    "1. Add Person in Address Book\n" +
                                    "2. Update Person info in Address Book\n" +
                                    "3. Delete Person from Address Book" +
                                    "\nChoice: ");
                            int editChoice = inputs.nextInt();
                            switch (editChoice) {
                                 case 1:


                                 case 2:
                                      System.out.print("Enter First Name: ");
                                      String fname = inputs.next();
                                      book.updatePerson(openedFile, fname);
                                      break;

                                 case 3:
                                      System.out.print("Enter first name to remove record: ");
                                      String name = inputs.next();
                                      book.deletePerson(openedFile, name);
                                      break;

                                 default:
                                      System.out.println("Invalid choice !!!\n");
                                      break;
                            }
                       }
                       break;

                  case 5:
                       if (openedFile == null) {
                            System.out.println("Open file first and then view records !\n");
                       } else {
                            System.out.print("Enter your choice to view\n" +
                                    "1. View by Last Name\n" +
                                    "2. View by zip\n" +
                                    "Choice: ");
                            int viewChoice = inputs.nextInt();
                            switch (viewChoice) {
                                 case 1:
                                      book.sortByLastName(openedFile);
                                      break;
                                 case 2:
                                      book.sortByZip(openedFile);
                                      break;
                                 default:
                                      System.out.println("Invalid Choice !!!\n");
                            }
                       }
                       break;

                  case 6:
                       if (openedFile == null) {
                            System.out.println("No Address Book Found to Close !\n");
                       } else {
                            openedFile = null;
                            System.out.println("Address Book Closed !\n");
                       }
                       break;

                  case 7:
                       quit = 1;
                       break;

                  default:
                       System.out.println("Invalid Choice !!!\n");
             }
        }

	}

}

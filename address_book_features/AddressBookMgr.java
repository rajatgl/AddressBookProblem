package address_book_features;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class AddressBookMgr implements AddressBookMgrInterface {
	@Override
    public void newBook(String name) throws IOException {
         String CSV_HEADER = "First Name,Last Name,Phone Number,City,State,Zip\n";
         File file = new File("C:\\Users\\rajath\\eclipse-workspace\\AddressBook_Stage3_Day16(CodeInClub)\\AddressBookDemo\\" + name + ".csv");
         boolean result = file.createNewFile();
         FileWriter newFile = new FileWriter(file);
         newFile.append(CSV_HEADER);
         if (result)
              System.out.println("New csv File Created !");
         else
              System.out.println("File Already Exists !");
         newFile.flush();
         newFile.close();
    }

    @Override
    public File openBook(String fileName) {
         File file = new File("C:\\Users\\rajath\\eclipse-workspace\\AddressBook_Stage3_Day16(CodeInClub)\\AddressBookDemo\\" + fileName + ".csv");
         boolean check = file.exists();
         if (check == true) {
              System.out.print(fileName + ".csv Book Opened !");
         } else {
              System.out.print("No such file exists !");
              file = null;
         }
         return file;
    }

    @Override
    public void showCSVFiles() {
         File folder = new File("C:\\Users\\rajath\\eclipse-workspace\\AddressBook_Stage3_Day16(CodeInClub)\\AddressBookDemo\\");
         File file[] = folder.listFiles();
         System.out.println("List of CSV Files");
         for (File print : file) {
              String name = print.getName();
              if (name.contains(".csv")) {
                   System.out.println(name);
              }
         }
    }
}

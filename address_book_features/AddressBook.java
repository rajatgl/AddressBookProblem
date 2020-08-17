package address_book_features;
import java.io.*;
import java.util.*;
public class AddressBook implements AddressBookInterface {
	private final String CSV_HEADER = "First Name,Last Name,Phone Number,City,State,Zip\n";
    private ArrayList<Person> people;

    @Override
    public void addPerson(File file, Person person) throws IOException {
         FileWriter writer = new FileWriter(file, true);

         writer.append(person.getFirstName());
         writer.append(',');
         writer.append(person.getLastName());
         writer.append(',');
         writer.append(person.getPhoneNumber());
         writer.append(',');
         writer.append(person.getAddress().getCity());
         writer.append(',');
         writer.append(person.getAddress().getState());
         writer.append(',');
         writer.append(String.valueOf(person.getAddress().getZip()));
         writer.append("\n");

         writer.flush();
         writer.close();

         System.out.println(person.toString() + "\nRecord written successfully !!!");
    }

    @Override
    public void sortByLastName(File file) throws IOException {
         people = AddressBook.CSVToArray(file);
         //ArrayList<Person> multiple = new ArrayList<>();
         Map<String, Person> map = new TreeMap<>();

         int i = 0;
         for (Person person : people) {
              String key = person.getLastName();
              if (map.containsKey(key)) {
                   key = key + "" + i;
                   i++;
              }
              map.put(key, person);
         }
         System.out.println("\n Records Sorted by LastName ");
         System.out.println(CSV_HEADER);
         for (Person person : map.values()) {
              System.out.print(person.getPerson() + "\n");
         }
    }

    @Override
    public void sortByZip(File file) throws IOException {
         people = AddressBook.CSVToArray(file);
         Map<Integer, Person> map = new TreeMap<>();
         int i = 1;
         for (Person person : people) {
              int key = person.getAddress().getZip();
              if (map.containsKey(key)) {
                   key = key + i;
                   i++;
              }
              map.put(key, person);
         }
         System.out.println("Records Sorted by Zip");
         System.out.println(CSV_HEADER);
         for (Person person : map.values()) {
              System.out.print(person.getPerson() + "\n");
         }
    }

    @Override
    public void deletePerson(File file, String fname) throws IOException {
         people = AddressBook.CSVToArray(file);
         Map<String, Person> map = new TreeMap<>();
         for (Person temp : people) {
              String key = temp.getFirstName();
              map.put(key, temp);
         }

         if (map.containsKey(fname) == true) {
              System.out.println("Removed Record: " + map.get(fname));
              map.remove(fname);
              FileWriter newWriter = new FileWriter(file);
              newWriter.append(CSV_HEADER);
              newWriter.flush();
              newWriter.close();
              FileWriter appendWriter = new FileWriter(file, true);
              for (Person printPersons : map.values()) {
                   appendWriter.append(printPersons + "\n");
              }
              appendWriter.flush();
              appendWriter.close();
              System.out.println("\nRecord removed successfully !");
         } else {
              System.out.println("\nRecord does not exists !");
         }
    }

    @Override
    public void updatePerson(File file, String fname) throws IOException {
         ArrayList<Person> people = AddressBook.CSVToArray(file);
         Map<String, Person> map = new HashMap<>();
         for (Person temp : people) {
              String key = temp.getFirstName();
              map.put(key, temp);
         }
         System.out.println(map);

         if (map.containsKey(fname) == true) {
              Scanner input = new Scanner(System.in);
              int flag = 0;
              Person per = map.get(fname);
              Address add = per.getAddress();
              while (flag == 0) {
                   System.out.println("Enter Updation Choice:" +
                           "\n1. Last Name" +
                           "\n2. Phone Number" +
                           "\n3. City" +
                           "\n4. State" +
                           "\n5. Zip" +
                           "\n6. Exit Updation");
                   System.out.print("Choice: ");
                   int choice = input.nextInt();

                   switch (choice) {
                        case 1:
                             System.out.print("\nEnter updated last name: ");
                             String lname = input.next();
                             per.setLastName(lname);
                             break;
                        case 2:
                             System.out.print("\nEnter updated phone number: ");
                             String phoneNum = input.next();
                             per.setPhoneNumber(phoneNum);
                             break;
                        case 3:
                             System.out.print("Enter updated city: ");
                             String city = input.next();
                             add.setCity(city);
                             per.setAddress(add);
                             break;
                        case 4:
                             System.out.print("Enter updated state: ");
                             String state = input.next();
                             add.setState(state);
                             per.setAddress(add);
                             break;
                        case 5:
                             System.out.print("Enter updated zip: ");
                             int zip = input.nextInt();
                             add.setZip(zip);
                             per.setAddress(add);
                        case 6:
                             flag = 1;
                             break;
                        default:
                             System.out.println("Invalid choice !!!");
                   }
                   map.computeIfPresent(fname, (K, V) -> V = per);
              }

              String CSV_HEADER = "\nFirst Name,Last Name,Phone Number,City,State,Zip\n";
              FileWriter newWriter = new FileWriter(file);
              newWriter.append(CSV_HEADER);
              newWriter.flush();
              newWriter.close();
              FileWriter appWriter = new FileWriter(file, true);
              for (Person person : map.values()) {
                   appWriter.append(person.getPerson() + "\n");
              }
              appWriter.flush();
              appWriter.close();
              System.out.println("Record updated successfully !");
         } else {
              System.out.println("Record not present !");
         }
    }

    private static ArrayList<Person> CSVToArray(File book) throws IOException {
         ArrayList<Person> ListOfPeople;
         if (book.exists()) {
              BufferedReader br = new BufferedReader(new FileReader(book));
              ListOfPeople = new ArrayList<>();

              String line = br.readLine();
              while ((line = br.readLine()) != null) {
                   if (line == "First Name,Last Name,Phone Number,City,State,Zip")
                        continue;
                   Person p = new Person();
                   Address a = new Address();
                   a.setAddress(line.split(",")[ 3 ],
                           line.split(",")[ 4 ],
                           Integer.parseInt(line.split(",")[ 5 ]));
                   p.setPerson(line.split(",")[ 0 ],
                           line.split(",")[ 1 ],
                           line.split(",")[ 2 ],
                           a
                   );
                   ListOfPeople.add(p);
              }
              br.close();
         } else {
              return null;
         }
         return (ListOfPeople);
    }

    private static File arrayToCSV(ArrayList<Person> array, File f) throws IOException {
         FileWriter fw = new FileWriter(f);
         String CSV_HEADER = "First Name,Last Name,Phone Number,City,State,Zip\n";
         fw.append(CSV_HEADER);
         fw.flush();
         fw.close();
         FileWriter nfw = new FileWriter(f, true);
         int count = 0;
         while (count < array.size()) {
              nfw.append(array.get(count).getPerson() + "\n");
              System.out.println(array.get(count).getPerson());
              count++;
         }
         nfw.flush();
         nfw.close();
         return f;
    }
}

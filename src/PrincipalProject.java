import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PrincipalProject {

    private static String input(String label) {
        System.out.print(label);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return input;
    }

    public static void main(String[] args) {

        DBOperations obj = new DBOperations();

        int optiune = -1;
        do {
            listMenu();

            Scanner sc = new Scanner(System.in);
            try {
                optiune = sc.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch (optiune) {
                case 1: {
                    try {
                        List l = DBOperations.read();

                        for (int i = 0; i < l.size(); i++) {
                            Person u = (Person) l.get(i);

                            System.out.println(u.getName());
                            System.out.println(u.getPhoneNumber());
                            System.out.println("----------");
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 2: {
                    //citim aici si punem la metoda in dboperations parametri

                    try {
                        Person p = new Person();
                        p.setName(input("Insert name: "));
                        p.setPhoneNumber(input("Insert phonenumber: "));
                        obj.addAgenda(p);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 3: {
                    try {
                        Person p = new Person();
                        p.setName(input("Find the person you want to delete: "));
                        List<Person> personsFound = obj.findPerson(p);

                        if(personsFound.size() == 0) {
                            System.out.println("You do not have this person in your agenda!");
                        }
                        else {
                            if(personsFound.size() == 1) {
                                String input = input("Are you sure you want to delete " + personsFound.get(0).getName() + " ? Press Y for yes or N for no ");
                                if (input.equalsIgnoreCase("y")) {
                                    obj.delete(personsFound.get(0));
                                }
                            }
                            else {
                                int numberPersons = search(personsFound, "delete");
                                if(numberPersons < personsFound.size()) {
                                    obj.delete(personsFound.get(numberPersons));
                                }                           }
                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 4: {

                    try {
                        Person p = new Person();
                        p.setName(input("Who are you searching for? "));
                        List<Person> personsFound = obj.findPerson(p);

                        if(personsFound.size() == 0) {
                            System.out.println("You don't have that person in your agenda!");
                        }
                        else {
                            for (int i = 0; i < personsFound.size(); i++) {
                                Person pers = personsFound.get(i);
                                System.out.println(pers.getName());
                                System.out.println(pers.getPhoneNumber());
                                System.out.println("-------");
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 5: {
                    try {
                        Person p = new Person();
                        p.setName(input("Who are you searching for? "));
                        List<Person> personsFound = obj.findPerson(p);

                        if(personsFound.size()==0){
                            System.out.println("You do not have this person in your agenda!");
                        }
                       else {
                            if(personsFound.size() == 1) {
                                String input = input("Are you sure you want to change " + personsFound.get(0).getName()+ " ? Press Y for yes or N for no ");
                                if(input.equalsIgnoreCase("y")) {
                                    String newName = input("Insert new name: ");
                                    String newPhone = input("Insert new phonenumber: ");
                                    obj.modify(personsFound.get(0), newName, newPhone);
                                }
                            } else {

                                int number = search(personsFound, "modify");

                                if(number>=0 && number<personsFound.size()) {
                                    String newName = input("Insert new name: ");
                                    String newPhone = input("Insert new phonenumber: ");
                                    obj.modify(personsFound.get(number), newName, newPhone);
                                }
                            }
                        }

                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        } while (optiune != 0);
    }

    public static void listMenu() {
        System.out.println("1> List agenda");
        System.out.println("2> Add person");
        System.out.println("3> Delete person");
        System.out.println("4> Find person");
        System.out.println("5> Modify person");
        System.out.println("0> Exit");
    }

    public static int search(List<Person> personsFound, String label) {
        for (int i = 0; i < personsFound.size(); i++) {
            Person eachPerson = personsFound.get(i);
            System.out.println(i + ". " + eachPerson.getName());
        }

        System.out.println("There are more than one person corresponding to your input.");
        System.out.print("Please insert the number corresponding to the person you want to " + label + ": ");

        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        return number;
    }
}
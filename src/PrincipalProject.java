import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class PrincipalProject{

    public static void main(String[] args) {
        afisareMeniu();
        //PrincipalProject am = new PrincipalProject();
        DBOperations obj = new DBOperations();
        int optiune = -1;
        do {

            Scanner sc = new Scanner(System.in);
            optiune = sc.nextInt();
            switch (optiune) {
                case 1: {
                    try {

                        List l = DBOperations.demoRead();

                for (int i=0;i<l.size();i++) {
                    Person u = (Person)l.get(i);

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
                    try {
                        obj.demoCreate();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 3: {
                    try {
                        obj.demoDelete();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                }

//                case 4: {
//                    cautareSimpla();
//                    break;
//                }

                case 5: {
                    try {
                        obj.demoUpdate();
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

    public static void afisareMeniu(){
        System.out.println("1> Afisare");
        System.out.println("2> Adaugare");
        System.out.println("3> Stergere");
        System.out.println("4> Cautare simpla");
        System.out.println("5> Modificare");
        System.out.println("0> Exit");
    }
}
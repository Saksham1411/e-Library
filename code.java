import java.util.*;

import javax.swing.text.DefaultStyledDocument.ElementSpec;
import javax.xml.transform.Source;

class book {
    String name;
    String auth_name;
    int sNo;
    int bookQuantity;
    Scanner sc = new Scanner(System.in);

    book() {
        System.out.print("Enter Book Serial no.  ");
        this.sNo = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Book Name  ");
        this.name = sc.nextLine();

        System.out.print("Enter Author Name  ");
        this.auth_name = sc.nextLine();

        System.out.print("Enter Book Quantity  ");
        this.bookQuantity = sc.nextInt();
    }
}

class Books {
    book[] thebooks = new book[50];
    int count = 0;
    Scanner sc = new Scanner(System.in);

    public void addbook(book b) {
        if (count < 50) {
            thebooks[count] = b;
            count++;
        } else {
            System.out.println("No more storage");
        }
    }

    public void UpgradeQty() {
        System.out.println("Enter serial no. of book");
        int sno = sc.nextInt();
        for (int i = 0; i < count; i++) {
            if (sno == thebooks[i].sNo) {
                System.out.println("Number of book you want to add");
                int qty = sc.nextInt();
                thebooks[i].bookQuantity += qty;
            }
        }
    }

    public void searchBySno() {
        System.out.println("Enter serial Number of book");
        int sno = sc.nextInt();
        for (int i = 0; i < count; i++) {
            if (sno == thebooks[i].sNo) {
                System.out.println("Sno.\t\tBook Name\t\tAuthor Name\t\tBook Quantity");
                System.out.println(thebooks[i].sNo + "\t\t" +
                        thebooks[i].name + "\t\t" +
                        thebooks[i].auth_name + "\t\t" +
                        thebooks[i].bookQuantity);
                return;
            }
        }
        System.out.println("book not Found");
    }

    public void searchByname() {
        System.out.println("Enter Book Name");
        // sc.nextLine();
        String name = sc.nextLine();
        for (int i = 0; i < count; i++) {
            if (name.equalsIgnoreCase(thebooks[i].name)) {
                System.out.println("Sno.\t\tBook Name\t\tAuthor Name\t\tBook Quantity");
                System.out.println(thebooks[i].sNo + "\t\t" +
                        thebooks[i].name + "\t\t" +
                        thebooks[i].auth_name + "\t\t" +
                        thebooks[i].bookQuantity);
                return;
            }
        }
        System.out.println("there is no book of name " + name);

    }

    public void showAllbook() {
        System.out.println("Sno.\t\tBook Name\t\tAuthor Name\t\tBook Quantity");
        for (int i = 0; i < count; i++) {
            System.out.println(thebooks[i].sNo + "\t\t" + thebooks[i].name + "\t\t" + thebooks[i].auth_name + "\t\t"
                    + thebooks[i].bookQuantity);
        }
    }

    public int isAvailable(int sno) {
        for (int i = 0; i < count; i++) {
            if (sno == thebooks[i].sNo) {
                if (thebooks[i].bookQuantity > 0) {
                    System.out.println("Book is available");
                    return i;
                }
                System.out.println("Book is unavailable");
                return -1;
            }
        }
        return -1;
    }

    public book chkOut() {
        System.out.println("Enter the serial no of book you want to check out");
        int sno = sc.nextInt();
        int idx = isAvailable(sno);
        if (idx != -1) {
            thebooks[idx].bookQuantity--;
            return thebooks[idx];
        }
        return null;
    }

    public void chkIn(book b) {
        for (int i = 0; i < count; i++) {
            if (b.equals(thebooks[i])) {
                thebooks[i].bookQuantity++;
                return;
            }
        }
    }

    public void displayMenu() {
        System.out.println("*******************************************");
        System.out.println("Press 0 to Exit Application");
        System.out.println("Press 1 to Add New Book");
        System.out.println("Press 2 to Upgrade Quantity of a BOOK");
        System.out.println("Press 3 to Search a Book");
        System.out.println("Press 4 to Show All Books");
        System.out.println("Press 5 to Register Student");
        System.out.println("Press 6 to Show all Register Student");
        System.out.println("Press 7 to Check Out Book");
        System.out.println("Press 8 to Check In Book");
        System.out.println("*******************************************");
    }

}

class student {
    String reg_no;
    String name;
    book[] borBook = new book[5];
    int countB = 0;
    Scanner sc = new Scanner(System.in);

    student() {

        System.out.println("Enter the Registration Number");
        this.reg_no = sc.nextLine();
        System.out.println("Enter the name");
        this.name = sc.nextLine();
    }

}

class students {
    Scanner sc = new Scanner(System.in);
    student[] std = new student[50];
    public static int cnt = 0;

    public void addStudent(student s) {
        if (cnt < 50) {
            std[cnt] = s;
            cnt++;
        } else {
            System.out.println("no more storage");
        }
    }

    public void showAllStudent() {
        System.out.println("Reg no.\t\tName");
        for (int i = 0; i < cnt; i++) {
            System.out.println(std[i].reg_no + "\t\t" + std[i].name);
        }
    }

    public int isStudentAvailable() {
        System.out.println("Enter your Registration number ");
        String sno = sc.nextLine();
        for (int i = 0; i < cnt; i++) {
            if (std[i].reg_no.equals(sno)) {
                return i;
            }
        }
        System.out.println("Student is not registered");
        return -1;
    }

    public void checkOut(Books b) {
        int idx = this.isStudentAvailable();
        if (idx != -1) {
            System.out.println("Checking out Book");
            book bk = b.chkOut();
            if (bk != null) {
                for (int i = 0; i < std[idx].countB; i++) {
                    if (bk.equals(std[idx].borBook[i])) {
                        System.out.println("you already have this book");
                        return;
                    }
                }
                if (std[idx].countB <= 5) {
                    System.out.println("Adding book");
                    std[idx].borBook[std[idx].countB] = bk;
                    std[idx].countB++;
                    return;
                } else {
                    System.out.println("Student can not borrow more than 5 books");
                    return;
                }
            } else {
                System.out.println("Book is not available");
            }
        }
    }

    public void checkIn(Books b) {
        int idx = this.isStudentAvailable();

        if (idx != -1) {
            student s = std[idx];
            System.out.println("Sno.\t\tName\t\tAuthor Name");
            for (int i = 0; i < s.borBook.length; i++) {
                if (s.borBook[i] == null) {
                    continue;
                }
                System.out.println(s.borBook[i].sNo + "\t\t"
                        + s.borBook[i].name + "\t\t"
                        + s.borBook[i].auth_name);

            }
            System.out.println("Enter the serial number you want to check in");
            int sno = sc.nextInt();
            for (int i = 0; i < s.borBook.length; i++) {
                if (s.borBook[i] == null) {
                    continue;
                } else if (sno == s.borBook[i].sNo) {
                    b.chkIn(s.borBook[i]);
                    s.borBook[i] = null;
                    s.countB--;
                    return;
                }
            }
            System.out.println("Book of Serial no " + sno + " not found");
        }
    }
}

public class code {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Books ob = new Books();
        students stu = new students();
        int choice;
        System.out.println("___________________________________________________");
        System.out.println("          Welcome to Saksham's Library");
        System.out.println("___________________________________________________");
        do {
            ob.displayMenu();
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    book b = new book();
                    ob.addbook(b);
                    break;
                case 2:
                    ob.UpgradeQty();
                    break;
                case 3:
                    System.out.println("Press 1 to Search by Serial number");
                    System.out.println("Press 2 to Search by Book name");
                    int c2 = sc.nextInt();
                    switch (c2) {
                        case 1:
                            ob.searchBySno();
                            break;
                        case 2:
                            ob.searchByname();
                            break;
                    }
                    break;
                case 4:
                    ob.showAllbook();
                    break;
                case 5:
                    student s = new student();
                    stu.addStudent(s);
                    break;
                case 6:
                    stu.showAllStudent();
                    break;
                case 7:
                    stu.checkOut(ob);
                    break;
                case 8:
                    stu.checkIn(ob);
                    break;
                case 0:
                    System.out.println("Thankyou for using My library\nBieee!!!!");
                    break;
                default:
                    System.out.println("Enter values between 0 to 8");
                    break;
            }

        } while (choice != 0);
    }
}

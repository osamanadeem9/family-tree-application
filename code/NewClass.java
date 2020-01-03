package newpackage;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;

class Node {

    public String name;
    public Node child;
    public int age, height;
    public boolean gender;
    public Node sibling;

    Node() {
        child = sibling = null;
        age = 0;
        height = 0;
        name = "";
        gender = false;
    }
    F3AddPerson addperson = new F3AddPerson();
    Scanner scanner = new Scanner(System.in);

    public void getData() {
        name = addperson.jTextField1_val;
        age = addperson.jTextField2_val;

        char ch = addperson.jTextField3_val;

        if (ch == 'm') {
            gender = true;
        }

        System.out.println(name);
        System.out.println(age);
        System.out.println(gender);
    }
}

class familytree {

    Scanner scanner = new Scanner(System.in);
    F3AddPerson addperson = new F3AddPerson();
    F4Relationship relationship = new F4Relationship();
    static String relationship_statement;
    public Node start = null;

    void addNew() {
        Node temp = new Node();
        temp.getData();

        if (start == null) {
            start = temp;
            temp.height = 0;

        } else {
            String name = addperson.jTextField4_val;
            int choice = addperson.jTextField5_val;
            switch (choice) {
                case 1:
                    addChild(search(name), temp);
                    break;
                case 2:
                    addSibling(search(name), temp);
                    break;
            }
        }
        System.out.println("Person added.");
    }

    void destroy(Node ptr) {
        Node temp = ptr;

        if (ptr == null) {
            return;
        }
        while (ptr != null) {
            destroy(ptr.child);
            temp = ptr;
            ptr = ptr.sibling;
            temp = null;
        }

        start = null;
    }

    String show(Node ptr) {
        String gender;
        if (ptr.gender == true) {
            gender = "male";
        } else {
            gender = "female";
        }
        String message = "Name: " + ptr.name + "    Age: " + ptr.age + "    Gender: " + gender;
        return message;
    }

    void display(Node ptr) {
        if (ptr == null) {
            return;
        }
        while (ptr != null) {
            System.out.println(ptr.name);
            display(ptr.child);
            ptr = ptr.sibling;
        }
    }
    ArrayList<String> grand_parent = new ArrayList();
    ArrayList<String> children = new ArrayList();
    ArrayList<String> grand_children = new ArrayList();
    ArrayList<String> great_grand_children = new ArrayList();

    void display3(Node ptr) {
        if (ptr == null) {
            return;
        }
        while (ptr != null) {
            if (ptr.height == 0) {
                grand_parent.add(ptr.name);
            } else if (ptr.height == 1) {
                children.add(ptr.name);
            } else if (ptr.height == 2) {
                grand_children.add(ptr.name);
            } else if (ptr.height == 3) {
                great_grand_children.add(ptr.name);
            }

            display3(ptr.sibling);
            ptr = ptr.child;
        }
        
    }

    void display4(){
        for (int i = 0; i < grand_parent.size(); i++) {
            grand_parent2= grand_parent2+ grand_parent.get(i)+" ";
        }
        for (int i = 0; i < children.size(); i++) {
            children2 = children2+ children.get(i)+", ";
        }
        for (int i = 0; i < grand_children.size(); i++) {
            grand_children2 = grand_children2+ grand_children.get(i)+ ", ";
        }
        if (grand_parent2!=null)
            grand_parent2 = grand_parent2.substring(4);
        if (children2!= null)
            children2 = children2.substring(4);
        if (grand_children2!=null)
            grand_children2 = grand_children2.substring(4);
    }
    String grand_parent2, children2, grand_children2, great_grand_children2;

    void findRelation() {
        String person1, person2;
        person1 = relationship.jTextField1_val;
        person2 = relationship.jTextField2_val;

        Node ptr1 = search(person1);
        Node ptr2 = search(person2);
        Node ptr;
        Node ptrk1 = ptr1;
        Node ptrk2 = ptr2;
        switch (ptr1.height - ptr2.height) {
            case 0:
                String s1 = "sister";
                if (ptr1.gender = true) {
                    s1 = "brother";
                }
                ptr = ptr1;
                while (ptr != null) {
                    if (ptr == ptr2) {
                        relationship_statement = (person1 + " is " + person2 + "'s " + s1);
                        return;
                    }
                    ptr = ptr.sibling;
                }
                ptr = ptr2;
                while (ptr != null) {
                    if (ptr == ptr1) {
                        relationship_statement = (person1 + " is " + person2 + "'s " + s1);
                        return;
                    }
                    ptr = ptr.sibling;
                }
                relationship_statement = (person1 + " and " + person2 + " are cousins.");
                break;

            case 1:
                String str = "daughter";
                if (ptr1.gender == true) {
                    str = "son";
                }
                ptr2 = ptr2.child;
                while (ptr2 != null) {
                    if (ptr2 == ptr1) {
                        relationship_statement = (person1 + " is " + person2 + "'s " + str);
                        return;
                    }
                    ptr2 = ptr2.sibling;
                }
                str = "niece";
                if (ptr1.gender == true) {
                    str = "nephew";
                }
                relationship_statement = (person1 + " is " + person2 + "'s " + str);
                break;

            case -1:
                String str2 = "mother";
                if (ptrk1.gender == true) {
                    str2 = "father";
                }
                ptr = ptrk1.child;

                while (ptr != null) {
                    if (ptr == ptrk1) {
                        relationship_statement = (person1 + " is " + person2 + "'s " + str2);
                        return;
                    }
                    ptr = ptr.sibling;
                }
                str2 = "aunt";
                if (ptrk1.gender == true) {
                    str2 = "uncle";
                }
                relationship_statement = (person1 + " is " + person2 + "'s " + str2);
                break;

            case 2:
                String str3 = "daughter";
                if (ptr2.gender == true) {
                    str3 = "son";
                }
                ptr2 = ptr2.child.child;
                while (ptr2 != null) {
                    if (ptr2 == ptr1) {
                        relationship_statement = (person1 + " is grand " + str3 + " of " + person2);
                        return;
                    }
                    ptr2 = ptr2.sibling;
                }
                break;
            case -2:
                String str4 = "mother";
                if (ptr1.gender == true) {
                    str4 = "father";
                }
                ptr1 = ptr1.child.child;

                while (ptr1 != null) {
                    if (ptr1 == ptr2) {
                        relationship_statement = (person1 + " is grand " + str4 + " of " + person2);
                        return;
                    }
                    ptr1 = ptr1.sibling;
                }
                break;
            default:
                relationship_statement = ("Too far relationship");
                break;
        }

    }

    Node search(String s) {
        Node ptr = start;

        if (s.equals(ptr.name)) {
            return ptr;
        } else if (moveRight(start, s) != null) {
            return moveRight(start, s);
        } else if (moveDown(start, s) != null) {
            return moveDown(start, s);
        } else {
            return null;
        }
    }

    Node moveDown(Node ptr, String s) {
        ptr = ptr.child;

        while (ptr != null) {
            if (s.equals(ptr.name)) {
                return ptr;
            } else if (moveRight(ptr, s) != null) {
                return moveRight(ptr, s);
            } else {
                ptr = ptr.child;
            }
        }
        return null;
    }

    Node moveRight(Node ptr, String s) {
        ptr = ptr.sibling;

        while (ptr != null) {
            if (s.equals(ptr.name)) {
                return ptr;
            } else if (moveDown(ptr, s) != null) {
                return moveDown(ptr, s);
            } else {
                ptr = ptr.sibling;
            }
        }
        return null;
    }

    void addChild(Node one, Node two) {
        if (one.child == null) {
            one.child = two;
        } else {
            addSibling(one.child, two);
        }

        two.height = one.height + 1;
    }

    void addSibling(Node one, Node two) {
        while (one.sibling != null) {
            one = one.sibling;
        }
        one.sibling = two;

        two.height = one.height;
    }

}

public class NewClass extends JFrame {

    public static void main(String args[]) {

        familytree tree[] = new familytree[10];
        for (int i = 0; i < 10; i++) {
            tree[i] = new familytree();

        }
        int choice, a, a1, a2;
        char c;
        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the family tree number: ");
        a = scanner.nextInt();

        while (true) {
            System.out.println("\n\nFamily tree no = " + a);
            System.out.print("\n\n\t1. Add new person\n\t2. Find relationship b/w two persons\n\t3. Search\n\t4. Destroy\n\t5. Display\n\t6. Change family tree\n\t7. Connect two family trees\n\t8. Exit\n\n\tEnter your choice = ");
            choice = scanner.nextInt();

            switch (choice) {
                default:
                    System.out.println("Invalid choice!");
                    break;
                case 1:
                    tree[a].addNew();
                    break;
                case 2:
                    tree[a].findRelation();
                    break;
                case 3:

                    System.out.print("Enter name of person to find: ");
                    name = scanner.next();
                    tree[a].show(tree[a].search(name));
                    break;
                case 4:
                    tree[a].destroy(tree[a].start);

                    System.out.println("Tree has been destroyed.");
                    break;
                case 5:
                    tree[a].display(tree[a].start);
                    break;
                case 6:

                    System.out.print("Enter family tree number: ");
                    a = scanner.nextInt();
                case 8:
                    return;
            }
            System.out.println("\nPress any key to continue...");
            c = scanner.next().charAt(0);
        }

    }

}

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List list = new List(10);

        System.out.println(list.insert(1));
        System.out.println(list.insert(3));
        System.out.println(list.insert(4));
        System.out.println(list.insert(8));
        System.out.println(list.insert(8));
        System.out.println(list.insert(2));
        System.out.println(list.insert(67));
        System.out.println(list.insert(7));
        System.out.println(list.insert(1));
        System.out.println(list.insert(21));
        System.out.println(list.insert(12));
        System.out.println(list.insert(31));
        System.out.println(list.insert(56));

        System.out.println(list.remove(1));

        System.out.println(list.insert(123));

        list.print();
    }
}
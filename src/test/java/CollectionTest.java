import org.astongrouph.collection.*;
import org.astongrouph.model.Student;

public class CollectionTest {
    public static void main(String[] args) {
        CustomArrayList <Integer> list = new CustomArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        System.out.println(list);
        list.remove(6);
        System.out.println(list);
        list.set(0, 244);
        System.out.println(list);
        System.out.println(list.size() + " | " + list.isEmpty());
        for (int i = 0; i < 20; i++) {
            list.add((int) (Math.random() * 10) + 1);
        }
        System.out.println("SIZE: " + list.size() + list);
        list.remove(0);
        System.out.println(list);
        System.out.println(list.get(28)); //Ошибка: индекс вне допустимого
        CustomArrayList <Student> studentList = new CustomArrayList<>(-10);//Ошибка: размер меньше 0
    }
}

package Homework.HW5;

public class HandleSplit {

    //testing on split
    public static void main(String[] args) {
        String s = "";
        String[] s1 = s.split(",\\s*");
        System.out.println(s1[0]);
        System.out.println(s1.length);
    }

}

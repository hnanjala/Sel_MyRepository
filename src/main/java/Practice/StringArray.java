package Practice;

public class StringArray {
public static void main(String args[])
{
String[][] a=new String[10][10];
String b="EXPR149180002";
a[0][0]=String.valueOf(b);
a[0][1]=String.valueOf(1);
System.out.println("a[0][0]:"+a[0][0]+"; a[0][1]:"+a[0][1]);
}
}

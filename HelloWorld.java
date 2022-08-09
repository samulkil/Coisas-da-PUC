import java.util.Scanner;
class HelloWorld{
public static void main (String args[]){
Scanner leitor = new Scanner(System.in);
String a;
int b;
double c;
char d;
System.out.println("String?");
a = leitor.next();
System.out.println("Inteiro?");
b = leitor.nextInt();
System.out.println("DOuble?");
c = leitor.nextDouble();
System.out.println("Char?");
d = leitor.next().charAt(0);


System.out.println(a + b + c + d);



}
}
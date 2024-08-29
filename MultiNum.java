import java.util.Scanner;
public class MultiNum
{
 public static void main(String arg[])
 {
  double num1, resultado;
  int num2;
  Scanner lectura = new Scanner(System.in);
  System.out.println("Digite el primer numero real");
  num1 = lectura.nextDouble();
  System.out.println("Digite el segundo numero");
  num2 = lectura.nextInt();
  resultado = num1 * num2;
  System.out.println("El resultado de la multiplicacion es de: "+resultado);
 }
}

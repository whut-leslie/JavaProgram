package Test3;
import Test3.Triangle;
import Test3.Circle;
import Test3.Lader;
public class Main {
	public static void main(String []args) throws Exception{
		Triangle t=new Triangle(2,3,4);
		System.out.print("���߳��ֱ�Ϊ��"+t.getx()+" ");
		System.out.print(t.gety()+" ");
		System.out.print(t.getz()+" ");
		System.out.println();
		System.out.println("�������ܳ�Ϊ��"+t.get_perimeter()+" ");
		System.out.println("���������Ϊ"+t.get_area()+" ");
		Lader l=new Lader(2,3,4);
		System.out.println("���ε����Ϊ"+l.get_area());
		Circle c=new Circle(2);
		System.out.println("Բ���ܳ�Ϊ"+c.get_perimeter());
		System.out.println("Բ�����Ϊ"+c.get_area());
		
		
	}

}

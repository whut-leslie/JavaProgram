package Test3;
import Test3.Triangle;
import Test3.Circle;
import Test3.Lader;
public class Main {
	public static void main(String []args) throws Exception{
		Triangle t=new Triangle(2,3,4);
		System.out.print("三边长分别为："+t.getx()+" ");
		System.out.print(t.gety()+" ");
		System.out.print(t.getz()+" ");
		System.out.println();
		System.out.println("三角形周长为："+t.get_perimeter()+" ");
		System.out.println("三角形面积为"+t.get_area()+" ");
		Lader l=new Lader(2,3,4);
		System.out.println("梯形的面积为"+l.get_area());
		Circle c=new Circle(2);
		System.out.println("圆的周长为"+c.get_perimeter());
		System.out.println("圆的面积为"+c.get_area());
		
		
	}

}

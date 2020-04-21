package Test3;
public class Circle extends Tuxing {
	static final double PI=3.1415926;
public	Circle(double r){
		super(r);
	}
public double get_perimeter(){
	return 2*PI*getx();
}
public double get_area(){
	return PI*getx()*getx();
}
}

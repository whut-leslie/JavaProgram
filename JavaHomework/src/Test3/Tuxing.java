package Test3;

public class Tuxing {
private	double x,y,z,perimeter,area;
	public Tuxing (){
		x=0;
		y=0;
		z=0;
	}
public Tuxing(double x){
	this.x=x;
	}
public Tuxing(double x,double y,double z){
	this.x=x;
	this.y=y;
	this.z=z;
}
public double getx(){
	return x;
}
public double gety(){
	return y;
}
public double getz(){
	return z;
}

public void seta(double a){
	 x=a;
}

public void setb(double a){
	 y=a;
}

public void setc(double a){
	 z=a;
}
public double get_perimeter() throws Exception{
	return perimeter;
}
public double get_area() throws Exception{
	return area;
}




}

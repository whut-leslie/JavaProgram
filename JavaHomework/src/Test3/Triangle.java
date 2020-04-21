package Test3;
public class Triangle extends Tuxing {
private boolean isTriangle;
public Triangle(double x,double y,double z){
super(x,y,z);
isTriangle=triangle();
}
//判断三角形
public boolean triangle(){
	double a=getx();
	double b=gety();
	double c=getz();
	if((a+b>c)&&(a+c>b)&&(a-b<c)&&(a-c<b)&&(b-c<a))
		return true;
	else return false;
}

//周长、面积
public double get_perimeter() throws Exception{
	if(isTriangle)
	{
		return  getx()+gety()+getz();
		}
	throw new Exception("三边不能构成三角形！");
	
}
public double get_area() throws Exception{
if(isTriangle)
	{ double p=(getx()+gety()+getz())/2;
	return Math.sqrt(p*(p-getx())*(p-gety())*(p-getz()))/4;
	}
		throw new Exception("三边不能构成三角形！");
	
}
}

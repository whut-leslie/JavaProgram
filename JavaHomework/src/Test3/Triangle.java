package Test3;
public class Triangle extends Tuxing {
private boolean isTriangle;
public Triangle(double x,double y,double z){
super(x,y,z);
isTriangle=triangle();
}
//�ж�������
public boolean triangle(){
	double a=getx();
	double b=gety();
	double c=getz();
	if((a+b>c)&&(a+c>b)&&(a-b<c)&&(a-c<b)&&(b-c<a))
		return true;
	else return false;
}

//�ܳ������
public double get_perimeter() throws Exception{
	if(isTriangle)
	{
		return  getx()+gety()+getz();
		}
	throw new Exception("���߲��ܹ��������Σ�");
	
}
public double get_area() throws Exception{
if(isTriangle)
	{ double p=(getx()+gety()+getz())/2;
	return Math.sqrt(p*(p-getx())*(p-gety())*(p-getz()))/4;
	}
		throw new Exception("���߲��ܹ��������Σ�");
	
}
}

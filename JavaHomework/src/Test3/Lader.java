package Test3;

public class Lader extends Tuxing{
	Lader(double x,double y,double z){
		super(x,y,z);
	}

    public double get_area(){
    	double a=getx();
    	double b=gety();
    	double h=getz();
	return (a+b)*h/2;
}

}

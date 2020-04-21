package Test2;

import java.text.DecimalFormat;
import java.util.Random;

public class Second1 {

	public static void main(String[]args ){
		
		int [][]ar;
		int []as;
		double []at;
		ar=new int[10][10];
		as=new int[10];
		at=new double[10];
		int i,j;
		Random random=new Random();
		for(i=0;i<10;i++)
			for(j=0;j<10;j++){
				ar[i][j]=random.nextInt(100);//产生0到100的随机数
			}
		int temp;
		for( i=0;i<10;i++)
		{
			temp=ar[i][0];
			for(j=0;j<10;j++)
			{
				if(temp<ar[i][j])
					{temp=ar[i][j];
					
					}
				as[i]=temp;
			}
		}
		for(j=0;j<10;j++){
			at[j]=0;
			for(i=0;i<10;i++){
				at[j]=at[j]+(double)ar[i][j]/10;
			}
			}
		
		
		
		
		
		for(i=0;i<10;i++)
		{
				System.out.print(as[i]+" ");
			}
		
		System.out.println();
		DecimalFormat df=new DecimalFormat("#.00");
		for(i=0;i<10;i++){
			System.out.print(df.format(at[i])+"    ");//保留两位小数
		
		}
		
	}
	
	
	
	
	}

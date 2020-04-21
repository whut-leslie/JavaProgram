package Test2;

import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Second2{
	//找鞍点
	public static void findandian(int a[][]){
		int b[]=new int[3];
		int[] c=new int[4];
		int i,j,temp;
		for( i=0;i<3;i++)
		{
			b[i]=a[i][0];
			for(j=0;j<4;j++)
			{
				if(b[i]<a[i][j])
					{b[i]=a[i][j];
					}
				
			}System.out.println("第"+(i+1)+"行的最大数为"+b[i]);
		}
		
		//列最大
		for( j=0;j<4;j++)
		{
			c[j]=a[0][j];
			for(i=0;i<3;i++)
			{
				if(c[j]>a[i][j])
					{
					c[j]=a[i][j];
	              }
			}System.out.println("第"+(j+1)+"列的最小数为"+c[j]);
		}		
		temp=1;
		for(i=0;i<3;i++)
			for(j=0;i<4;j++)
			{
				if(a[i][j]==b[i]&&a[i][j]==c[j]){
					System.out.println("输入的数组中的鞍点为a["+i+"]["+j+"]="+a[i][j]);
				}
				else
					temp=0;
			}
		
		if(temp==0){
			System.out.println("输入的数组中没有鞍点");
		}
	}
	

public static void main(String[]args){
	int i,j,temp;
	int a[][]=new int[3][4];
	System.out.println("请输入数组：");
Scanner in=new Scanner(System.in);
for(i=0;i<3;i++)
	for(j=0;j<4;j++){
		a[i][j]=in.nextInt();
	}
	findandian(a);
	
	
	
	
}
		
}

package Test2;

import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Second2{
	//�Ұ���
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
				
			}System.out.println("��"+(i+1)+"�е������Ϊ"+b[i]);
		}
		
		//�����
		for( j=0;j<4;j++)
		{
			c[j]=a[0][j];
			for(i=0;i<3;i++)
			{
				if(c[j]>a[i][j])
					{
					c[j]=a[i][j];
	              }
			}System.out.println("��"+(j+1)+"�е���С��Ϊ"+c[j]);
		}		
		temp=1;
		for(i=0;i<3;i++)
			for(j=0;i<4;j++)
			{
				if(a[i][j]==b[i]&&a[i][j]==c[j]){
					System.out.println("����������еİ���Ϊa["+i+"]["+j+"]="+a[i][j]);
				}
				else
					temp=0;
			}
		
		if(temp==0){
			System.out.println("�����������û�а���");
		}
	}
	

public static void main(String[]args){
	int i,j,temp;
	int a[][]=new int[3][4];
	System.out.println("���������飺");
Scanner in=new Scanner(System.in);
for(i=0;i<3;i++)
	for(j=0;j<4;j++){
		a[i][j]=in.nextInt();
	}
	findandian(a);
	
	
	
	
}
		
}

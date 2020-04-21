package Test2;

public class Second3 {
	public static void main(String[]args){
		int [][] a = new int[10][10];
		//先初始化为1
		for(int i = 0 ; i < a.length ; i++){
			for(int j = 0 ; j < a[i].length ; j++){
				a[i][j] = 1 ;
			}
		}
		//杨辉三角核心算法
		//从第三行开始满足以下规律
		for(int i = 2 ; i < a.length ; i++)
		{
			for(int j = 1 ; j < i ;j++)
			{
				a[i][j] = a[i-1][j-1] + a[i-1][j] ;//上与上左之和
			}
		}
		for(int i = 0 ; i < a.length ; i++){
			for(int j = 0 ; j <=i ; j++){
				System.out.print(a[i][j] + "\t");
			}
			System.out.println();
		}
		
		
	}

}

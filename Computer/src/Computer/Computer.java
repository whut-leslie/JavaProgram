package Computer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import java.util.ArrayList;
//001֧��+-*/������
//002Ԥ��  ֧��С����  ��n���� ��n����

import java.util.Scanner;
@SuppressWarnings("unused")
public class Computer{
	private String cacular;
	public String getCacular() {
		return cacular;
	}
	public void setCacular(String cacular) {
		this.cacular = cacular;
	}
	public Computer(String ca){
		this.cacular = ca;
	}
	public Computer(){
	}
	    //1+(2-3*(5+1)/(-4+2*(-6))-3*(+6-(-2)))+6*(5-4)
	public static String center(String strIn)//����������
	    {
	        //����ǰ������֤����
	        judge(strIn);
	        String str0=strIn;//���汸��
	        strIn=addBrackets(strIn);//��������Χ������
	        ArrayList<String> jiSuan1=new ArrayList<String>();
	        ArrayList<Double> jiSuan2=new ArrayList<Double>();
	        ArrayList<Double> re=new ArrayList<Double>();
	        ArrayList<Double> num=separateNum(strIn);//��������
	        ArrayList<Integer> sumNum=new ArrayList<Integer>();
	        ArrayList<Integer> sumSym=new ArrayList<Integer>();
	        ArrayList<String>  sym=simpleSym(strIn);//ʽ�Ӽ�Ϊ����ʽ
	        sumNum=sumNumber(sym);//ͳ������
	        sumSym=sumSymbol(sym);//ͳ�Ʒ���
	        double result=0;//�ܼ�����
	        int cm=brackets0(sym);//��������
	        int bra [][]=new int[cm][2];
	        bra=brackets(sym);//����ͳ��
	        
	        int m=0;//m ������
	        for(int i=0;i<bra.length;i++)
	        {
	            if(m<bra[i][0])
	            {
	                m=bra[i][0];
	            }
	        }
	        int i=0,k,t=0,d,f=1,g1=1,g2=1,n,r1=-1,r2=0,l1=-1,l2=-1;//t,d ���Ӧ��һ������
	        for(i=m;i>=0;i--)//����ѭ��
	        {
	            for(t=0;t<bra.length;t++)
	            {
	                if(bra[t][0]==i)//ÿһ������
	                {
	                    d=t+1;
	                    jiSuan1.clear();//���
	                    jiSuan2.clear();
	                    re.clear();
	                /*    for(;;)//�ҳ����Ӧ����һ������
	                    {
	                        d++;
	                        if(bra[d][0]==bra[t][0])
	                        {
	                            break;
	                        }
	                    }*/
	                    l1=bra[t][1];
	                    r1=bra[d][1];
	                    /*for(k=bra[t][1]+1;k<bra[d][1];k++)//����Ҫ����Ĳ�������jiSuan1��
	                    {
	                        
	                        jiSuan1.add(sym.get(k));
	                        if(g1==1)
	                        {
	                            k1=k;
	                            g1=0;
	                        }
	                    }*/
	                //    sym.add(bra[t][0],"0");
	                    //44.7-4*1
	                    for(k=0,g1=1,l1=0,r1=0;k<sumSym.size();k++)//����Ҫ����Ĳ�������jiSuan1��sumSym.get(n)
	                    {
	                        n=sumSym.get(k);
	                        if(n>bra[t][1]&&n<bra[d][1])
	                        {
	                            jiSuan1.add(sym.get(n));
	                            r1++;
	                        if(g1==1)
	                            {
	                                l1=k;
	                                g1=0;
	                            }
	                        }
	                    }
	                    for(k=0,g2=1,l2=0,r2=0;k<sumNum.size();k++)//����Ҫ����Ĳ�������jiSuan2��sumNum.get(k)
	                    {
	                        n=sumNum.get(k);
	                        if(n>bra[t][1]&&n<bra[d][1])
	                        {
	                            jiSuan2.add(num.get(k));
	                            r2++;
	                        if(g2==1)
	                            {
	                                l2=k;
	                                g2=0;
	                            }
	                        }
	                    }
	                    for(int x=bra[t][1];x<=bra[d][1]&&l1>=0&&r1>=0;x++)
	                    {
	                        sym.set(x, "@");
	                    }
	                    //���ú�������
	                    result=caculate(jiSuan1,jiSuan2);
	                    //ɾ��
	                    int z=bra[t][1];
	                    while(z>=0)
	                    {
	                        sym.remove(z);
	                        z=sym.indexOf("@");
	                        
	                    }
	                    sym.add(bra[t][1],"0");
	                    for(z=0;z<r2;z++)
	                    {
	                        num.remove(l2);
	                    }
	                    num.add(l2, result);
	                    //����
	                    bra=brackets(sym);
	                    sumNum=sumNumber(sym);
	                    sumSym=sumSymbol(sym);
	                    //System.out.println(d);
	                    t=0;
	                }
	            }
	        }
	        //output(str0,result);
	        String ans=str0+"="+result;
	        return ans;
	    }

	    private static ArrayList<String> sToA(String s)  //�� String ת��Ϊ ArrayList
	    {
	       ArrayList<String> a=new ArrayList<String>();
	       for(int i=0;i<s.length();i++)
	       {
	           a.add(s.substring(i, i+1));
	       }
	       return a;
	    
	    }
	    private static String aToS(ArrayList<String> a)  //�� String ת��Ϊ ArrayList
	    {
	       String s="";
	       for(int i=0;i<a.size();i++)
	       {
	           s=s+a.get(i);
	       }
	      return s;
	    }
	    
	 
	    private static String addBrackets(String s)
	    {
	        if(!(s.charAt(0)=='('&&s.charAt(s.length()-1)==')'))
	        {
	            s="("+s+")";
	        }
	        else if(s.indexOf(")")!=s.length()-1)
	        {
	            s="("+s+")";
	        }
	        return s;
	    }
	    private static int brackets0(ArrayList<String> str)   //ʵ�����ŵĴ���ͳ��
	    {
	        ArrayList<String> s=new ArrayList<String>(str);
	        int c=0,i=0;
	        for(;;)
	        {
	            if((i=s.indexOf("("))<0)
	            {
	                break;
	            }
	            s.remove(i);
	                c++;
	        }
	        for(;;)
	        {
	            if((i=s.indexOf(")"))<0)
	            {
	                break;
	            }
	            s.remove(i);
	                c++;
	        }
	            return c;
	    }
	    
	    private static ArrayList<Integer> sumNumber(ArrayList<String> s) //ʵ�����ֵ�ͳ��
	    {
	        ArrayList<Integer> a=new ArrayList<Integer>();
	        int i=0;
	        String str;
	        char c;
	        for(i=0;i<s.size();i++)
	        {
	            str=s.get(i);
	            c=str.charAt(0);
	            if(c=='0')
	            {
	                a.add(i);
	            }
	            
	        }
	        return a;
	    }
	    
	    private static ArrayList<Integer> sumSymbol(ArrayList<String> s) //ʵ�ַ��ŵ�ͳ��
	    {
	        ArrayList<Integer> a=new ArrayList<Integer>();
	        int i=0;
	        String str;
	        char c;
	        for(i=0;i<s.size();i++)
	        {
	            str=s.get(i);
	            c=str.charAt(0);
	            if(c!='0'&&c!='('&&c!=')')
	            {
	                a.add(i);
	            }
	            
	        }
	        return a;
	    }
	    private static int[][] brackets( ArrayList<String> sym) //ʵ�����ŵ�ͳ��
	    {
	       //                   +(-*(+)/(-+*(-))-*(+-(-)))+*(-)
	       ArrayList<Integer> b1=new ArrayList<Integer>();//����
	       ArrayList<Integer> b2=new ArrayList<Integer>();//λ��
	       int c=-1;//����
	       int cm=0;//������
	       int i,f=1;
	      String s=aToS(sym);
	       for( i=0;i<s.length();i++)
	       {
	       
	           if(s.charAt(i)=='(')
	           {
	               if(f==1)
	               {
	               c++;
	               }
	               f=1;
	               b1.add(c);
	            b2.add(i);
	           }
	           if(s.charAt(i)==')')
	           {
	               if(f==0)
	               {
	               c--;
	               }
	               f=0;
	               b1.add(c);
	            b2.add(i);
	           }
	           if(cm<c)
	           {
	               cm=c;
	           }
	           
	       }
	       

	             int bra[][]=new int[b1.size()][2];//��һ ά��ţ��ڶ�ά������λ��
	             for(i=0;i<b1.size();i++)
	             {
	                 bra[i][0]=b1.get(i);
	                 bra[i][1]=b2.get(i);
	             }
	         
	        return bra;
	    
	    }
	    
	   
	    private static double caculate(ArrayList<String> s,ArrayList<Double> a) //����
	    {
	       double result=0,left,right;
	       int i=-1;
	       while((i=s.indexOf("��"))>=0)
	       { 
	           left=1/a.remove(i);
	            right=a.remove(i);
	           try
	           {
	               if(right<0)
	               {
	                   throw new Exception("������������С���㣡");
	               }
	             
	               a.add(i,Math.pow(right, left));
	               
	           }
	           catch(Exception e)
	              {
	               System.out.println(e.getMessage());
	              }
	           s.remove(i);
	       }
	        i=0;
	        while((i=s.indexOf("^"))>=0)
	           {
	               left=a.remove(i);
	               right=a.remove(i);
	               a.add(i,Math.pow(left, right));
	               s.remove(i);
	           }
	            i=0;
	       while((i=s.indexOf("/"))>=0)
	       {
	               left=a.remove(i);
	               right=a.remove(i);
	               try
	               {
	                   if(Math.abs(right)<10e-8)
	                   {
	                       throw new Exception("��������Ϊ�㣡");
	                   }
	                   a.add(i, left/right);
	               }
	               catch(Exception e)
	               {
	                   System.out.println(e.getMessage());
	               }
	               s.remove(i);
	       }
	       i=0;
	          while((i=s.indexOf("*"))>=0)
	          {
	              left=a.remove(i);
	              right=a.remove(i);
	              a.add(i, left*right);
	              s.remove(i);
	          }
	          i=0;
	          while((i=s.indexOf("-"))>=0)
	          {
	              left=a.remove(i);
	              right=a.remove(i);
	              a.add(i, left-right);
	              s.remove(i);
	          }
	          i=0;
	          while((i=s.indexOf("+"))>=0)
	          {
	              left=a.remove(i);
	              right=a.remove(i);
	              a.add(i, left+right);
	              s.remove(i);
	          }
	       
	    //end
	           result=a.get(0);
	        return result;
	    }

	    
	    private static ArrayList<Double> separateNum(String s) 
	    {
	        ArrayList<Double> num=new ArrayList<Double>();
	        String c="";
	        int i=0,t=0,f=0,l,p,l2=0,minus=0;

	        double d=0,a,    m=0;
	        for(i=0;i<s.length();i++)
	        {
	            
	            if((s.charAt(i)>='0'&&s.charAt(i)<='9')||s.charAt(i)=='.')
	            {
	                c=c+s.charAt(i);
	                f=1;
	                if(s.charAt(i-1)=='-'&&(s.charAt(i-2)=='('||s.charAt(i-2)=='��'||s.charAt(i-2)=='^'))
	                {
	                    c="-"+c;
	                }
	            //    System.out.println("add"+c);
	            }
	            else if(f==1)
	            {
	                if(c.charAt(0)=='-')
	                {
	                    minus=1;
	                    c=c.substring(1);
	                }
	                //�ַ�ת����
	                p=c.indexOf('.');
	                l=c.length();
	                if(p>0)
	                {
	                    l2=l-p;//С��
	                    l=p;//����
	                }
	                for(t=0,m=10,d=0;t<l&&t!=p;t++)
	                {
	                    a=c.charAt(t)-'0';
	                    d=d+a*Math.pow(m,l-1-t);    
	                }
	                if(p>0)
	                {
	                    for(t=1,m=0.1;t<l2;t++)
	                    {
	                        a=c.charAt(p+t)-'0';
	                        d=d+a*Math.pow(m,t);    
	                    }
	                }
	                if(minus==1)
	                {
	                    d=-1*d;
	                    minus=0;
	                }
	                num.add(d);
	                f=0;
	                c="";
	            }
	        }
	 
	        return num;
	    }
	    private static ArrayList<String> simpleSym(String s)
	    {
	        ArrayList<String> sym=new ArrayList<String>();
	        int f=0,f2=0;

	        s="0+"+s;
	            for(int i=0;i<s.length();i++)
	            {
	                if((s.charAt(i)>='0'&&s.charAt(i)<='9')||s.charAt(i)=='.')
	                {
	                    f=1;
	                }
	                else if(f==1)
	                {
	                    sym.add("0");
	                    f=0;
	                }
	                if(s.charAt(i)=='+'||s.charAt(i)=='-'||s.charAt(i)=='^'||s.charAt(i)=='��')
	                {
	                    if(s.charAt(i-1)!='('&&s.charAt(i-1)!='��'&&s.charAt(i-1)!='^')
	                    {
	                        sym.add(s.substring(i,i+1));
	                    }
	                }
	                
	                if(s.charAt(i)=='*'||s.charAt(i)=='/'||s.charAt(i)=='('||s.charAt(i)==')')
	                {
	                        sym.add(s.substring(i,i+1));
	                }
	                    
	            }
	            //System.out.println(sym);

	                sym.remove(0);
	                sym.remove(0);
	            s=s.substring(2);
	        return sym;
	    }
	    
	    private static void judge(String s)//��֤ʽ���Ƿ���ȷ
	    {
	        try
	        {
	            //�ַ��Ƿ���ȷ
	            for(int i=0,f=0;i<s.length();i++)
	            {
	                f=0;
	                if(s.charAt(i)=='+'||s.charAt(i)=='-'||s.charAt(i)=='*'||s.charAt(i)=='/'||s.charAt(i)=='('||s.charAt(i)==')'||s.charAt(i)=='.'||s.charAt(i)=='^'||s.charAt(i)=='��')//����251
	                {
	                    
	                    f=1;
	                }
	                if(s.charAt(i)>='a'&&s.charAt(i)<='z'||s.charAt(i)>='A'&&s.charAt(i)<='Z')
	                {
	                    f=1;
	                }
	                if(s.charAt(i)>='0'&&s.charAt(i)<='9')
	                {
	                    f=1;
	                }
	                if(f==0)throw  new Exception("δʶ��ķ���\" "+s.charAt(i)+" \"��λ�ã�"+(i+1));
	            }
	            //�����Ƿ�ƥ��
	            int left=0,right=0;
	        for(int i=0;i<s.length();i++)
	        {
	            if(s.charAt(i)=='(')
	            {
	                left++;
	            }
	            if(s.charAt(i)==')')
	            {
	                right++;
	            }
	            
	        }
	        if(left!=right)throw  new Exception("���Ų�ƥ��");
	        //�����Ƿ���ȷ
	        for(int i=0;i<s.length();i++)
	        {
	            if(s.charAt(i)=='+'||s.charAt(i)=='-')
	            {
	                if(i>0&&(s.charAt(i-1)=='+'||s.charAt(i-1)=='-'||s.charAt(i-1)=='*'||s.charAt(i-1)=='/'))
	                {
	                    throw  new Exception("�����"+s.charAt(i)+"���û�����֣�λ�ã�"+(i+1));
	                }
	                if(s.charAt(i+1)=='+'||s.charAt(i+1)=='-'||s.charAt(i+1)=='*'||s.charAt(i+1)=='/'||s.charAt(i+1)==')'||s.charAt(i+1)=='��'||s.charAt(i+1)=='^')    
	                {
	                    throw  new Exception("�����"+s.charAt(i)+"�ұ�û�����֣�λ�ã�"+(i+1));
	                }
	                if(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='-'||s.charAt(s.length()-1)=='*'||s.charAt(s.length()-1)=='/'||s.charAt(s.length()-1)=='��'||s.charAt(s.length()-1)=='^')    
	                {
	                    throw  new Exception("�����"+s.charAt(s.length()-1)+"�ұ�û�����֣�λ�ã�"+(s.length()));
	                }
	            }
	            if(s.charAt(i)=='*'||s.charAt(i)=='/')
	            {
	                if(s.charAt(i-1)=='+'||s.charAt(i-1)=='-'||s.charAt(i-1)=='*'||s.charAt(i-1)=='/'||s.charAt(i-1)=='('||s.charAt(i-1)=='��'||s.charAt(i-1)=='^')
	                {
	                    throw  new Exception("�����"+s.charAt(i)+"���û�����֣�λ�ã�"+(i+1));
	                }
	                if(s.charAt(i+1)=='+'||s.charAt(i+1)=='-'||s.charAt(i+1)=='*'||s.charAt(i+1)=='/'||s.charAt(i+1)==')'||s.charAt(i+1)=='��'||s.charAt(i+1)=='^')    
	                {
	                    throw  new Exception("�����"+s.charAt(i)+"�ұ�û�����֣�λ�ã�"+(i+1));
	                }
	                if(i>0&&(s.charAt(s.length()-1)=='+'||s.charAt(s.length()-1)=='-'||s.charAt(s.length()-1)=='*'||s.charAt(s.length()-1)=='/'||s.charAt(s.length()-1)=='��'||s.charAt(s.length()-1)=='^'))    
	                {
	                    throw  new Exception("�����"+s.charAt(s.length()-1)+"�ұ�û�����֣�λ�ã�"+(s.length()));
	                }
	            }
	        }
	        }catch(Exception e)
	        {
	        	JOptionPane.showMessageDialog(null, e.getMessage()+"\n����������",  "����",JOptionPane.ERROR_MESSAGE); 
	        }
	    }
	   
	  
	
}
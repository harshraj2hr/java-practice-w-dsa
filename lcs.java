import java.util.*;
public class lcs {
	
	public int longestSeq(String a, String b, int i, int j) {
		if(i>=a.length() || j>=b.length())return 0;
		int cur=0;
		if(a.charAt(i)==b.charAt(j)) {
			cur+=1;
			cur+=longestSeq(a,b,i+1,j+1);
		}
		else {
			cur+=Math.max(longestSeq(a,b,i+1,j), longestSeq(a,b,i,j+1));
		}
		return cur;
	}
	
	public static void commonSeq(int[][] dp, int l1, int l2, String a) {
		int i=l1,j=l2;
		StringBuilder s=new StringBuilder();
		while(i>0 && j>0) {
			if(dp[i-1][j-1]+1!=dp[i][j]) { //don't use another while
				--i;
			}
			else {
			s.append(a.charAt(i-1));
			i-=1;j-=1;}
		}

		System.out.println("Common Sequence is: "+s.reverse());
	}
	
	public static void dpBottomUPCount(String a, String b) {
		int l1=a.length();
		int l2=b.length();
		int[][] dp= new int[l1+1][l2+1];
		for(int i=0;i<=l1;i++) {
			dp[i][0]=0;
		}
		for(int i=0;i<=l2;i++) {
			dp[0][i]=0;
		}
		
		for(int i=1;i<=l1;i++) {
			for(int j=1;j<=l2;j++) {
				if(a.charAt(i-1)==b.charAt(j-1)) {
					dp[i][j]=dp[i-1][j-1]+1;
				}
				else {
					dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		System.out.println();
		System.out.println("\nUsing Bottom-Up Dp we get: "+dp[l1][l2]);
		
		System.out.println("Finding the common sequence now");
		commonSeq(dp,l1,l2,a);
		System.out.println();
	}
	
	public static int dpTopDownCount(String a, String b, int[][] dp, int i, int j) {
		if(dp[i][j]!=-1)return dp[i][j];
		if(i==0 || j==0) {
			 dp[i][j]=0;
			 return 0;
		}
		if(a.charAt(i-1)==b.charAt(j-1)) {
			dp[i][j]=1 + dpTopDownCount(a,b,dp,i-1,j-1);
		}
		else {
			dp[i][j]=Math.max(dpTopDownCount(a,b,dp,i,j-1),dpTopDownCount(a,b,dp,i-1,j));
		}
		
		return dp[i][j];
	}
	
	public static void main(String[] args) {
		Scanner scn= new Scanner(System.in);
		String a,b;
		a=scn.next(); b=scn.next();
		lcs obj=new lcs();
		int i=0,j=0;
		System.out.print(obj.longestSeq(a,b,i,j)); //or make function static, then no need to create object
		
		dpBottomUPCount(a,b);
		
		int l1=a.length(),l2=b.length();
		int[][] dp= new int[l1+1][l2+1];
		for(int[] curRow: dp)
		{Arrays.fill(curRow, -1);}
		System.out.println();
		System.out.print("Using top-down dp, we get: ");
		dpTopDownCount(a,b,dp,l1,l2);
		System.out.println(dp[l1][l2]);
		System.out.print("And ");
		commonSeq(dp,l1,l2,a);
		scn.close();
	}
}

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Graycode 
{
	public static void main(String args[]) throws FileNotFoundException
	{
		graycoding(9,6);
	}
	
	/**
	 * generating graycode by giving number n as length of the graycode, k as base of graycode
	 * @param n
	 * @param k
	 * @throws FileNotFoundException
	 */
	public static void graycoding(int n, int k) throws FileNotFoundException
	{
	    
		int size = (int) Math.pow(k, n);
		int arr[][]= new int[size][n];
		int previousBit = -1;
		boolean inc = true;
		for(int i = 1; i< size; i++)
		{
			
			//copy previous row to second row
			for(int s = 0; s <n;s++)
			{
				arr[i][s]=arr[i-1][s];
			}
			//get which bit needs to be flipped
			int bitFlip = flip(i,k,n);
			
			//if the bit that needs to flip is 0, increment
			if(arr[i][bitFlip]==0)
			{
				arr[i][bitFlip]+=1;
				inc = true;
			}
			else
			{
				//check if the bit flip is same as previous one, if not, check if it needs to be increment or decrement
				if(previousBit != bitFlip)
				{
					inc = check(arr[i][bitFlip],k,arr[i-((int) Math.pow(k, bitFlip))-1][bitFlip]);
					if(inc == true)
					{
						arr[i][bitFlip]+=1;
					}
					else if(inc == false)
					{
						arr[i][bitFlip]-=1;
					}
				}
				else //if same bit flip, keep doing the operation
				{
					if(inc == true)
					{
						arr[i][bitFlip]+=1;
					}
					else if(inc == false)
					{
						arr[i][bitFlip]-=1;
					}
				}
			}
			
			previousBit = bitFlip;
			
			
		}
		
		//Print result into txt file.
		PrintWriter writer = new PrintWriter("gray.txt");
		for(int l = 0; l <size;l++)
		{
			for(int j = n-1; j>=0;j--)
			{
				writer.print(arr[l][j]);
			}
			writer.println("");
		}
	    writer.close();

	    
	}
	
	/**
	 * Check function check if the bit needs to be increment or decrement
	 * @param num1 the current number at the bit before being flipped
	 * @param num2 number k
	 * @param num3 the "previous" number that was being flipped in same bit position
	 * @return return if the number needs to be increment or decrement
	 */
	public static boolean check(int num1, int num2, int num3)
	{
		if(num1>num3)
		{
			if(num1+1>=num2)
				return false;
			else 
				return true;
		}
		else if(num1<=num3)
		{
			if(num1-1<0)
			{
				return true;
			}
			else
				return false;
		}
		return true;
	}
	
	/**
	 * Flip function return the position of the bit that needs to be flipped
	 * @param num number of iteration
	 * @param k base of graycode that was given
	 * @param n length of graycode that was given
	 * @return return which bit is going to be flipped compare to the previous one
	 */
	public static int flip(int num,int k,int n)
	{
		for(int i = n; i>=0; i--)
		{
			if ((num)%(Math.pow(k, i))==0)
			{
				return i;
			}
		}
		return 0;
	}
}

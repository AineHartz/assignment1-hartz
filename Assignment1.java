/**
 * 
 */
/**
 * @author ryanh
 *
 */
public class Assignment1 
{

	public static void main(String args[])
	{
		Assignment1 solver = new Assignment1();
		
		//Question 1 examples.
	//	solver.subsequence("abc", "abc");
	//	solver.subsequence("almanacs", "albatross");
	//	solver.subsequence("almanac", "ferris");
		
		//Question 2 examples.
	//	solver.substring("spy family", "jujutzu");
		solver.substring("gears of war", "History of warriors");
	//	solver.substring("spy family", "jujutsu kaisen");
		
		//Question 3 example. 
	//	solver.notfib(10);
		
		//Question 4 example. 
	//	solver.whereinsequence(5);
		
		//Question 5 example. I forgot how to put an array into the method's inputs, so I just declared the array the line before. 
	//	int[] nums = new int[] {0, 1, 2, 2, 3, 0, 4, 2};
	//	solver.removeElement(nums, 2);

		
		
	}
	
	/*
	 * The code for question 1 of the assignment.
	 */
	public int subsequence(String text1, String text2)
	{
		//Convert to array to make the strings easy to iterate through.
		char[] text1array = text1.toCharArray();
		char[] text2array = text2.toCharArray();
		String result = "";
		
		//My for loop breaks if text1array is longer, so this makes sure it's not.
		if(text2array.length < text1array.length)
		{
			text1array = text2.toCharArray();
			text2array = text1.toCharArray();
		}
		
		//Iterate through the same element of each array, and add them to the result
		//string if they're the same.
		for(int index = 0; index < (text1array.length); index++)
		{
			if(text1array[index] == text2array[index])
			{
				result = result + text1array[index];
			}
		}
		
		//Either print result if there is one, or print a no results statement if not.
		if(result.length() != 0)
		{
			System.out.println("The longest common subsequence is " + result + " and its length is " + result.length() + ".");
			return result.length();
		}
		
		else
		{
			System.out.println("There is no common subsequence, so the result is 0.");
			return 0;
		}
	}


	/*Issues: While loop not stopping when it's not subsequent.
	**Do I really need to iterate through everything like this? 
	**This code isn't quite working, but I'm short on time, so I'm answering with text instead. 
	*/
	
	 public String substring(String text1, String text2)
	{
		//Convert to array to make the strings easy to iterate through.
		char[] text1array = text1.toCharArray();
		char[] text2array = text2.toCharArray();
		String tempresult = "";
		String finalresult = "";
		
		boolean start = false;
		int loopvar = 0;
		
		//Iterate through the same element of each array, and add them to the result
		//string if they're the same.
		for(int index = 0; index < (text1array.length); index++)
		{
			for(int iterator = 0; iterator < (text2array.length); iterator++)
			{
				if(text1array[index] == text2array[iterator])
				{
					start = true;
					loopvar = index;
				}
			
				while(start == true)
				{
					tempresult = tempresult + text1array[loopvar];
					loopvar++;
				
					if(loopvar+iterator > (text2array.length-1) || loopvar > (text1array.length-1) || text1array[loopvar] == text2array[loopvar+iterator])
					{
						start = false;
						loopvar = 0;
					}
				}
			
				if(tempresult.length() > finalresult.length())
				{
					finalresult = tempresult;
					tempresult = "";
				}
			}
		}
		
		if(finalresult == "")
		{
			System.out.println("No substrings found.");
			return "";
		}
		else 
		{
			System.out.println(finalresult);
			return finalresult;
		}
	}
	
	
	//Question 3. Made it return an array for use in question 4. 
	public long[] notfib(int termcount)
	{
		long first_term = 0;
		long second_term = 1;
		long new_term = 0;
		String answer_string = "0, 1, ";
		long[] answer_array = new long[termcount];
		answer_array[0] = first_term;
		answer_array[1] = second_term;
		
		//index starts at 2 because the first two terms are given
		for(int index = 2; index < termcount; index++)
		{
			
			//I feel fairly sure I've implemented this correctly, but the assignment has two different formulas and an example output and all
			// of them seem to be different. I've left the written formula commented out, and I used the direct formula you provided. Apologies if I'm
			// misunderstanding something here.
			
			//new_term = ((second_term * 2) + first_term);
			new_term = (3 * second_term) + (2 * first_term);
			
			//This if/else statement is wholly unnecessary and just makes the output string prettier.
			if(index == (termcount - 1))
			{
				answer_string += (new_term + ".");
				answer_array[index] = new_term;
			}
			
			else
			{
				answer_string += (new_term +  ", ");
				answer_array[index] = new_term;
			}
			
			first_term = second_term;
			second_term = new_term;
		}
		System.out.println(answer_string);
		return(answer_array);
	}
	
	//Question 4.
	public void whereinsequence(int num)
	{
		boolean found = false;
		int array_size = 0;
		
		// This while loop generates a notfib array, checks if the given number is in that array, and makes it bigger, if not. This function increases quickly,
		// so I figured this was the best way to save time while still allowing for someone inputting a ludicrously large number to find the position of. 
		while(found == false)
		{
			int check_val = 10;
			long[] check_array = notfib(check_val);
			
			if(num < check_array[(check_val - 1)])
			{
				array_size = check_val;
				found = true;
			}
			
			else
			{
				check_val += 5;
			}
		}
		
		found = false;
		long[] fib_array = notfib(array_size);
		
		
		for(int index = array_size - 1; index >= 0 && found == false; index -= 1)
		{
			if(fib_array[index] == num)
			{
				System.out.println("Posistion: " + (index));
				found = true;
			}
			
			else if(fib_array[index - 1] < num)
			{
				System.out.println("Posistion: " + (index));
				found = true;
			}
			
		}
	}

	//Question 5.
	public int removeElement(int[] nums, int val) 
	{
		
		//This for loops sets every target value to a throwaway value, 1000. 
		for(int index = 0; index < (nums.length); index++)
        {
            if(nums[index] == val)
            {
            	nums[index] = 1000;
            }
        }
        
        int k = 0;
        
      //This for loop just finds k. 
        for(int index = 0; index < (nums.length); index++)
        {
            if(nums[index] != 1000)
            {
            	k++;
            }
        }
        
        //This for loop finds every instance of 1000 and swaps it with the latest number in the array that isn't 1000. 
        for(int index = 0; index < (nums.length); index++)
        {
            if(nums[index] == 1000 && index < k)
            {
            	boolean found = false;
            	int j = 1;
            	while( found == false)
            	{
            		if(nums[nums.length - j] != 1000)
                	{
                		nums[index] = nums[nums.length - j];
                		nums[nums.length - j] = 1000;
                		found = true;
                	}
            		
            		else
            		{
            			j++;
            		}
            	}
            }
        }
        
        return k;
	}
}


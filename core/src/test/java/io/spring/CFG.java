/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.spring;

// Java program to print a given number in words. The
// program handles numbers from 0 to 9999

class GFG
{
	// A function that prints
// given number in words
	static String convert_to_words(char[] num)
	{
		// Get number of digits
		// in given number
		int len = num.length;

		// Base cases
		if (len == 0)
		{
			return "empty string";
		}
		if (len > 4)
		{
			return "Length more than 4 is not supported";
		}

	/* The first string is not used, it is to make
		array indexing simple */
		String[] single_digits = new String[]{ "zero", "one",
				"two", "three", "four",
				"five", "six", "seven",
				"eight", "nine"};

	/* The first string is not used, it is to make
		array indexing simple */
		String[] two_digits = new String[]{"", "ten", "eleven", "twelve",
				"thirteen", "fourteen",
				"fifteen", "sixteen", "seventeen",
				"eighteen", "nineteen"};

		/* The first two string are not used, they are to make array indexing simple*/
		String[] tens_multiple = new String[]{"", "", "twenty", "thirty", "forty",
				"fifty","sixty", "seventy",
				"eighty", "ninety"};

		String[] tens_power = new String[] {"hundred", "thousand"};

		/* Used for debugging purpose only */
//		System.out.print(String.valueOf(num)+": ");

		/* For single digit number */
		if (len == 1)
		{
			return single_digits[num[0] - '0'];
		}

	/* Iterate while num
		is not '\0' */
		int x = 0;

		StringBuilder result = new StringBuilder();
		while (x < num.length)
		{

			/* Code path for first 2 digits */
			if (len >= 3)
			{
				if (num[x]-'0' != 0)
				{
					result.append(single_digits[num[x] - '0']+" ");
					result.append(tens_power[len - 3]+" ");
					// here len can be 3 or 4
				}
				--len;
			}

			/* Code path for last 2 digits */
			else
			{
			/* Need to explicitly handle
			10-19. Sum of the two digits
			is used as index of "two_digits"
			array of strings */
				if (num[x] - '0' == 1)
				{
					int sum = num[x] - '0' +
							num[x+1] - '0';
					result.append(two_digits[sum]);
					break;
				}

				/* Need to explicitely handle 20 */
				else if (num[x] - '0' == 2 &&
						num[x + 1] - '0' == 0)
				{
					return "twenty";
				}

			/* Rest of the two digit
			numbers i.e., 21 to 99 */
				else
				{
					int i = (num[x] - '0');
					if(i > 0)
						result.append(tens_multiple[i]+" ");
					x++;
					if (num[x] - '0' != 0)
						result.append(single_digits[num[x] - '0']);
				}
			}
			++x;
		}

		return result.toString();
	}

	// Driver Code
	public static void main(String[] args)
	{

		for(int i = 1; i < 1001; i = i + 3) {
			int first = i;
			int second = first+1;
			int third = second+1;
			System.out.println(String.format("%s,%s,%s", convert_to_words(String.valueOf(first).toCharArray()), convert_to_words(String.valueOf(second).toCharArray()), third));
		}
//		System.out.println(convert_to_words("10".toCharArray()));
//		System.out.println(convert_to_words("11".toCharArray()));
//		System.out.println(convert_to_words("12".toCharArray()));
//		System.out.println(convert_to_words("13".toCharArray()));
//		System.out.println(convert_to_words("14".toCharArray()));
//		System.out.println(convert_to_words("15".toCharArray()));
//		System.out.println(convert_to_words("16".toCharArray()));
//		System.out.println(convert_to_words("17".toCharArray()));
//		System.out.println(convert_to_words("18".toCharArray()));
//		System.out.println(convert_to_words("19".toCharArray()));
//		System.out.println(convert_to_words("20".toCharArray()));
//		System.out.println(convert_to_words("9923".toCharArray()));
//		System.out.println(convert_to_words("523".toCharArray()));
//		System.out.println(convert_to_words("89".toCharArray()));
//		System.out.println(convert_to_words("8989".toCharArray()));
	}
}
// This code is contributed
// by Mithun Kumar

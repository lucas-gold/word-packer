package Assign34;

import java.util.*;

public class WordPacking {


	public static String getAuthorName()
	{
		return "Gold, Lucas";

	}


	public static String getRyersonID()
	{
		return "500686031";
	}


	public static List<List<String>> conflictFinder(List<String> words)
	{
		List<List<String>> conflictArrays = new ArrayList<List<String>>();

		for (int i = 0; i < words.size(); i++)
		{
			ArrayList<String> wordConflict = new ArrayList<String>();
			wordConflict.add(words.get(i)); //index 0 of array is the original word
			//words that follow interact with the original word

			for (int j = 0; j < words.size(); j++)
			{
				char mainOne = words.get(i).charAt(0);
				char mainTwo = words.get(i).charAt(1);
				char mainThree = words.get(i).charAt(2);
				char mainFour = words.get(i).charAt(3);
				char mainFive = words.get(i).charAt(4);


				char one = words.get(j).charAt(0);
				char two = words.get(j).charAt(1);
				char three = words.get(j).charAt(2);
				char four = words.get(j).charAt(3);
				char five = words.get(j).charAt(4);

				if (!words.get(i).equals(words.get(j))) //ignore itself in list
				{
					//checks for character match between two words
					if (mainOne == one || mainTwo == two || mainThree == three || mainFour == four || mainFive == five)
					{
						wordConflict.add(words.get(j)); //add to list of conflicting words
					}
				}

			}

			conflictArrays.add(wordConflict);
		}

		return conflictArrays;
	}


	public static List<List<String>> sortByMax (List<List<String>> conflictArrays)
	{
		//sorts by descending order of number of interactions with other words
		Collections.sort(conflictArrays, listLengthComparator);

		return conflictArrays;
	}

	public static List<List<String>> sortByRandom (List<List<String>> conflictArrays)
	{
		Collections.shuffle(conflictArrays);

		return conflictArrays;
	}

	public static boolean checkContains(List<List<String>> wordBuilder, String word)
	{

		for (int i = 0; i < wordBuilder.size(); i++)
		{
			if (wordBuilder.get(i).contains(word))
			{
				return true;
			}
		}
		return false;
	}


	public static List<List<String>> wordPackingByMax(List<String> words) 
	{
		List<List<String>> bin = new ArrayList<List<String>>(); 
		List<String> tmpBin = new ArrayList<String>(); 
		List<List<String>> nextBins = new ArrayList<List<String>>(); 

		//Assign conflict values to each word and store in array
		List<List<String>> wordList = new ArrayList<List<String>>(); 
		wordList = conflictFinder(words);
		wordList = sortByMax(wordList); //pick words with greatest number of interactions first

		//add word to tmpBin and array to wordBuilder
		List<List<String>> wordBuilder = new ArrayList<List<String>>(); 

		boolean present;
		boolean endLoop = false;

		while (endLoop == false) {
			
			wordBuilder.add(wordList.get(0));
			tmpBin.add(wordList.get(0).get(0));
			
			for (int i = 1; i < wordList.size(); i++) 
			{
				present = checkContains(wordBuilder, wordList.get(i).get(0));

				if (present == true)
				{
					nextBins.add(wordList.get(i));
				}
				else if (present == false){
					wordBuilder.add(wordList.get(i));
					tmpBin.add(wordList.get(i).get(0));
				}
			}
			

			if (nextBins.size() != 0)
			{

				bin.add(tmpBin);
				wordList = nextBins;
				tmpBin = new ArrayList<String>();
				nextBins = new ArrayList<List<String>>();
				wordBuilder = new ArrayList<List<String>>();

			}
			else {
				bin.add(tmpBin);
				endLoop = true;
			}

		}

		return bin;
	}


	public static List<List<String>> wordPacking(List<String> words)
	{
		List<List<String>> bin = new ArrayList<List<String>>(); 
		List<String> tmpBin = new ArrayList<String>(); 
		List<List<String>> nextBins = new ArrayList<List<String>>(); 

		//Assign conflict values to each word and store in array
		List<List<String>> wordList = new ArrayList<List<String>>(); 
		wordList = conflictFinder(words);
		wordList = sortByRandom(wordList); //shuffle word order

		//add word to tmpBin and array to wordBuilder
		List<List<String>> wordBuilder = new ArrayList<List<String>>(); 

		boolean present;
		boolean endLoop = false;

		while (endLoop == false) {
			
			wordBuilder.add(wordList.get(0));
			tmpBin.add(wordList.get(0).get(0));
			
			for (int i = 1; i < wordList.size(); i++) 
			{		
				present = checkContains(wordBuilder, wordList.get(i).get(0));

				if (present == true)
				{
					nextBins.add(wordList.get(i));
				}
				else if (present == false){
					wordBuilder.add(wordList.get(i));
					tmpBin.add(wordList.get(i).get(0));
				}
			}

			
			if (nextBins.size() != 0)
			{
				bin.add(tmpBin);
				wordList = nextBins;
				tmpBin = new ArrayList<String>();
				nextBins = new ArrayList<List<String>>();
				wordBuilder = new ArrayList<List<String>>();

			}
			else {
				bin.add(tmpBin);
				endLoop = true;
			}

		}

		return bin;
	}

	
	public static List<List<String>> wordPack(List<String> words)
	{
		List<List<String>> bin = new ArrayList<List<String>>(); 
		List<List<String>> minBin = new ArrayList<List<String>>(); 
		minBin = wordPackingByMax(words); 
		for (int i = 0; i < 2500; i++) //loop any number of times, new random solution each time
		{
			bin = wordPacking(words); //find bin solution with randomly sorted array
			if (bin.size() < minBin.size()) 
			{
				minBin = bin; 
			}
		}

		return minBin; 

	}




	static Comparator<List<String>> listLengthComparator = new Comparator<List<String>>()
	{
		@Override
		public int compare(List<String> o1, List<String> o2)
		{
			return Integer.compare(o2.size(), o1.size());
		}
	};


}





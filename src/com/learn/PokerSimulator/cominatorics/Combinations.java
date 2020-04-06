package com.learn.PokerSimulator.cominatorics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.learn.PokerSimulator.utils.Logger;

public class Combinations {
	
	/**
	 * Returns all possible combinations
	 * For simplicity, we keep our sets in lists. We want to have fast access to elements specified by index, which we can achieve it with List, but not with Set.
	 * 
	 * @param  inputSet  The input set to generate the combinations from (must contain unique elements)
	 * @param  k  		 The number of elements required in the combination
	 * @param  results	 The results (all combinations)
	 * @param  accumulator 
	 * @param  index 	 Index of the element currently being processed 
	 * 
	 */	
	private static void combinationsInternal(
			List inputSet, int k, List results, List accumulator, int index) {
		
			// We start by defining helper variables:

			// needToAccumulate – indicates how many more elements we need to add to our accumulator to get a proper combination
			// canAcculumate – indicates how many more elements we can add to our accumulator

	
	        int needToAccumulate = k - accumulator.size();
			int canAcculumate = inputSet.size() - index;
			 
			//Now, we check if our accumulator size is equal to k. If so, then we can put the copied array into the results list.			
			if (accumulator.size() == k) {
				results.add(new ArrayList<>(accumulator));
				
			//In another case, if we still have enough elements in the remaining part of the set, we make two separate recursive calls: with and without the currently processed element being put into the accumulator. This part is analogous to how we generated the powerset earlier.	
			} else if (needToAccumulate <= canAcculumate) {
				combinationsInternal(inputSet, k, results, accumulator, index + 1);
			    accumulator.add(inputSet.get(index));
			    combinationsInternal(inputSet, k, results, accumulator, index + 1);
			    accumulator.remove(accumulator.size() - 1);
			}

			//Of course, this method could've been written to work a little bit faster. For example, we could declare needToAccumulate and canAcculumate variables later. However, we are focused on readability.					
	}	
	

	/**
	 * Returns all possible combinations
	 * For simplicity, we keep our sets in lists. We want to have fast access to elements specified by index, which we can achieve it with List, but not with Set.
	 * 
	 * @param  inputSet  The input set to generate the combinations from (must contain unique elements)
	 * @param  k  		 The number of elements required in the combination
	 * @return			 The combinations
	 * 
	 */		
	public static List<List<Integer>> combinations(List<Integer> inputSet, int k) {
		

		
	    List<List<Integer>> results = new ArrayList<>();
	    combinationsInternal(inputSet, k, results, new ArrayList<>(), 0);
	    

	    return results;
	}	
	
	
	public static void  main(String[] args) {
		
		double time = System.nanoTime();
		
		List<Integer> cards = new ArrayList<Integer>();
		
		for (int i = 1; i < 8; i++) {
			if(i != 3 && i !=9) {
				cards.add(i);
			}
		}
		
		List<List<Integer>> combinations = combinations(cards, 4);
		
		Logger.debug("Combinations = " + combinations.size() );
		
		double rtime = (System.nanoTime() - time)/1e9; // time given is start time
		
		Logger.debug("time " + rtime);
		
		Iterator<List<Integer>> iter = combinations.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	
}

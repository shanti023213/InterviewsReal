package com.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class NestedIntegers{
	Object data;
	public NestedIntegers(Integer value){
		this.data =value;
	}
	
	public NestedIntegers(List<NestedIntegers> list){
		this.data = list;
	}
}

public class SumOfNumbersDepthFinal {

	public static void main(String[] args) {
		

		NestedIntegers n1 = new NestedIntegers(1);
		NestedIntegers n2 = new NestedIntegers(1);
		
		List<NestedIntegers> firstList = new ArrayList<NestedIntegers>();
		firstList.add(n1);
		firstList.add(n2);
		NestedIntegers list1 = new NestedIntegers(firstList);
		
		NestedIntegers list2 = new NestedIntegers(2);
		
		NestedIntegers p1 = new NestedIntegers(1);
		NestedIntegers p2 = new NestedIntegers(1);
		List<NestedIntegers> secondList = new ArrayList<NestedIntegers>();
		secondList.add(p1);
		secondList.add(p2);
		
		NestedIntegers list3 = new NestedIntegers(secondList);
		
		List<NestedIntegers> list = new ArrayList<NestedIntegers>();
		list.add(list1);
		list.add(list2);
		list.add(list3);
		
		//NestedIntegers total = new NestedIntegers(list);
		
		int result = nestedListWeightSum(list);
		System.out.println(result);
		
		
		
		NestedIntegers a1 = new NestedIntegers(1);
		NestedIntegers a2 = new NestedIntegers(4);
		NestedIntegers a3 = new NestedIntegers(6);
		List<NestedIntegers> a3List = new ArrayList<NestedIntegers>();
		a3List.add(a3);
		
		NestedIntegers a3Final = new NestedIntegers(a3List);
		
		List<NestedIntegers> a2List = new ArrayList<NestedIntegers>();
		a2List.add(a2);
		a2List.add(a3Final);
		
		NestedIntegers a2Final = new NestedIntegers(a2List);
		
		List<NestedIntegers> a1List = new ArrayList<NestedIntegers>();
		a1List.add(a1);
		a1List.add(a2Final);
		
		//NestedIntegers a1Final = new NestedIntegers(a1List);
		
		
		int result1 = nestedListWeightSum(a1List);
		System.out.println(result1);
	}
	
	
	public static int nestedListWeightSum(List<NestedIntegers> list){
		
		Stack<NestedIntegers> elementStack =  new Stack<NestedIntegers>();
		Stack<Integer> depthStack =  new Stack<Integer>();
		
		Map<Integer,ArrayList<Integer>> map = new HashMap<Integer,ArrayList<Integer>>();
		
		for(NestedIntegers p: list){
			elementStack.push(p);
			depthStack.push(1);
			
		}
		
		int maxDepth = Integer.MIN_VALUE;
		
		while(!elementStack.isEmpty()){
			
			NestedIntegers q = elementStack.pop();
			Integer depth = depthStack.pop();
			maxDepth = Math.max(maxDepth,depth );
			if(q.data instanceof Integer){
				if(map.containsKey(depth)){
					ArrayList<Integer> mapList = map.get(depth);
					mapList.add((int)q.data);
				}else{
					ArrayList<Integer> mapList = new ArrayList<Integer>();
					mapList.add((int)q.data);
					map.put(depth,mapList);
				}
			}else{
				
				ArrayList<NestedIntegers> m = (ArrayList<NestedIntegers>) q.data;
				for(NestedIntegers n: m){
					elementStack.push(n);
					depthStack.push(depth+1);
				}
			}
		}
		
		int totalSum =0;
		
		for(Integer key: map.keySet()){
			ArrayList<Integer> mapValues = map.get(key);
			int sum =0;
			for(Integer n :mapValues){
				sum+=n;
			}
			totalSum+=(sum*(maxDepth-key+1));
		}
		
		return totalSum;
	}

}

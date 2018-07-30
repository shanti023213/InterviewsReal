package com.linkedin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class DepSum {
	public class NestedInteger{
		public Object value;
		public NestedInteger(List<NestedInteger> value){
			this.value = value;
		}
		public NestedInteger(Integer value){
			this.value= value;
		}
	}
	public class Dep{
		int dep;
		public Dep(int dep){
			this.dep = dep;
		}
	}
	
	public DepSum(){
		List<NestedInteger> firstList = new ArrayList<>();
		firstList.add(new NestedInteger(1));
		firstList.add(new NestedInteger(1));
		NestedInteger n = new NestedInteger(firstList);
		
		List<NestedInteger> firstList1 = new ArrayList<>();
		firstList1.add(new NestedInteger(1));
		firstList1.add(new NestedInteger(1));
		NestedInteger n1 = new NestedInteger(firstList1);
		
		NestedInteger n2 = new NestedInteger(new Integer(2));
		
		List<NestedInteger> listGlobal1 = new ArrayList<>();
		listGlobal1.add(n);
		listGlobal1.add(n2);
		listGlobal1.add(n1);
		
		System.out.println(reverseDepSum(listGlobal1));
		
		List<NestedInteger> list = new ArrayList<>();
		NestedInteger ni6 = new NestedInteger(6);
		list.add(ni6);
		NestedInteger nestedIntegerList = new NestedInteger(list);
		
		NestedInteger ni4 = new NestedInteger(4);
		List<NestedInteger> list1 = new ArrayList<>();
		list1.add(ni4);
		list1.add(nestedIntegerList);
		NestedInteger nestedIntegerList1 = new NestedInteger(list1);
		
		NestedInteger ni1 = new NestedInteger(1);
		List<NestedInteger> listGlobal = new ArrayList<>();
		listGlobal.add(ni1);
		listGlobal.add(nestedIntegerList1);
		
		System.out.println(reverseDepSum(listGlobal));
	}
	
	private int reverseDepSum(List<NestedInteger> list){
		Dep dep = new Dep(1);
		return getSum(list,dep);
	}
	
	private int getSum(List<NestedInteger> list, Dep dep){

		if(list==null || list.size()==0) return 0;
		int sum = 0;
		int listSum=0;
		int currDep = dep.dep;
		for(NestedInteger ni : list){
			if(ni.value instanceof Integer){
				sum += (int)ni.value;
			}else{
				Dep nextDep = new Dep(dep.dep);
				listSum += getSum((List)ni.value,nextDep);
				currDep = Math.max(currDep, nextDep.dep);
			}
		}
		dep.dep = currDep+1;
		return currDep*sum+listSum;}
	
	public static void main(String[] args) {
		DepSum a = new DepSum();
	}
	
}

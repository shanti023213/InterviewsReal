package com.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class ReversedDepthSum {

	interface NestedInteger {
		boolean isInteger();
	}

	static class NestedIntegerValue implements NestedInteger {
		Integer value;

		public NestedIntegerValue(Integer value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value.toString();
		}

		@Override
		public boolean isInteger() {
			return true;
		}
	}

	static class NestedIntegerValues implements NestedInteger {
		List<NestedInteger> values;

		public NestedIntegerValues(NestedInteger... values) {
			this.values = Arrays.asList(values);
		}

		@Override
		public String toString() {
			StringBuffer buff = new StringBuffer();
			buff.append('{');
			values.forEach(v -> {
				buff.append(v.toString());
				buff.append(',');
			});
			buff.setCharAt(buff.length() - 1, '}');
			return buff.toString();
		}

		@Override
		public boolean isInteger() {
			return false;
		}
	}

	public static int getMaxDepth(List<NestedInteger> input, int depth) {
		int maxDepth = depth;
		for (NestedInteger value : input) {
			if (!value.isInteger()) {
				maxDepth = getMaxDepth(NestedIntegerValues.class.cast(value).values, ++depth);
				if (maxDepth < depth) {
					return depth;
				}
				depth--;
			}
		}
		return maxDepth;
	}

	public static int reverseDepthSum(List<NestedInteger> input, int depth, int maxDepth) {
		int sum = 0;
		for (NestedInteger value : input) {
			if (value.isInteger()) {
				NestedIntegerValue nestedInteger = NestedIntegerValue.class.cast(value);
				sum += nestedInteger.value * (maxDepth - depth + 1);
			} else {
				sum += reverseDepthSum(NestedIntegerValues.class.cast(value).values, ++depth, maxDepth);
				--depth;
			}
		}
		return sum;
	}

	public static int reverseDepthSum(List<NestedInteger> input) {
		int maxDepth = getMaxDepth(input, 1);
		return reverseDepthSum(input, 1, maxDepth);
	}

	// {{1,1},2,{1,1}}
	private static List<NestedInteger> getSample1() {
		return Arrays.asList(new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)),
				new NestedIntegerValue(2),
				new NestedIntegerValues(new NestedIntegerValue(1), new NestedIntegerValue(1)));
	}

	private static List<NestedInteger> getSample2() {
		return Arrays.asList(new NestedIntegerValue(1),
				new NestedIntegerValues(new NestedIntegerValue(4), new NestedIntegerValues(new NestedIntegerValue(6))));
	}

	public static void main(String[] args) {
		System.out.println(getSample1() + " -> " + reverseDepthSum(getSample1()));
		System.out.println(getSample2() + " -> " + reverseDepthSum(getSample2()));
	}

}
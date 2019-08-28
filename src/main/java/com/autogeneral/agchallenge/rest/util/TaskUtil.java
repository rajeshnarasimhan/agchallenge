package com.autogeneral.agchallenge.rest.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Utility class to check if a string is balanced
 * @author rajesh
 */
public class TaskUtil {

	/**
	 * method to check if a string is balanced or not
	 * @param input string
	 * @return boolean - true if the string is balanced, otherwise false
	 */
	public static boolean isValid(String s) {
        Map<Character, Character> symbolMap = new HashMap<>();
        symbolMap.put('(', ')');
        symbolMap.put('[', ']');
        symbolMap.put('{', '}');
        Stack<Character> currentStack = new Stack<>();
        for(char currentChar : s.toCharArray()){
        	if (currentChar == '{' || currentChar == '(' || currentChar == '['
        			|| currentChar == '}' || currentChar == ')' || currentChar == ']'){
        		if(symbolMap.containsKey(currentChar)){
                	currentStack.push(currentChar);
                } else if(!currentStack.empty() && symbolMap.get(currentStack.peek())==currentChar){
                	currentStack.pop();
                }
        	}
        }
        return currentStack.empty();
    }
}
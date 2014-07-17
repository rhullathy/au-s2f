package com.vw.formhandler.common;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import com.vw.formhandler.webspring.mvc.FormHandlerParamsEnumsInterface;

public class CommonUtils
{
	public static final Short defaultShortZero = new Short((short) 0); 
	public static final Integer defaultIntZero = new Integer(0); 
    private static SimpleDateFormat validDateFormat = new SimpleDateFormat(FormHandlerConstants.validDateFormatStr);

	/**
	 * @author Kunal Bhatia
	 * 
	 * @param input
	 * @param defaultVal
	 * @return
	 */
	public static boolean isNullOrDefaultVal(Short input, Short defaultVal)
	{
		if(input==null || input.equals(defaultVal))
			return true;
		
		return false;
	}
	public static boolean isNullOrDefaultVal(Integer input, Integer defaultVal)
	{
		if(input==null || input.equals(defaultVal))
			return true;
		
		return false;
	}

	public static boolean isNullOrBlank(String str)
	{
		if(str==null || str.trim().length()<1)
			return true;
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean isNullOrEmpty(Collection col)
	{
		if(col==null || col.isEmpty())
			return true;
		
		return false;
	}
	
	public static boolean isNullOrEmpty(Map<?, ?> map)
	{
		if(map==null || map.isEmpty())
			return true;
		
		return false;
	}


	public static boolean isNullOrEmpty(Object[] strings) {
		if(strings==null || strings.length <1)
			return true;
		
		return false;
	}
	
	public static boolean isCorrectDateFormat(SimpleDateFormat sdf, String dateStr)
	{
	    sdf.setLenient(false);
	    return ((dateStr.length()==sdf.toPattern().length()) && (sdf.parse(dateStr, new ParsePosition(0)) != null));
	}
	
	public static boolean isStringABoolean(String input)
	{
		return 
		(	Boolean.TRUE.toString().equals(input) 
			|| Boolean.FALSE.toString().equals(input));
	}
	
	public static boolean enumContains(FormHandlerParamsEnumsInterface[] enumValues, String input)
	{
		for (FormHandlerParamsEnumsInterface currEnumClassValue : enumValues)
			if (currEnumClassValue.toString().equals(input))
				return true;
		return false;
	}

	public static FormHandlerParamsEnumsInterface getEnumConstant(FormHandlerParamsEnumsInterface[] enumValues, String input)
	{
		for (FormHandlerParamsEnumsInterface currEnumClassValue : enumValues)
			if (currEnumClassValue.toString().equals(input))
				return currEnumClassValue;
		return null;
	}

	public static SimpleDateFormat getValidDateFormat()
	{
		return CommonUtils.validDateFormat;
	}
}

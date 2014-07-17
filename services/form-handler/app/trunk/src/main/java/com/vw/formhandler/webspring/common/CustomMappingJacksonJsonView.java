package com.vw.formhandler.webspring.common;

import java.util.Map;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import com.vw.formhandler.webspring.mvc.response.Errors;

/**
 * This class will make sure that if there is a single object to
 * transform to JSON, it won't be rendered inside a map.
 */
public class CustomMappingJacksonJsonView extends MappingJacksonJsonView 
{
	@SuppressWarnings("unchecked")
	@Override
	protected Object filterModel(Map<String, Object> model)
	{
		Object result = super.filterModel(model);
		if (!(result instanceof Map))
			return result;

		Map map = (Map) result;
		if (map.size() == 1)
		{
			Object resultTopObj = map.values().toArray()[0];
			if (resultTopObj instanceof Errors)
				return map.values().toArray()[0];
		}
		
	 	return map;
	}
}

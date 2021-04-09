package com.vilin.util;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.util.*;

public class BeanUtil {

    private BeanUtil(){

    }

    public static final Map toMap(Object bean){
        return toMap(bean, new String[]{
                "class"
        });
    }

    public static final Map toMap(Object bean, String ignoreProperties[]){
        return toMap(bean, ((Set) (new HashSet())), false, ignoreProperties);
    }

    public static final Map toMap(Object object, Set convertContext, boolean flat, String ignoreProperties[]){
        return toMap(null, object, convertContext, flat, ignoreProperties);
    }

    private static final Map toMap(String key, Object object, Set convertContext, boolean flat, String ignoreProperties[]){
        if(object == null){
            return null;
        }
        if(convertContext.contains(object)){
            return null;
        }
        Class type = object.getClass();
        if(isSimpleValueType(type)){
            throw new RuntimeException((new StringBuilder("Simple type[")).append(type.getName()).append("] cab not to map!").toString());
        }
        convertContext.add(object);
        Map desc = new HashMap();
        PropertyDescriptor pds[] = BeanUtils.getPropertyDescriptors(type);
        List ignoreList = ignoreProperties == null ? null : Arrays.asList(ignoreProperties);
        PropertyDescriptor aPropertyDescriptor[];
        int j = (aPropertyDescriptor = pds).length;
        for(int i = 0; i < j; i++){
            PropertyDescriptor pd = aPropertyDescriptor[i];
            if(ignoreProperties == null || !ignoreList.contains(pd.getName())){
                Method readMethod = pd.getReadMethod();
                if(readMethod != null){
                    desc.put(pd.getName(), ReflectionUtils.invokeMethod(readMethod, object));
                }
            }
        }

        Map resultMap = new HashMap();
        for(Iterator iterator = desc.keySet().iterator(); iterator.hasNext();){
            String innerKey = (String)iterator.next();
            Object value = desc.get(innerKey);
            if(flat){
                String subKey = key != null ? (new StringBuilder(String.valueOf(key))).append(".").append(innerKey).toString() : innerKey;
                Map flatMap = descFlat(subKey, value, convertContext, ignoreProperties);
                if(flatMap != null){
                    resultMap.putAll(flatMap);
                }
            }else{
                resultMap.put(innerKey, desc(value, convertContext, ignoreProperties));
            }
        }
        return resultMap;
    }

    public static boolean isSimpleValueType(Class clazz){
        return ClassUtils.isPrimitiveOrWrapper(clazz) || clazz.isEnum() || CharSequence.class.isAssignableFrom(clazz) || Number.class.isAssignableFrom(clazz) || Date.class.isAssignableFrom(clazz) || clazz.equals(URI.class) || clazz.equals(URL.class) || clazz.equals(Locale.class) || clazz.equals(Class.class);
    }

    private static Map descFlat(String key, Object object, Set context, String ignoreProperties[]){
        Map result = new HashMap();
        if(object == null || context.contains(object)){
            result.put(key, null);
            return result;
        }
        Class type = object.getClass();
        if(isSimpleValueType(type) || type.isArray()){
            result.put(key, object);
        }
        if(object instanceof Collection){
            Collection col = (Collection)object;
            List resultList = new ArrayList(col.size());
            Object value;
            for(Iterator iterator = col.iterator(); iterator.hasNext(); resultList.add(value)){
                Object o = iterator.next();
                value = descFlat(null, o, context, ignoreProperties);
            }

            result.put(key, resultList);
            return result;
        }
        if(object instanceof Map){
            Map mapValue = (Map)object;
            Map mapResult = new HashMap();
            Map nestedDesc;
            for(Iterator iterator = mapValue.keySet().iterator(); iterator.hasNext(); mapResult.putAll(nestedDesc)){
                Object innerKey = iterator.next();
                Object value = mapValue.get(innerKey);
                String nextKey = (new StringBuilder(String.valueOf(key))).append(".").append(innerKey).toString();
                nestedDesc = descFlat(nextKey, value, context, ignoreProperties);
            }

            return mapResult;
        }else{
            return toMap(key, object, context, true, ignoreProperties);
        }
    }

    private static Object desc(Object object, Set context, String ignoreProperties[]){
        if(object == null || context.contains(object))
            return null;
        Class type = object.getClass();
        if(isSimpleValueType(type) || type.isArray())
            return object;
        if(object instanceof Collection){
            Collection col = (Collection)object;
            List resultList = new ArrayList(col.size());
            Object value;
            for(Iterator iterator = col.iterator(); iterator.hasNext(); resultList.add(value)){
                Object o = iterator.next();
                value = desc(o, context, ignoreProperties);
            }
            return resultList;
        }
        if(object instanceof Map){
            Map mapValue = (Map)object;
            Map mapResult = new HashMap();
            Object key;
            Object value;
            for(Iterator iterator = mapValue.keySet().iterator(); iterator.hasNext(); mapResult.put(key.toString(), desc(value, context, ignoreProperties))){
                key = iterator.next();
                value = mapValue.get(key);
            }
            return mapResult;
        }else {
            return toMap(object, context, false, ignoreProperties);
        }
    }
}

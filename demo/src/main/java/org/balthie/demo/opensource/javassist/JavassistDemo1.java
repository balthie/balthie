package org.balthie.demo.opensource.javassist;

import org.balthie.demo.my.json.jackson2.list.ListJsonDemo1;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class JavassistDemo1
{
    public static void main(String[] args) {  
        Class clazz = ListJsonDemo1.class;  
        try {  
            ClassPool pool = ClassPool.getDefault();  
            CtClass cc = pool.get(clazz.getName());  
            CtMethod cm = cc.getDeclaredMethod("jsonToStringList");  
      
          
            MethodInfo methodInfo = cm.getMethodInfo();  
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();  
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);  
            if (attr == null) {  
                // exception  
            }  
            String[] paramNames = new String[cm.getParameterTypes().length];  
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;  
            for (int i = 0; i < paramNames.length; i++)  
            {
                paramNames[i] = attr.variableName(i + pos);  
            }
        
            for (int i = 0; i < paramNames.length; i++) {  
                System.out.println(paramNames[i]);  
            }  
      
        } catch (NotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
}

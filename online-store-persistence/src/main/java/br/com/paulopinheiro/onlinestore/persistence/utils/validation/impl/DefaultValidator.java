package br.com.paulopinheiro.onlinestore.persistence.utils.validation.impl;

import br.com.paulopinheiro.onlinestore.persistence.onlinestore.annotations.Validate;
import br.com.paulopinheiro.onlinestore.persistence.utils.validation.Validator;
import java.lang.reflect.Field;

public class DefaultValidator implements Validator {

    @Override
    public boolean isValid(Object obj) {
        Class clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            Validate validateAnnotation = field.getAnnotation(Validate.class);
            if (validateAnnotation != null) {
                String pattern = validateAnnotation.pattern();
                field.setAccessible(true);
                Object fieldValue = null;
                try {
                    fieldValue = field.get(obj);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (fieldValue instanceof String) {
                    if (!((String) fieldValue).matches(pattern)) {
                        System.out.println("FALSE " + fieldValue);
                        return false;
                    }
                }
            }
        }

        return true;
    }
}

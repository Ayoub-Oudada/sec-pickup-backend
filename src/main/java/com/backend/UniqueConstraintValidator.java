package com.backend;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class UniqueConstraintValidator implements ConstraintValidator<UniqueField, String> {
    private String fieldName;
    private JpaRepository<?, ?> repository;
    private final ApplicationContext applicationContext;
    private final HttpServletRequest httpServletRequest;

    public UniqueConstraintValidator(ApplicationContext applicationContext, HttpServletRequest httpServletRequest) {
        this.applicationContext = applicationContext;
        this.httpServletRequest = httpServletRequest;
    }


    @Override
    public void initialize(UniqueField constraintAnnotation) {
        fieldName = constraintAnnotation.fieldName();
        repository = (JpaRepository<?, ?>) applicationContext.getBean(constraintAnnotation.repository());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String method = httpServletRequest.getMethod();

        var data = repository.findAll().stream().filter(entity -> {
            try {
                Field field = entity.getClass().getDeclaredField(fieldName);
                Field id = entity.getClass().getSuperclass().getDeclaredField("id");

                field.setAccessible(true);
                id.setAccessible(true);

                Object value = field.get(entity);
                var curId = extractIdFromURI(httpServletRequest.getRequestURI());

                return method.equals("PUT")
                        ? s.equals(value) && !id.get(entity).equals(curId)
                        : s.equals(value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        });

        return data.count() > 0 ? false : true;
    }

    private String extractIdFromURI(String uri) {
        String[] uriSegments = uri.split("/");

        return uriSegments[uriSegments.length - 1];
    }
}

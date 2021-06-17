package com.xiaokaige.annotataion;

import com.xiaokaige.dao.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zengkai
 * @date 2021/6/15 16:39
 */
@Slf4j
@Aspect
@Component
public class CheckUserImpl implements ConstraintValidator<MyselfDefineAnnotation,Long> {

    private MyselfDefineAnnotation constraintAnnotation;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void initialize(MyselfDefineAnnotation constraintAnnotation) {
        this.constraintAnnotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintValidatorContext) {
        if(value == null || value == 0){
            return constraintAnnotation.allowNull();
        }

        boolean doValid = this.constraintAnnotation.doValid();
        if (!doValid) {
            return true;
        }

        Integer count = studentMapper.findStudentById(value);
        if(count > 0){
            return true;
        }
        return false;
    }
}

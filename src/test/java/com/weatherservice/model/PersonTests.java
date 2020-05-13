package com.weatherservice.model;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PersonTests {
    @Test
    public void testGetterSetter() {
        PojoClass pojoclass = PojoClassFactory.getPojoClass(Person.class);
        Validator validator = ValidatorBuilder
                .create()
                .with(new SetterMustExistRule())
                .with(new GetterMustExistRule())
                .with(new SetterTester())
                .with(new GetterTester())
                .build();
        validator.validate(pojoclass);
    }

    @Test
    public void testTempInCity() throws IOException {
        Person person1 = new Person(1L, "John", "Washington");
        assertThat(person1.tempInCity(), not(equalTo("Not Found")));

        Person person2 = new Person(2L, "John", "ttt");
        assertEquals(person2.tempInCity(), "Not Found");
    }
}

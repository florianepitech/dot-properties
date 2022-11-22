package net.dot.properties.enums;

import net.dot.properties.PropertiesFormat;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface PropertiesElement {

    @NotNull String name();

    @NotNull String[] description() default {};

    @NotNull String[] regex() default {};

    @NotNull PropertiesFormat.Default[] format() default {};

    boolean required() default false;

}

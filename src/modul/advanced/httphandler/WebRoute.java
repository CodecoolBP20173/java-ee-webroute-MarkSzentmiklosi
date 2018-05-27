package modul.advanced.httphandler;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebRoute{
    String value();
    String requestMethod() default "GET";
}

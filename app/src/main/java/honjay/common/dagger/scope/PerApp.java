package honjay.common.dagger.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by honjayChen on 2017/4/6.
 */

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApp {
}

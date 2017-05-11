package honjay.common.dagger.scope;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by honjayChen on 2017/4/5.
 */

@Scope
@Retention(RUNTIME)
public @interface PerService {
}
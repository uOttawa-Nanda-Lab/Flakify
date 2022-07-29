/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ninja.params;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ninja.Context;
import ninja.Result;
import ninja.RoutingException;
import ninja.session.FlashCookie;
import ninja.session.SessionCookie;
import ninja.validation.JSR303Validation;
import ninja.validation.NumberValue;
import ninja.validation.Required;
import ninja.validation.Validation;
import ninja.validation.ValidationImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;


@RunWith(MockitoJUnitRunner.class)
public class ControllerMethodInvokerTest {

    @Mock
	private MockController mockController;
    @Mock
    private Context context;
    @Mock
    private SessionCookie session;
    @Mock
    private FlashCookie flash;

    private Validation validation;

    @Before
    public void setUp() throws Exception {
        validation = new ValidationImpl();
        when(context.getSessionCookie()).thenReturn(session);
        when(context.getFlashCookie()).thenReturn(flash);
        when(context.getValidation()).thenReturn(validation);
    }

    @Test public void floatValidationShouldWork() throws Exception{when(context.getParameter("param1")).thenReturn("blah");create("floatParam").invoke(mockController,context);verify(mockController).floatParam(null);assertTrue(validation.hasFieldViolation("param1"));}

    

    // JSR303Validation(@Pattern(regexp = "[a-z]*") String param1,
    // @Length(min = 5, max = 10) String param2, @Min(3) @Max(10) int param3);
    private void validateJSR303(Dto dto) {
        when(context.parseBody(Dto.class)).thenReturn(dto);
        create("JSR303Validation").invoke(mockController, context);
    }

    private void validateJSR303WithRequired(Dto dto) {
        when(context.parseBody(Dto.class)).thenReturn(dto);
        create("JSR303ValidationWithRequired").invoke(mockController, context);
    }

    private Dto buildDto(String regex, String length, int range) {
        Dto dto = new Dto();
        dto.regex = regex;
        dto.length = length;
        dto.range = range;
        return dto;
    }

    private ControllerMethodInvoker create(String methodName, final Object... toBind) {
        Method method = null;
        for (Method m : MockController.class.getMethods()) {
            if (m.getName().equals(methodName)) {
                method = m;
                break;
            }
        }
        return ControllerMethodInvoker.build(method, Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                for (Object o : toBind) {
                    bind((Class<Object>) o.getClass()).toInstance(o);
                }
            }
        }));
    }

    public interface MockController {
        public Result noParameter();
        public Result context(Context context);
        public Result session(SessionCookie session);
        public Result flash(FlashCookie flash);
        public Result param(@Param("param1") String param1);
        public Result pathParam(@PathParam("param1") String param1);
        public Result sessionParam(@SessionParam("param1") String param1);
        public Result attribute(@Attribute("param1") Dep param1);
        public Result integerParam(@Param("param1") Integer param1);
        public Result intParam(@Param("param1") int param1);
        public Result booleanParam(@Param("param1") Boolean param1);
        public Result primBooleanParam(@Param("param1") boolean param1);
        public Result longParam(@Param("param1") Long param1);
        public Result primLongParam(@Param("param1") long param1);
        public Result floatParam(@Param("param1") Float param1);
        public Result primFloatParam(@Param("param1") float param1);
        public Result doubleParam(@Param("param1") Double param1);
        public Result primDoubleParam(@Param("param1") double param1);
        public Result noArgArgumentExtractor(@NoArg String param1);
        public Result classArgArgumentExtractor(@ClassArg String param1);
        public Result guiceArgumentExtractor(@GuiceAnnotation(foo = "bar") String param1);
        public Result multiple(@Param("param1") String param1, @PathParam("param2") int param2,
                Context context, SessionCookie session);
        public Result required(@Param("param1") @Required String param1);
        public Result requiredInt(@Param("param1") @Required @NumberValue(min = 10) int param1);
        public Result badValidator(@Param("param1") @NumberValue(min = 10) String param1);
        public Result body(Object body);
        public Result tooManyBodies(Object body1, Object body2);

        public Result JSR303Validation(@JSR303Validation Dto dto, Validation validation);

        public Result JSR303ValidationWithRequired(@Required @JSR303Validation Dto dto,
                Validation validation);
    }

    // Custom argument extractors for testing different instantiation paths

    public static class NoArgArgumentExtractor implements ArgumentExtractor<String> {
        @Override
        public String extract(Context context) {
            return "noargs";
        }

        @Override
        public Class<String> getExtractedType() {
            return String.class;
        }

        @Override
        public String getFieldName() {
            return null;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    @WithArgumentExtractor(NoArgArgumentExtractor.class)
    public @interface NoArg {}

    public static class ClassArgArgumentExtractor implements ArgumentExtractor<String> {
        private final Class<?> clazz;

        public ClassArgArgumentExtractor(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public String extract(Context context) {
            return clazz.getName();
        }

        @Override
        public Class<String> getExtractedType() {
            return String.class;
        }

        @Override
        public String getFieldName() {
            return null;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    @WithArgumentExtractor(ClassArgArgumentExtractor.class)
    public @interface ClassArg {}

    public class Dep {
        private final String value;

        public Dep(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    /**
     * Argument extractor that has a complex constructor for Guice. It depends on some
     * other dependency (dep), plus the annotation that was on the parameter, and the
     * class of the parameter.
     */
    public static class GuiceArgumentExtractor implements ArgumentExtractor<String> {
        private final Dep dep;
        private final GuiceAnnotation annot;
        private final Class clazz;

        @Inject
        public GuiceArgumentExtractor(Dep dep, GuiceAnnotation annot, ArgumentClassHolder holder) {
            this.dep = dep;
            this.annot = annot;
            this.clazz = holder.getArgumentClass();
        }

        @Override
        public String extract(Context context) {
            return dep.value() + ":" + annot.foo() + ":" + clazz.getName();
        }

        @Override
        public Class<String> getExtractedType() {
            return String.class;
        }

        @Override
        public String getFieldName() {
            return null;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    @WithArgumentExtractor(GuiceArgumentExtractor.class)
    public @interface GuiceAnnotation {
        String foo();
    }

    public class Dto {
        @Size(min = 1, max = 10)
        @Pattern(regexp = "[a-z]*")
        public String regex;
        @Size(min = 5, max = 10)
        public String length;
        @Min(value = 3)
        @Max(value = 10)
        public int range;
    }

}

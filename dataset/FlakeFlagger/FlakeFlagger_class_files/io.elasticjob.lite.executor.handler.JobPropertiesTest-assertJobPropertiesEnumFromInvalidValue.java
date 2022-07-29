/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
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
 * </p>
 */

package io.elasticjob.lite.executor.handler;

import io.elasticjob.lite.executor.handler.impl.DefaultExecutorServiceHandler;
import io.elasticjob.lite.executor.handler.impl.DefaultJobExceptionHandler;
import io.elasticjob.lite.fixture.APIJsonConstants;
import io.elasticjob.lite.fixture.handler.IgnoreJobExceptionHandler;
import org.junit.Test;
import org.unitils.util.ReflectionUtils;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class JobPropertiesTest {
    
    private Map getMap(final JobProperties jobProperties) throws NoSuchFieldException {
        return (Map) ReflectionUtils.getFieldValue(jobProperties, JobProperties.class.getDeclaredField("map"));
    }
    
    @Test public void assertJobPropertiesEnumFromInvalidValue(){assertNull(JobProperties.JobPropertiesEnum.from("invalid"));}
}

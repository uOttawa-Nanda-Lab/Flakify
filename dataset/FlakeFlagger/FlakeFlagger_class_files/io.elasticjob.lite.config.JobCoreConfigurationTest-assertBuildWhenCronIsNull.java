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

package io.elasticjob.lite.config;

import io.elasticjob.lite.executor.handler.JobProperties;
import io.elasticjob.lite.executor.handler.impl.DefaultJobExceptionHandler;
import io.elasticjob.lite.fixture.handler.IgnoreJobExceptionHandler;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public final class JobCoreConfigurationTest {
    
    private void assertRequiredProperties(final JobCoreConfiguration actual) {
        assertThat(actual.getJobName(), is("test_job"));
        assertThat(actual.getCron(), is("0/1 * * * * ?"));
        assertThat(actual.getShardingTotalCount(), is(3));
    }
    
    private void assertDefaultValues(final JobCoreConfiguration actual) {
        assertThat(actual.getShardingItemParameters(), is(""));
        assertThat(actual.getJobParameter(), is(""));
        assertFalse(actual.isFailover());
        assertTrue(actual.isMisfire());
        assertThat(actual.getDescription(), is(""));
        assertThat(actual.getJobProperties().get(JobProperties.JobPropertiesEnum.JOB_EXCEPTION_HANDLER), is(DefaultJobExceptionHandler.class.getName()));
    }
    
    @Test(expected=IllegalArgumentException.class) public void assertBuildWhenCronIsNull(){JobCoreConfiguration.newBuilder("test_job",null,3).build();}
}

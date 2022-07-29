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

package io.elasticjob.lite.lifecycle.internal.settings;

import io.elasticjob.lite.executor.handler.JobProperties.JobPropertiesEnum;
import io.elasticjob.lite.executor.handler.impl.DefaultExecutorServiceHandler;
import io.elasticjob.lite.executor.handler.impl.DefaultJobExceptionHandler;
import io.elasticjob.lite.lifecycle.api.JobSettingsAPI;
import io.elasticjob.lite.lifecycle.domain.JobSettings;
import io.elasticjob.lite.lifecycle.fixture.LifecycleJsonConstants;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class JobSettingsAPIImplTest {
    
    private JobSettingsAPI jobSettingsAPI;
    
    @Mock
    private CoordinatorRegistryCenter regCenter;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jobSettingsAPI = new JobSettingsAPIImpl(regCenter);
    }
    
    private void assertJobSettings(final JobSettings jobSettings, final String jobType, final String className) {
        assertThat(jobSettings.getJobName(), is("test_job"));
        assertThat(jobSettings.getJobType(), is(jobType));
        assertThat(jobSettings.getJobClass(), is(className));
        assertThat(jobSettings.getShardingTotalCount(), is(3));
        assertThat(jobSettings.getCron(), is("0/1 * * * * ?"));
        assertThat(jobSettings.getShardingItemParameters(), is(""));
        assertThat(jobSettings.getJobParameter(), is("param"));
        assertThat(jobSettings.isMonitorExecution(), is(true));
        assertThat(jobSettings.getMaxTimeDiffSeconds(), is(-1));
        assertThat(jobSettings.getMonitorPort(), is(8888));
        assertFalse(jobSettings.isFailover());
        assertTrue(jobSettings.isMisfire());
        assertThat(jobSettings.getJobShardingStrategyClass(), is(""));
        assertThat(jobSettings.getReconcileIntervalMinutes(), is(10));
        jobSettings.getJobProperties().put(JobPropertiesEnum.EXECUTOR_SERVICE_HANDLER.getKey(), DefaultExecutorServiceHandler.class.getCanonicalName());
        jobSettings.getJobProperties().put(JobPropertiesEnum.JOB_EXCEPTION_HANDLER.getKey(), DefaultJobExceptionHandler.class.getCanonicalName());
        assertThat(jobSettings.getDescription(), is(""));
        if ("DATAFLOW".equals(jobType)) {
            assertTrue(jobSettings.isStreamingProcess());
        }
        if ("SCRIPT".equals(jobType)) {
            assertThat(jobSettings.getScriptCommandLine(), is("test.sh"));
        }
    }
    
    @Test(expected=IllegalArgumentException.class) public void assertUpdateJobSettingsIfJobNameIsEmpty(){JobSettings jobSettings=new JobSettings();jobSettings.setJobName("");jobSettingsAPI.updateJobSettings(jobSettings);}
}

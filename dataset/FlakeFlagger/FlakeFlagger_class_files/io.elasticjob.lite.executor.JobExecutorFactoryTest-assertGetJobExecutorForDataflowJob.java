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

package io.elasticjob.lite.executor;

import io.elasticjob.lite.exception.JobConfigurationException;
import io.elasticjob.lite.executor.type.DataflowJobExecutor;
import io.elasticjob.lite.executor.type.ScriptJobExecutor;
import io.elasticjob.lite.executor.type.SimpleJobExecutor;
import io.elasticjob.lite.fixture.config.TestDataflowJobConfiguration;
import io.elasticjob.lite.fixture.config.TestScriptJobConfiguration;
import io.elasticjob.lite.fixture.config.TestSimpleJobConfiguration;
import io.elasticjob.lite.fixture.handler.IgnoreJobExceptionHandler;
import io.elasticjob.lite.fixture.job.OtherJob;
import io.elasticjob.lite.fixture.job.TestDataflowJob;
import io.elasticjob.lite.fixture.job.TestSimpleJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public final class JobExecutorFactoryTest {
    
    @Mock
    private JobFacade jobFacade;
    
    @Test public void assertGetJobExecutorForDataflowJob(){when(jobFacade.loadJobRootConfiguration(true)).thenReturn(new TestDataflowJobConfiguration(false));assertThat(JobExecutorFactory.getJobExecutor(new TestDataflowJob(null),jobFacade),instanceOf(DataflowJobExecutor.class));}
}

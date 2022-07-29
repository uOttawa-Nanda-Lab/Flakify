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

package io.elasticjob.lite.lifecycle.internal.statistics;

import io.elasticjob.lite.lifecycle.api.JobStatisticsAPI;
import io.elasticjob.lite.lifecycle.domain.JobBriefInfo;
import io.elasticjob.lite.lifecycle.fixture.LifecycleJsonConstants;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;
import com.google.common.collect.Lists;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

public final class JobStatisticsAPIImplTest {
    
    private JobStatisticsAPI jobStatisticsAPI;
    
    @Mock
    private CoordinatorRegistryCenter regCenter;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jobStatisticsAPI = new JobStatisticsAPIImpl(regCenter);
    }
    
    @Test public void assertGetJobsBriefInfoByIp(){when(regCenter.getChildrenKeys("/")).thenReturn(Arrays.asList("test_job_1","test_job_2","test_job_3"));when(regCenter.getChildrenKeys("/test_job_1/servers")).thenReturn(Collections.singletonList("ip1"));when(regCenter.getChildrenKeys("/test_job_2/servers")).thenReturn(Collections.singletonList("ip1"));when(regCenter.getChildrenKeys("/test_job_3/servers")).thenReturn(Collections.singletonList("ip1"));when(regCenter.isExisted("/test_job_1/servers/ip1")).thenReturn(true);when(regCenter.isExisted("/test_job_2/servers/ip1")).thenReturn(true);when(regCenter.get("/test_job_2/servers/ip1")).thenReturn("DISABLED");when(regCenter.getChildrenKeys("/test_job_1/instances")).thenReturn(Collections.singletonList("ip1@-@defaultInstance"));int i=0;for (JobBriefInfo each:jobStatisticsAPI.getJobsBriefInfo("ip1")){assertThat(each.getJobName(),is("test_job_" + ++i));if (i == 1){assertThat(each.getInstanceCount(),is(1));assertThat(each.getStatus(),Is.is(JobBriefInfo.JobStatus.OK));} else if (i == 2){assertThat(each.getInstanceCount(),is(0));assertThat(each.getStatus(),Is.is(JobBriefInfo.JobStatus.DISABLED));}}}
}

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
    
    @Test public void assertGetOKJobBriefInfoWithPartialDisabledServer(){when(regCenter.getChildrenKeys("/")).thenReturn(Lists.newArrayList("test_job"));when(regCenter.get("/test_job/config")).thenReturn(LifecycleJsonConstants.getSimpleJobJson("test_job","desc"));when(regCenter.getChildrenKeys("/test_job/servers")).thenReturn(Arrays.asList("ip1","ip2"));when(regCenter.get("/test_job/servers/ip1")).thenReturn("DISABLED");when(regCenter.getChildrenKeys("/test_job/instances")).thenReturn(Arrays.asList("ip1@-@defaultInstance","ip2@-@defaultInstance"));when(regCenter.getChildrenKeys("/test_job/sharding")).thenReturn(Arrays.asList("0","1"));when(regCenter.get("/test_job/sharding/0/instance")).thenReturn("ip1@-@defaultInstance");when(regCenter.get("/test_job/sharding/1/instance")).thenReturn("ip2@-@defaultInstance");JobBriefInfo jobBrief=jobStatisticsAPI.getJobBriefInfo("test_job");assertThat(jobBrief.getStatus(),Is.is(JobBriefInfo.JobStatus.OK));}
}

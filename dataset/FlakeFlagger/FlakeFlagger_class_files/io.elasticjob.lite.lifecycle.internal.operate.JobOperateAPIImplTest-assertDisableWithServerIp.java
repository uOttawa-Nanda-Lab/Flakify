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

package io.elasticjob.lite.lifecycle.internal.operate;

import io.elasticjob.lite.lifecycle.api.JobOperateAPI;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;
import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class JobOperateAPIImplTest {
    
    private JobOperateAPI jobOperateAPI;
    
    @Mock
    private CoordinatorRegistryCenter regCenter;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jobOperateAPI = new JobOperateAPIImpl(regCenter);
    }
    
    @Test public void assertDisableWithServerIp(){when(regCenter.getChildrenKeys("/")).thenReturn(Arrays.asList("test_job1","test_job2"));when(regCenter.isExisted("/test_job1/servers/localhost")).thenReturn(true);when(regCenter.isExisted("/test_job2/servers/localhost")).thenReturn(true);jobOperateAPI.disable(Optional.<String>absent(),Optional.of("localhost"));verify(regCenter).getChildrenKeys("/");verify(regCenter).persist("/test_job1/servers/localhost","DISABLED");verify(regCenter).persist("/test_job2/servers/localhost","DISABLED");}
}

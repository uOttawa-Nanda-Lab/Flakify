package io.elasticjob.lite.internal.instance;

import io.elasticjob.lite.api.strategy.JobInstance;
import io.elasticjob.lite.internal.schedule.JobRegistry;
import io.elasticjob.lite.internal.server.ServerService;
import io.elasticjob.lite.internal.storage.JobNodeStorage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.unitils.util.ReflectionUtils;

import java.util.Arrays;
import java.util.Collections;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class InstanceServiceTest {
    
    @Mock
    private JobNodeStorage jobNodeStorage;
    
    @Mock
    private ServerService serverService;
    
    private InstanceService instanceService;
    
    @Before
    public void setUp() throws NoSuchFieldException {
        JobRegistry.getInstance().addJobInstance("test_job", new JobInstance("127.0.0.1@-@0"));
        instanceService = new InstanceService(null, "test_job");
        MockitoAnnotations.initMocks(this);
        InstanceNode instanceNode = new InstanceNode("test_job");
        ReflectionUtils.setFieldValue(instanceService, "instanceNode", instanceNode);
        ReflectionUtils.setFieldValue(instanceService, "jobNodeStorage", jobNodeStorage);
        ReflectionUtils.setFieldValue(instanceService, "serverService", serverService);
    }
    
    @Test public void assertIsLocalJobInstanceExisted(){when(jobNodeStorage.isJobNodeExisted("instances/127.0.0.1@-@0")).thenReturn(true);assertTrue(instanceService.isLocalJobInstanceExisted());}
}

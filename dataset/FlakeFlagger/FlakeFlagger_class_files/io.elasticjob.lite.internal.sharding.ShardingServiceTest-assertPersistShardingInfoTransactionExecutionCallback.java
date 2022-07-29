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

package io.elasticjob.lite.internal.sharding;

import io.elasticjob.lite.api.strategy.JobInstance;
import io.elasticjob.lite.config.JobCoreConfiguration;
import io.elasticjob.lite.config.LiteJobConfiguration;
import io.elasticjob.lite.config.simple.SimpleJobConfiguration;
import io.elasticjob.lite.fixture.TestSimpleJob;
import io.elasticjob.lite.internal.config.ConfigurationService;
import io.elasticjob.lite.internal.election.LeaderService;
import io.elasticjob.lite.internal.instance.InstanceNode;
import io.elasticjob.lite.internal.instance.InstanceService;
import io.elasticjob.lite.internal.schedule.JobRegistry;
import io.elasticjob.lite.internal.schedule.JobScheduleController;
import io.elasticjob.lite.internal.server.ServerService;
import io.elasticjob.lite.internal.storage.JobNodeStorage;
import io.elasticjob.lite.internal.storage.TransactionExecutionCallback;
import io.elasticjob.lite.reg.base.CoordinatorRegistryCenter;
import org.apache.curator.framework.api.transaction.CuratorTransactionBridge;
import org.apache.curator.framework.api.transaction.CuratorTransactionFinal;
import org.apache.curator.framework.api.transaction.TransactionCreateBuilder;
import org.apache.curator.framework.api.transaction.TransactionDeleteBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.unitils.util.ReflectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class ShardingServiceTest {
    
    @Mock
    private CoordinatorRegistryCenter regCenter;
    
    @Mock
    private JobScheduleController jobScheduleController;
    
    @Mock
    private JobNodeStorage jobNodeStorage;
    
    @Mock
    private LeaderService leaderService;
    
    @Mock
    private ConfigurationService configService;
    
    @Mock
    private ExecutionService executionService;
    
    @Mock
    private ServerService serverService;
    
    @Mock
    private InstanceService instanceService;
    
    private final ShardingService shardingService = new ShardingService(null, "test_job");
    
    @Before
    public void setUp() throws NoSuchFieldException {
        MockitoAnnotations.initMocks(this);
        ReflectionUtils.setFieldValue(shardingService, "jobNodeStorage", jobNodeStorage);
        ReflectionUtils.setFieldValue(shardingService, "leaderService", leaderService);
        ReflectionUtils.setFieldValue(shardingService, "configService", configService);
        ReflectionUtils.setFieldValue(shardingService, "executionService", executionService);
        ReflectionUtils.setFieldValue(shardingService, "instanceService", instanceService);
        ReflectionUtils.setFieldValue(shardingService, "serverService", serverService);
        JobRegistry.getInstance().addJobInstance("test_job", new JobInstance("127.0.0.1@-@0"));
    }
    
    @Test public void assertPersistShardingInfoTransactionExecutionCallback() throws Exception{CuratorTransactionFinal curatorTransactionFinal=mock(CuratorTransactionFinal.class);TransactionCreateBuilder transactionCreateBuilder=mock(TransactionCreateBuilder.class);TransactionDeleteBuilder transactionDeleteBuilder=mock(TransactionDeleteBuilder.class);CuratorTransactionBridge curatorTransactionBridge=mock(CuratorTransactionBridge.class);when(curatorTransactionFinal.create()).thenReturn(transactionCreateBuilder);when(configService.load(true)).thenReturn(LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestSimpleJob.class.getCanonicalName())).build());when(transactionCreateBuilder.forPath("/test_job/sharding/0/instance","host0@-@0".getBytes())).thenReturn(curatorTransactionBridge);when(transactionCreateBuilder.forPath("/test_job/sharding/1/instance","host0@-@0".getBytes())).thenReturn(curatorTransactionBridge);when(transactionCreateBuilder.forPath("/test_job/sharding/2/instance","host0@-@0".getBytes())).thenReturn(curatorTransactionBridge);when(curatorTransactionBridge.and()).thenReturn(curatorTransactionFinal);when(curatorTransactionFinal.delete()).thenReturn(transactionDeleteBuilder);when(transactionDeleteBuilder.forPath("/test_job/leader/sharding/necessary")).thenReturn(curatorTransactionBridge);when(curatorTransactionBridge.and()).thenReturn(curatorTransactionFinal);when(curatorTransactionFinal.delete()).thenReturn(transactionDeleteBuilder);when(transactionDeleteBuilder.forPath("/test_job/leader/sharding/processing")).thenReturn(curatorTransactionBridge);when(curatorTransactionBridge.and()).thenReturn(curatorTransactionFinal);Map<JobInstance, List<Integer>> shardingResult=new HashMap<>();shardingResult.put(new JobInstance("host0@-@0"),Arrays.asList(0,1,2));ShardingService.PersistShardingInfoTransactionExecutionCallback actual=shardingService.new PersistShardingInfoTransactionExecutionCallback(shardingResult);actual.execute(curatorTransactionFinal);verify(curatorTransactionFinal,times(3)).create();verify(curatorTransactionFinal,times(2)).delete();verify(transactionDeleteBuilder).forPath("/test_job/leader/sharding/necessary");verify(transactionDeleteBuilder).forPath("/test_job/leader/sharding/processing");verify(curatorTransactionBridge,times(5)).and();}
}

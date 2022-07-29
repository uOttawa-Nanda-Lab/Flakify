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

package io.elasticjob.lite.internal.config;

import io.elasticjob.lite.api.JobType;
import io.elasticjob.lite.api.script.ScriptJob;
import io.elasticjob.lite.config.JobCoreConfiguration;
import io.elasticjob.lite.config.LiteJobConfiguration;
import io.elasticjob.lite.config.dataflow.DataflowJobConfiguration;
import io.elasticjob.lite.config.script.ScriptJobConfiguration;
import io.elasticjob.lite.config.simple.SimpleJobConfiguration;
import io.elasticjob.lite.executor.handler.JobProperties;
import io.elasticjob.lite.executor.handler.impl.DefaultExecutorServiceHandler;
import io.elasticjob.lite.executor.handler.impl.DefaultJobExceptionHandler;
import io.elasticjob.lite.fixture.TestDataflowJob;
import io.elasticjob.lite.fixture.TestSimpleJob;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public final class LiteJobConfigurationGsonFactoryTest {
    
    private static final String JOB_PROPS_JSON = "{\"job_exception_handler\":\"" + DefaultJobExceptionHandler.class.getCanonicalName() + "\","
            + "\"executor_service_handler\":\"" + DefaultExecutorServiceHandler.class.getCanonicalName() + "\"}";
    
    private String simpleJobJson =  "{\"jobName\":\"test_job\",\"jobClass\":\"io.elasticjob.lite.fixture.TestSimpleJob\",\"jobType\":\"SIMPLE\",\"cron\":\"0/1 * * * * ?\","
            + "\"shardingTotalCount\":3,\"shardingItemParameters\":\"\",\"jobParameter\":\"\",\"failover\":true,\"misfire\":false,\"description\":\"\","
            + "\"jobProperties\":" + JOB_PROPS_JSON + ",\"monitorExecution\":false,\"maxTimeDiffSeconds\":1000,\"monitorPort\":8888,"
            + "\"jobShardingStrategyClass\":\"testClass\",\"reconcileIntervalMinutes\":15,\"disabled\":true,\"overwrite\":true}";
    
    private String dataflowJobJson = "{\"jobName\":\"test_job\",\"jobClass\":\"io.elasticjob.lite.fixture.TestDataflowJob\",\"jobType\":\"DATAFLOW\",\"cron\":\"0/1 * * * * ?\","
            + "\"shardingTotalCount\":3,\"shardingItemParameters\":\"\",\"jobParameter\":\"\",\"failover\":false,\"misfire\":true,\"description\":\"\","
            + "\"jobProperties\":" + JOB_PROPS_JSON + ",\"streamingProcess\":true,"
            + "\"monitorExecution\":true,\"maxTimeDiffSeconds\":-1,\"monitorPort\":-1,\"jobShardingStrategyClass\":\"\",\"reconcileIntervalMinutes\":10,\"disabled\":false,\"overwrite\":false}";
    
    private String scriptJobJson = "{\"jobName\":\"test_job\",\"jobClass\":\"io.elasticjob.lite.api.script.ScriptJob\",\"jobType\":\"SCRIPT\",\"cron\":\"0/1 * * * * ?\","
            + "\"shardingTotalCount\":3,\"shardingItemParameters\":\"\",\"jobParameter\":\"\",\"failover\":false,\"misfire\":true,\"description\":\"\","
            + "\"jobProperties\":" + JOB_PROPS_JSON + ",\"scriptCommandLine\":\"test.sh\",\"monitorExecution\":true,\"maxTimeDiffSeconds\":-1,\"monitorPort\":-1,"
            + "\"jobShardingStrategyClass\":\"\",\"reconcileIntervalMinutes\":10,\"disabled\":false,\"overwrite\":false}";
    
    @Test public void assertToJsonForDataflowJob(){LiteJobConfiguration actual=LiteJobConfiguration.newBuilder(new DataflowJobConfiguration(JobCoreConfiguration.newBuilder("test_job","0/1 * * * * ?",3).build(),TestDataflowJob.class.getCanonicalName(),true)).build();assertThat(LiteJobConfigurationGsonFactory.toJson(actual),is(dataflowJobJson));}
}

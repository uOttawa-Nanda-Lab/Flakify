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

package io.elasticjob.lite.event.rdb;

import io.elasticjob.lite.context.ExecutionType;
import io.elasticjob.lite.event.type.JobExecutionEvent;
import io.elasticjob.lite.event.type.JobStatusTraceEvent;
import io.elasticjob.lite.event.type.JobStatusTraceEvent.Source;
import io.elasticjob.lite.event.type.JobStatusTraceEvent.State;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class JobEventRdbSearchTest {
    
    private static JobEventRdbStorage storage;
    
    private static JobEventRdbSearch  repository;
    
    @BeforeClass
    public static void setUpClass() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(org.h2.Driver.class.getName());
        dataSource.setUrl("jdbc:h2:mem:");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        storage = new JobEventRdbStorage(dataSource);
        repository = new JobEventRdbSearch(dataSource);
        initStorage();
    }
    
    private static void initStorage() {
        for (int i = 1; i <= 500; i++) {
            JobExecutionEvent startEvent = new JobExecutionEvent("fake_task_id", "test_job_" + i, JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER, 0);
            storage.addJobExecutionEvent(startEvent);
            if (i % 2 == 0) {
                JobExecutionEvent successEvent = startEvent.executionSuccess();
                storage.addJobExecutionEvent(successEvent);
            }
            storage.addJobStatusTraceEvent(
                    new JobStatusTraceEvent("test_job_" + i, "fake_failed_failover_task_id", "fake_slave_id", 
                            Source.LITE_EXECUTOR, ExecutionType.FAILOVER, "0", State.TASK_FAILED, "message is empty."));
        }
    }
    
    @Test public void assertFindJobStatusTraceEventsWithSort(){JobEventRdbSearch.Result<JobStatusTraceEvent> result=repository.findJobStatusTraceEvents(new JobEventRdbSearch.Condition(10,1,"jobName","ASC",null,null,null));assertThat(result.getTotal(),is(500));assertThat(result.getRows().size(),is(10));assertThat(result.getRows().get(0).getJobName(),is("test_job_1"));result=repository.findJobStatusTraceEvents(new JobEventRdbSearch.Condition(10,1,"jobName","DESC",null,null,null));assertThat(result.getTotal(),is(500));assertThat(result.getRows().size(),is(10));assertThat(result.getRows().get(0).getJobName(),is("test_job_99"));}
}

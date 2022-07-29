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

package io.elasticjob.lite.statistics.rdb;

import com.google.common.base.Optional;
import io.elasticjob.lite.statistics.StatisticInterval;
import io.elasticjob.lite.statistics.type.job.JobRegisterStatistics;
import io.elasticjob.lite.statistics.type.job.JobRunningStatistics;
import io.elasticjob.lite.statistics.type.task.TaskResultStatistics;
import io.elasticjob.lite.statistics.type.task.TaskRunningStatistics;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class StatisticRdbRepositoryTest {
    
    private StatisticRdbRepository repository;
    
    @Before
    public void setup() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(org.h2.Driver.class.getName());
        dataSource.setUrl("jdbc:h2:mem:");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        repository = new StatisticRdbRepository(dataSource);
    }
    
    @Test public void assertFindJobRegisterStatisticsWithDifferentFromDate(){Date now=new Date();Date yesterday=getYesterday();assertTrue(repository.add(new JobRegisterStatistics(100,yesterday)));assertTrue(repository.add(new JobRegisterStatistics(100,now)));assertThat(repository.findJobRegisterStatistics(yesterday).size(),is(2));assertThat(repository.findJobRegisterStatistics(now).size(),is(1));}
    
    private Date getYesterday() {
        return new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
    }
}

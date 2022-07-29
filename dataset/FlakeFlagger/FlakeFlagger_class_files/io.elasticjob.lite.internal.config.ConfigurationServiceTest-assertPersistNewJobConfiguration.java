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

import io.elasticjob.lite.config.LiteJobConfiguration;
import io.elasticjob.lite.exception.JobConfigurationException;
import io.elasticjob.lite.exception.JobExecutionEnvironmentException;
import io.elasticjob.lite.fixture.LiteJsonConstants;
import io.elasticjob.lite.fixture.util.JobConfigurationUtil;
import io.elasticjob.lite.internal.storage.JobNodeStorage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.unitils.util.ReflectionUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class ConfigurationServiceTest {
    
    @Mock
    private JobNodeStorage jobNodeStorage;
    
    private final ConfigurationService configService = new ConfigurationService(null, "test_job");
    
    @Before
    public void initMocks() throws NoSuchFieldException {
        MockitoAnnotations.initMocks(this);
        ReflectionUtils.setFieldValue(configService, "jobNodeStorage", jobNodeStorage);
    }
    
    @Test public void assertPersistNewJobConfiguration(){LiteJobConfiguration liteJobConfig=JobConfigurationUtil.createSimpleLiteJobConfiguration();configService.persist(liteJobConfig);verify(jobNodeStorage).replaceJobNode("config",LiteJobConfigurationGsonFactory.toJson(liteJobConfig));}
}

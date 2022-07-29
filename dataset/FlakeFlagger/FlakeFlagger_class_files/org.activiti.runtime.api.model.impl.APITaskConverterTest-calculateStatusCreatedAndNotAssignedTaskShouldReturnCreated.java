/*
 * Copyright 2018 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.runtime.api.model.impl;

import java.util.Date;

import org.activiti.api.task.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.activiti.api.task.model.Task.TaskStatus.ASSIGNED;
import static org.activiti.api.task.model.Task.TaskStatus.CANCELLED;
import static org.activiti.api.task.model.Task.TaskStatus.CREATED;
import static org.activiti.api.task.model.Task.TaskStatus.SUSPENDED;
import static org.activiti.runtime.api.model.impl.MockTaskBuilder.taskBuilder;
import static org.activiti.runtime.api.model.impl.MockTaskBuilder.taskEntityBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

public class APITaskConverterTest {

    @InjectMocks
    private APITaskConverter taskConverter;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test public void calculateStatusCreatedAndNotAssignedTaskShouldReturnCreated(){assertThat(taskConverter.from(taskBuilder().build())).isNotNull().extracting(Task::getStatus).containsExactly(CREATED);}
}
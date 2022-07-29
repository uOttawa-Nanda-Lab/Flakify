/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.engine.test.api.v6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * These are the first tests ever written for Activiti 6.
 * Keeping them here for nostalgic reasons.
 */
public class Activiti6Test extends PluggableActivitiTestCase {

    /**
	 * Based on the process and use cases described in http://www.bp-3.com/blogs/2013/09/joins-and-ibm-bpm-diving-deeper/
	 */@Test @org.activiti.engine.test.Deployment(resources="org/activiti/engine/test/api/v6/Activiti6Test.testInclusiveTrickyMerge.bpmn20.xml") public void testInclusiveTrickyMergeEasy(){ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("trickyInclusiveMerge");assertNotNull(processInstance);assertFalse(processInstance.isEnded());assertEquals(3,taskService.createTaskQuery().count());Task taskC=taskService.createTaskQuery().taskName("C").singleResult();taskService.complete(taskC.getId());List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();assertEquals(3,tasks.size());assertEquals("A",tasks.get(0).getName());assertEquals("B",tasks.get(1).getName());assertEquals("E",tasks.get(2).getName());taskService.complete(tasks.get(0).getId());taskService.complete(tasks.get(1).getId());tasks=taskService.createTaskQuery().orderByTaskName().asc().list();assertEquals(2,tasks.size());assertEquals("D",tasks.get(0).getName());assertEquals("E",tasks.get(1).getName());taskService.complete(tasks.get(0).getId());taskService.complete(tasks.get(1).getId());assertEquals(0,runtimeService.createExecutionQuery().count());}
}

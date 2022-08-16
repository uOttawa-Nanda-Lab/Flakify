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
package org.activiti.engine.test.api.task;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

public class TaskBatchDeleteTest extends PluggableActivitiTestCase {

  /**
 * Validating fix for ACT-2070
 */@Deployment public void testDeleteTaskWithChildren() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("testBatchDeleteOfTask");assertNotNull(processInstance);assertFalse(processInstance.isEnded());Task firstTask=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskDefinitionKey("taskOne").singleResult();assertNotNull(firstTask);taskService.complete(firstTask.getId());processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();assertNull(processInstance);}
}

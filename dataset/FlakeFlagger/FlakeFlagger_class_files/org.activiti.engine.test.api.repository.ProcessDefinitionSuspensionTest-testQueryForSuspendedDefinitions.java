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
package org.activiti.engine.test.api.repository;

import java.util.Date;
import java.util.List;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

public class ProcessDefinitionSuspensionTest extends PluggableActivitiTestCase {

  @Deployment(resources={"org/activiti/engine/test/db/processOne.bpmn20.xml","org/activiti/engine/test/db/processTwo.bpmn20.xml"}) public void testQueryForSuspendedDefinitions(){List<ProcessDefinition> processDefinitionList=repositoryService.createProcessDefinitionQuery().list();assertEquals(2,processDefinitionList.size());assertEquals(2,repositoryService.createProcessDefinitionQuery().active().count());ProcessDefinition processDefinition=processDefinitionList.get(0);repositoryService.suspendProcessDefinitionById(processDefinition.getId());assertEquals(2,repositoryService.createProcessDefinitionQuery().count());assertEquals(1,repositoryService.createProcessDefinitionQuery().suspended().count());}

}

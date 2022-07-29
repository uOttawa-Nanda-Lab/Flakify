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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.ParallelGateway;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.test.Deployment;

/**


 */
public class RepositoryServiceTest extends PluggableActivitiTestCase {

  public void testDeploymentWithDelayedProcessDefinitionActivation() {
	Date startTime = new Date();
	processEngineConfiguration.getClock().setCurrentTime(startTime);
	Date inThreeDays = new Date(startTime.getTime() + (3 * 24 * 60 * 60 * 1000));
	org.activiti.engine.repository.Deployment deployment = repositoryService.createDeployment()
			.addClasspathResource("org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml")
			.addClasspathResource("org/activiti/engine/test/api/twoTasksProcess.bpmn20.xml")
			.activateProcessDefinitionsOn(inThreeDays).deploy();
	assertEquals(1, repositoryService.createDeploymentQuery().count());
	assertEquals(2, repositoryService.createProcessDefinitionQuery().count());
	assertEquals(2, repositoryService.createProcessDefinitionQuery().suspended().count());
	assertEquals(0, repositoryService.createProcessDefinitionQuery().active().count());
	try {
		runtimeService.startProcessInstanceByKey("oneTaskProcess");
		fail();
	} catch (ActivitiException e) {
		assertTextPresentIgnoreCase("suspended", e.getMessage());
	}
	Date inFourDays = new Date(startTime.getTime() + (4 * 24 * 60 * 60 * 1000));
	processEngineConfiguration.getClock().setCurrentTime(inFourDays);
	waitForJobExecutorToProcessAllJobs(5000L, 50L);
	assertEquals(1, repositoryService.createDeploymentQuery().count());
	assertEquals(2, repositoryService.createProcessDefinitionQuery().count());
	assertEquals(0, repositoryService.createProcessDefinitionQuery().suspended().count());
	assertEquals(2, repositoryService.createProcessDefinitionQuery().active().count());
	runtimeService.startProcessInstanceByKey("oneTaskProcess");
	assertEquals(1, runtimeService.createProcessInstanceQuery().count());
	repositoryService.deleteDeployment(deployment.getId(), true);
}

}

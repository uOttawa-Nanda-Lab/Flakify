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

package org.activiti.engine.test.bpmn.deployment;

import java.io.InputStream;
import java.util.List;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.IoUtil;
import org.activiti.engine.impl.util.ReflectUtil;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.test.Deployment;
import org.activiti.validation.validator.Problems;

/**


 */
public class BpmnDeploymentTest extends PluggableActivitiTestCase {

  private String readInputStreamToString(InputStream inputStream) {
    byte[] bytes = IoUtil.readInputStream(inputStream, "input stream");
    return new String(bytes);
  }

  public void testViolateProcessDefinitionNameAndDescriptionMaximumLength() {
	try {
		repositoryService.createDeployment()
				.addClasspathResource(
						"org/activiti/engine/test/bpmn/deployment/processWithLongNameAndDescription.bpmn20.xml")
				.deploy();
		fail();
	} catch (ActivitiException e) {
		assertTextPresent(Problems.PROCESS_DEFINITION_NAME_TOO_LONG, e.getMessage());
		assertTextPresent(Problems.PROCESS_DEFINITION_DOCUMENTATION_TOO_LONG, e.getMessage());
	}
	assertEquals(0, repositoryService.createDeploymentQuery().count());
}

}

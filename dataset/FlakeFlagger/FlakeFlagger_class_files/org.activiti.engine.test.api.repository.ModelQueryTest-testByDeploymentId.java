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

import java.util.List;

import org.activiti.engine.impl.persistence.entity.ModelEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.junit.Assert;

/**

 */
public class ModelQueryTest extends PluggableActivitiTestCase {

  private String modelOneId;

  public void testByDeploymentId() {
	Deployment deployment = repositoryService.createDeployment().addString("test", "test").deploy();
	assertEquals(0, repositoryService.createModelQuery().deploymentId(deployment.getId()).count());
	assertEquals(0, repositoryService.createModelQuery().deployed().count());
	assertEquals(1, repositoryService.createModelQuery().notDeployed().count());
	Model model = repositoryService.createModelQuery().singleResult();
	model.setDeploymentId(deployment.getId());
	repositoryService.saveModel(model);
	assertEquals(1, repositoryService.createModelQuery().deploymentId(deployment.getId()).count());
	assertEquals(1, repositoryService.createModelQuery().deployed().count());
	assertEquals(0, repositoryService.createModelQuery().notDeployed().count());
	repositoryService.deleteDeployment(deployment.getId(), true);
	assertEquals(0, repositoryService.createDeploymentQuery().count());
	assertEquals(0, repositoryService.createModelQuery().deploymentId(deployment.getId()).count());
	assertEquals(1, repositoryService.createModelQuery().notDeployed().count());
	assertEquals(1, repositoryService.createModelQuery().count());
}
}

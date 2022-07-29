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

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;

import java.util.List;

/**

 */
public class DeploymentQueryTest extends PluggableActivitiTestCase {

  private String deploymentOneId;

  private String deploymentTwoId;

  public void testVerifyDeploymentProperties(){List<Deployment> deployments=repositoryService.createDeploymentQuery().orderByDeploymentName().asc().list();Deployment deploymentOne=deployments.get(0);assertEquals("org/activiti/engine/test/repository/one.bpmn20.xml",deploymentOne.getName());assertEquals(deploymentOneId,deploymentOne.getId());Deployment deploymentTwo=deployments.get(1);assertEquals("org/activiti/engine/test/repository/two.bpmn20.xml",deploymentTwo.getName());assertEquals(deploymentTwoId,deploymentTwo.getId());deployments=repositoryService.createDeploymentQuery().deploymentNameLike("%one%").orderByDeploymentName().asc().list();assertEquals("org/activiti/engine/test/repository/one.bpmn20.xml",deployments.get(0).getName());assertEquals(1,deployments.size());assertEquals(2,repositoryService.createDeploymentQuery().orderByDeploymentId().asc().list().size());assertEquals(2,repositoryService.createDeploymentQuery().orderByDeploymenTime().asc().list().size());}

}

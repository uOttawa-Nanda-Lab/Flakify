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

  public void testQueryNoCriteria() {
	DeploymentQuery query = repositoryService.createDeploymentQuery();
	assertEquals(2, query.list().size());
	assertEquals(2, query.count());
	try {
		query.singleResult();
		fail();
	} catch (ActivitiException e) {
	}
}

}

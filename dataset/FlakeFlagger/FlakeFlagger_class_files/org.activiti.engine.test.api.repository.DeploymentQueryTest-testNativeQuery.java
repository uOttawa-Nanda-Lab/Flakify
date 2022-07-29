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

  public void testNativeQuery() {
	assertEquals("ACT_RE_DEPLOYMENT", managementService.getTableName(Deployment.class));
	assertEquals("ACT_RE_DEPLOYMENT", managementService.getTableName(DeploymentEntity.class));
	String tableName = managementService.getTableName(Deployment.class);
	String baseQuerySql = "SELECT * FROM " + tableName;
	assertEquals(2, repositoryService.createNativeDeploymentQuery().sql(baseQuerySql).list().size());
	assertEquals(1, repositoryService.createNativeDeploymentQuery().sql(baseQuerySql + " where NAME_ = #{name}")
			.parameter("name", "org/activiti/engine/test/repository/one.bpmn20.xml").list().size());
	assertEquals(2, repositoryService.createNativeDeploymentQuery().sql(baseQuerySql).listPage(0, 2).size());
	assertEquals(1, repositoryService.createNativeDeploymentQuery().sql(baseQuerySql).listPage(1, 3).size());
}

}

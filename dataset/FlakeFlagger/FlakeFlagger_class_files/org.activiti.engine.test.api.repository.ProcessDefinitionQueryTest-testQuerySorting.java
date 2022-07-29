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
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**

 */
public class ProcessDefinitionQueryTest extends PluggableActivitiTestCase {

  private String deploymentOneId;
  private String deploymentTwoId;

  public void testQuerySorting() {

    // asc

    ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionId().asc();
    verifyQueryResults(query, 3);

    query = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().asc();
    verifyQueryResults(query, 3);

    query = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().asc();
    verifyQueryResults(query, 3);

    query = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().asc();
    verifyQueryResults(query, 3);

    // desc

    query = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionId().desc();
    verifyQueryResults(query, 3);

    query = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
    verifyQueryResults(query, 3);

    query = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().desc();
    verifyQueryResults(query, 3);

    query = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionVersion().desc();
    verifyQueryResults(query, 3);

    // Typical use case
    query = repositoryService.createProcessDefinitionQuery().orderByProcessDefinitionKey().asc().orderByProcessDefinitionVersion().desc();
    List<ProcessDefinition> processDefinitions = query.list();
    assertEquals(3, processDefinitions.size());

    assertEquals("one", processDefinitions.get(0).getKey());
    assertEquals(2, processDefinitions.get(0).getVersion());
    assertEquals("one", processDefinitions.get(1).getKey());
    assertEquals(1, processDefinitions.get(1).getVersion());
    assertEquals("two", processDefinitions.get(2).getKey());
    assertEquals(1, processDefinitions.get(2).getVersion());
  }

  private void verifyQueryResults(ProcessDefinitionQuery query, int countExpected) {
    assertEquals(countExpected, query.list().size());
    assertEquals(countExpected, query.count());

    if (countExpected == 1) {
      assertNotNull(query.singleResult());
    } else if (countExpected > 1) {
      verifySingleResultFails(query);
    } else if (countExpected == 0) {
      assertNull(query.singleResult());
    }
  }

  private void verifySingleResultFails(ProcessDefinitionQuery query) {
    try {
      query.singleResult();
      fail();
    } catch (ActivitiException e) {
    }
  }
}

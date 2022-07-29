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

  public void testByLatestVersion() {
	ModelQuery query = repositoryService.createModelQuery().latestVersion().modelKey("someKey");
	Model model = query.singleResult();
	assertNotNull(model);
	Model newVersion = repositoryService.newModel();
	newVersion.setName("my model");
	newVersion.setKey("someKey");
	newVersion.setCategory("test");
	newVersion.setVersion(model.getVersion() + 1);
	repositoryService.saveModel(newVersion);
	model = query.singleResult();
	assertNotNull(model);
	assertTrue(model.getVersion() == 2);
	repositoryService.deleteModel(model.getId());
}
}

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

  public void testKeyAndLatest() throws Exception {
	ModelEntity model1 = null;
	ModelEntity model2 = null;
	try {
		model1 = processEngineConfiguration.getModelEntityManager().create();
		model1.setKey("key1");
		model1.setVersion(1);
		repositoryService.saveModel(model1);
		model2 = processEngineConfiguration.getModelEntityManager().create();
		model2.setKey("key2");
		model2.setVersion(2);
		repositoryService.saveModel(model2);
		Model model = repositoryService.createModelQuery().modelKey("key1").latestVersion().singleResult();
		Assert.assertNotNull(model);
	} finally {
		try {
			if (model1 != null) {
				repositoryService.deleteModel(model1.getId());
			}
			if (model2 != null) {
				repositoryService.deleteModel(model2.getId());
			}
		} catch (Throwable ignore) {
		}
	}
}
}

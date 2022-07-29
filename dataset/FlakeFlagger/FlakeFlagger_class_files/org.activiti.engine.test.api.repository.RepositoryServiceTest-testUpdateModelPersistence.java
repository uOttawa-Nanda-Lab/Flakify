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

  public void testUpdateModelPersistence() throws Exception {
	Model model = repositoryService.newModel();
	assertNotNull(model);
	model.setName("Test model");
	model.setCategory("test");
	model.setMetaInfo("meta");
	repositoryService.saveModel(model);
	assertNotNull(model.getId());
	model = repositoryService.getModel(model.getId());
	assertNotNull(model);
	model.setName("New name");
	model.setCategory("New category");
	model.setMetaInfo("test");
	model.setVersion(2);
	repositoryService.saveModel(model);
	assertNotNull(model.getId());
	repositoryService.addModelEditorSource(model.getId(), "new".getBytes("utf-8"));
	repositoryService.addModelEditorSourceExtra(model.getId(), "new".getBytes("utf-8"));
	model = repositoryService.getModel(model.getId());
	assertEquals("New name", model.getName());
	assertEquals("New category", model.getCategory());
	assertEquals("test", model.getMetaInfo());
	assertNotNull(model.getCreateTime());
	assertEquals(Integer.valueOf(2), model.getVersion());
	assertEquals("new", new String(repositoryService.getModelEditorSource(model.getId()), "utf-8"));
	assertEquals("new", new String(repositoryService.getModelEditorSourceExtra(model.getId()), "utf-8"));
	repositoryService.deleteModel(model.getId());
}

}

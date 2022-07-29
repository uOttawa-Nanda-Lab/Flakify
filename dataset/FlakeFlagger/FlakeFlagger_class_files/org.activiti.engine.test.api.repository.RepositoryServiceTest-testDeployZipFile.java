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

  /**
 * This test was added due to issues with unzip of JDK 7, where the default is changed to UTF8 instead of the platform encoding (which is, in fact, good). However, some platforms do not create UTF8-compatible ZIP files. The tested zip file is created on OS X (non-UTF-8). See https://blogs.oracle.com/xuemingshen/entry/non_utf_8_encoding_in
 */public void testDeployZipFile(){InputStream inputStream=this.getClass().getClassLoader().getResourceAsStream("org/activiti/engine/test/api/repository/test-processes.zip");assertNotNull(inputStream);ZipInputStream zipInputStream=new ZipInputStream(inputStream);assertNotNull(zipInputStream);repositoryService.createDeployment().addZipInputStream(zipInputStream).deploy();assertEquals(6,repositoryService.createProcessDefinitionQuery().count());for (org.activiti.engine.repository.Deployment deployment:repositoryService.createDeploymentQuery().list()){repositoryService.deleteDeployment(deployment.getId(),true);}}

}

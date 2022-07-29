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

package org.activiti.engine.test.history;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.history.HistoricIdentityLink;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

public class HistoricTaskInstanceTest extends PluggableActivitiTestCase {

    @Deployment public void testHistoricIdentityLinksOnTask() throws Exception{ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("historicIdentityLinks");Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();assertNotNull(task);taskService.addUserIdentityLink(task.getId(),"gonzo","customUseridentityLink");assertEquals(4,taskService.getIdentityLinksForTask(task.getId()).size());List<HistoricIdentityLink> historicIdentityLinks=historyService.getHistoricIdentityLinksForTask(task.getId());assertEquals(4,historicIdentityLinks.size());boolean foundCandidateUser=false,foundCandidateGroup=false,foundAssignee=false,foundCustom=false;for (HistoricIdentityLink link:historicIdentityLinks){assertEquals(task.getId(),link.getTaskId());if (link.getGroupId() != null){assertEquals("sales",link.getGroupId());foundCandidateGroup=true;} else {if (link.getType().equals("candidate")){assertEquals("fozzie",link.getUserId());foundCandidateUser=true;} else if (link.getType().equals("assignee")){assertEquals("kermit",link.getUserId());foundAssignee=true;} else if (link.getType().equals("customUseridentityLink")){assertEquals("gonzo",link.getUserId());foundCustom=true;}}}assertTrue(foundAssignee);assertTrue(foundCandidateGroup);assertTrue(foundCandidateUser);assertTrue(foundCustom);taskService.complete(task.getId());assertEquals(4,historyService.getHistoricIdentityLinksForTask(task.getId()).size());historyService.deleteHistoricTaskInstance(task.getId());try {historyService.getHistoricIdentityLinksForTask(task.getId()).size();fail("Exception expected");} catch (ActivitiObjectNotFoundException aonfe){assertEquals(HistoricTaskInstance.class,aonfe.getObjectClass());}}
}

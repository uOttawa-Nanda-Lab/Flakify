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

package org.activiti.engine.test.api.task;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.ActivitiOptimisticLockingException;
import org.activiti.engine.ActivitiTaskAlreadyClaimedException;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableUpdate;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.CommentEntity;
import org.activiti.engine.impl.persistence.entity.HistoricDetailVariableInstanceUpdateEntity;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Event;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import static org.assertj.core.api.Assertions.*;

public class TaskServiceTest extends PluggableActivitiTestCase {

    public void testCustomTaskComments(){if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.ACTIVITY)){Task task=taskService.newTask();task.setOwner("johndoe");taskService.saveTask(task);String taskId=task.getId();Authentication.setAuthenticatedUserId("johndoe");String customType1="Type1";String customType2="Type2";Comment comment=taskService.addComment(taskId,null,"This is a regular comment");Comment customComment1=taskService.addComment(taskId,null,customType1,"This is a custom comment of type Type1");Comment customComment2=taskService.addComment(taskId,null,customType1,"This is another Type1 comment");Comment customComment3=taskService.addComment(taskId,null,customType2,"This is another custom comment. Type2 this time!");assertEquals(CommentEntity.TYPE_COMMENT,comment.getType());assertEquals(customType1,customComment1.getType());assertEquals(customType2,customComment3.getType());assertNotNull(taskService.getComment(comment.getId()));assertNotNull(taskService.getComment(customComment1.getId()));List<Comment> regularComments=taskService.getTaskComments(taskId);assertEquals(1,regularComments.size());assertEquals("This is a regular comment",regularComments.get(0).getFullMessage());List<Event> allComments=taskService.getTaskEvents(taskId);assertEquals(4,allComments.size());List<Comment> type2Comments=taskService.getCommentsByType(customType2);assertEquals(1,type2Comments.size());assertEquals("This is another custom comment. Type2 this time!",type2Comments.get(0).getFullMessage());List<Comment> taskTypeComments=taskService.getTaskComments(taskId,customType1);assertEquals(2,taskTypeComments.size());taskService.deleteTask(taskId,true);}}

    private void checkHistoricVariableUpdateEntity(String variableName,
                                                   String processInstanceId) {
        if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.FULL)) {
            boolean deletedVariableUpdateFound = false;

            List<HistoricDetail> resultSet = historyService.createHistoricDetailQuery().processInstanceId(processInstanceId).list();
            for (HistoricDetail currentHistoricDetail : resultSet) {
                assertTrue(currentHistoricDetail instanceof HistoricDetailVariableInstanceUpdateEntity);
                HistoricDetailVariableInstanceUpdateEntity historicVariableUpdate = (HistoricDetailVariableInstanceUpdateEntity) currentHistoricDetail;

                if (historicVariableUpdate.getName().equals(variableName)) {
                    if (historicVariableUpdate.getValue() == null) {
                        if (deletedVariableUpdateFound) {
                            fail("Mismatch: A HistoricVariableUpdateEntity with a null value already found");
                        } else {
                            deletedVariableUpdateFound = true;
                        }
                    }
                }
            }

            assertTrue(deletedVariableUpdateFound);
        }
    }
}

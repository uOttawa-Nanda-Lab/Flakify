package org.activiti.engine.test.api.runtime;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;

public class TaskInvolvementTest  extends PluggableActivitiTestCase {

    public void testQueryByInvolvedGroupOrUserO(){try {Task adhocTask=taskService.newTask();adhocTask.setAssignee("kermit");adhocTask.setOwner("involvedUser");adhocTask.setPriority(10);taskService.saveTask(adhocTask);List<String> groups=new ArrayList<String>();groups.add("group1");assertEquals(1,taskService.createTaskQuery().or().taskInvolvedUser("involvedUser").taskInvolvedGroupsIn(groups).endOr().count());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){assertEquals(1,historyService.createHistoricTaskInstanceQuery().or().taskInvolvedUser("involvedUser").taskInvolvedGroupsIn(groups).endOr().count());}}  finally {List<Task> allTasks=taskService.createTaskQuery().list();for (Task task:allTasks){if (task.getExecutionId() == null){taskService.deleteTask(task.getId());if (processEngineConfiguration.getHistoryLevel().isAtLeast(HistoryLevel.AUDIT)){historyService.deleteHistoricTaskInstance(task.getId());}}}}}
}

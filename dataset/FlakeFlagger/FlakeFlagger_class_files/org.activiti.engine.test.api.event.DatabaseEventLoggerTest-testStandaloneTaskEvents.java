package org.activiti.engine.test.api.event;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.impl.event.logger.EventLogger;
import org.activiti.engine.impl.event.logger.handler.Fields;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.impl.util.CollectionUtil;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**

 */
public class DatabaseEventLoggerTest extends PluggableActivitiTestCase {

  protected EventLogger databaseEventLogger;

  protected ObjectMapper objectMapper = new ObjectMapper();

  void assertEquals() {
}

  public void testStandaloneTaskEvents() throws JsonParseException,JsonMappingException,IOException{Task task=taskService.newTask();task.setAssignee("kermit");task.setTenantId("myTenant");taskService.saveTask(task);taskService.setAssignee(task.getId(),"gonzo");task=taskService.createTaskQuery().taskId(task.getId()).singleResult();task.setAssignee("kermit");taskService.saveTask(task);List<EventLogEntry> events=managementService.getEventLogEntries(null,null);assertEquals(4,events.size());assertEquals("TASK_CREATED",events.get(0).getType());assertEquals("TASK_ASSIGNED",events.get(1).getType());assertEquals("TASK_ASSIGNED",events.get(2).getType());assertEquals("TASK_ASSIGNED",events.get(3).getType());for (EventLogEntry eventLogEntry:events){Map<String, Object> data=objectMapper.readValue(eventLogEntry.getData(),new TypeReference<HashMap<String, Object>>(){});assertEquals("myTenant",data.get(Fields.TENANT_ID));}taskService.deleteTask(task.getId(),true);for (EventLogEntry eventLogEntry:managementService.getEventLogEntries(null,null)){managementService.deleteEventLogEntry(eventLogEntry.getLogNumber());}}

}

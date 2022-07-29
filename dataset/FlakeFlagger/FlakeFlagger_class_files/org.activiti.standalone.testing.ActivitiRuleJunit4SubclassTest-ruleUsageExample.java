package org.activiti.standalone.testing;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ActivitiRuleJunit4SubclassTest {
@Test @Deployment public void ruleUsageExample(){
  RuntimeService runtimeService=activitiRule.getRuntimeService();
  runtimeService.startProcessInstanceByKey("ruleUsage");
  TaskService taskService=activitiRule.getTaskService();
  Task task=taskService.createTaskQuery().singleResult();
  assertEquals("My Task",task.getName());
  taskService.complete(task.getId());
  assertEquals(0,runtimeService.createProcessInstanceQuery().count());
}

}
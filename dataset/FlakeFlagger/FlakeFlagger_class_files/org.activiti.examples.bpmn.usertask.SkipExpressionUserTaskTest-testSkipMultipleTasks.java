package org.activiti.examples.bpmn.usertask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

public class SkipExpressionUserTaskTest extends PluggableActivitiTestCase {

    @Deployment public void testSkipMultipleTasks(){Map<String, Object> variables=new HashMap<String, Object>();variables.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED",true);variables.put("skip1",true);variables.put("skip2",true);variables.put("skip3",false);runtimeService.startProcessInstanceByKey("skipExpressionUserTask-testSkipMultipleTasks",variables);List<Task> tasks=taskService.createTaskQuery().list();assertEquals(1,tasks.size());assertEquals("Task3",tasks.get(0).getName());}
}

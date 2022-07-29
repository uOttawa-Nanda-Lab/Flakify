package org.activiti.examples.bpmn.usertask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.test.PluggableActivitiTestCase;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.Deployment;

public class SkipExpressionUserTaskTest extends PluggableActivitiTestCase {

    @Deployment public void testWithCandidateGroups(){Map<String, Object> vars=new HashMap<String, Object>();vars.put("_ACTIVITI_SKIP_EXPRESSION_ENABLED",true);vars.put("skip",true);runtimeService.startProcessInstanceByKey("skipExpressionUserTask",vars);assertEquals(0,taskService.createTaskQuery().list().size());}
}

package org.activiti.standalone.cfg;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.activiti.engine.impl.test.ResourceActivitiTestCase;
import org.activiti.engine.task.Task;

/**

 */
public class CustomMybatisMapperTest extends ResourceActivitiTestCase {

  public void testSelectTaskColumns(){for (int i=0;i < 5;i++){Task task=taskService.newTask();task.setName(i + "");taskService.saveTask(task);}CustomSqlExecution<MyTestMapper, List<Map<String, Object>>> customSqlExecution=new AbstractCustomSqlExecution<MyTestMapper, List<Map<String, Object>>>(MyTestMapper.class){public List<Map<String, Object>> execute(MyTestMapper customMapper){return customMapper.selectTasks();}};List<Map<String, Object>> tasks=managementService.executeCustomSql(customSqlExecution);assertEquals(5,tasks.size());for (int i=0;i < 5;i++){Map<String, Object> task=tasks.get(i);assertNotNull(task.get("ID"));assertNotNull(task.get("NAME"));assertNotNull(task.get("CREATETIME"));}for (Task task:taskService.createTaskQuery().list()){taskService.deleteTask(task.getId());historyService.deleteHistoricTaskInstance(task.getId());}}

}

public void testOneTaskProcess(){
  deployStartProcessInstanceAndProfile("process-usertask-01.bpmn20.xml","process-usertask-01",false);
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId());
  stopProfiling();
  assertExecutedCommands("StartProcessInstanceCmd","org.activiti.engine.impl.TaskQueryImpl","CompleteTaskCmd");
  assertDatabaseSelects("StartProcessInstanceCmd","selectLatestProcessDefinitionByKey",1L);
  assertDatabaseInserts("StartProcessInstanceCmd","ExecutionEntityImpl-bulk-with-2",1L,"TaskEntityImpl",1L,"HistoricActivityInstanceEntityImpl-bulk-with-2",1L,"HistoricTaskInstanceEntityImpl",1L,"HistoricProcessInstanceEntityImpl",1L);
  assertNoUpdatesAndDeletes("StartProcessInstanceCmd");
  assertDatabaseSelects("org.activiti.engine.impl.TaskQueryImpl","selectTaskByQueryCriteria",1L);
  assertNoInserts("org.activiti.engine.impl.TaskQueryImpl");
  assertNoUpdates("org.activiti.engine.impl.TaskQueryImpl");
  assertNoDeletes("org.activiti.engine.impl.TaskQueryImpl");
  assertDatabaseSelects("CompleteTaskCmd","selectById org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntityImpl",1L,"selectById org.activiti.engine.impl.persistence.entity.HistoricTaskInstanceEntityImpl",1L,"selectById org.activiti.engine.impl.persistence.entity.TaskEntityImpl",1L,"selectUnfinishedHistoricActivityInstanceExecutionIdAndActivityId",2L,"selectTasksByParentTaskId",1L,"selectIdentityLinksByTask",1L,"selectVariablesByTaskId",1L,"selectExecutionsWithSameRootProcessInstanceId",1L,"selectTasksByExecutionId",1L);
  assertDatabaseInserts("CompleteTaskCmd","HistoricActivityInstanceEntityImpl",1L);
  assertNoUpdates("CompleteTaskCmd");
  assertDatabaseDeletes("CompleteTaskCmd","TaskEntityImpl",1L,"ExecutionEntityImpl",2L);
}

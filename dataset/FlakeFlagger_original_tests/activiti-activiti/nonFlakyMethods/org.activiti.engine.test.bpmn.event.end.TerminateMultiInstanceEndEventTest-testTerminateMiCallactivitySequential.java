@Deployment(resources={"org/activiti/engine/test/bpmn/event/end/TerminateMultiInstanceEndEventTest.testTerminateMiCallactivity-parentProcessSequential.bpmn20.xml","org/activiti/engine/test/bpmn/event/end/TerminateMultiInstanceEndEventTest.testTerminateMiCallactivity-calledProcess.bpmn20.xml"}) public void testTerminateMiCallactivitySequential(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("terminateMiCallActivity");
  Task taskA=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  assertEquals("A",taskA.getName());
  taskService.complete(taskA.getId());
  List<Task> bTasks=taskService.createTaskQuery().taskName("B").list();
  assertEquals(1,bTasks.size());
  taskService.complete(bTasks.get(0).getId());
  List<Task> cTasks=taskService.createTaskQuery().taskName("C").list();
  assertEquals(1,cTasks.size());
  List<Task> dTasks=taskService.createTaskQuery().taskName("D").list();
  assertEquals(1,dTasks.size());
  taskService.complete(cTasks.get(0).getId());
  List<Task> afterMiTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).orderByTaskName().asc().list();
  assertEquals(2,afterMiTasks.size());
  assertEquals("AfterMi",afterMiTasks.get(0).getName());
  assertEquals("Parallel task",afterMiTasks.get(1).getName());
}

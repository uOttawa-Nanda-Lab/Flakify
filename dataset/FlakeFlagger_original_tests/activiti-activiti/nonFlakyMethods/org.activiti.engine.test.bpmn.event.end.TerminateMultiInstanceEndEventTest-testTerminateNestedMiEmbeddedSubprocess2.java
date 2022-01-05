@Deployment(resources="org/activiti/engine/test/bpmn/event/end/TerminateMultiInstanceEndEventTest.testTerminateNestedMiEmbeddedSubprocess.bpmn20.xml") public void testTerminateNestedMiEmbeddedSubprocess2(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("terminateNestedMiEmbeddedSubprocess",CollectionUtil.singletonMap("var","toEnd"));
  List<Task> aTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("A").list();
  assertEquals(12,aTasks.size());
  List<Task> afterInnerMiTasks=taskService.createTaskQuery().processInstanceId(processInstance.getId()).taskName("AfterInnerMi").list();
  assertEquals(12,afterInnerMiTasks.size());
}

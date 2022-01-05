@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testMultipleProcessesStarted(){
  for (int i=0; i < 20; i++) {
    processEngine.getRuntimeService().startProcessInstanceByKey("oneTaskProcess");
  }
  List<Task> tasks=processEngine.getTaskService().createTaskQuery().list();
  assertEquals(20,tasks.size());
}

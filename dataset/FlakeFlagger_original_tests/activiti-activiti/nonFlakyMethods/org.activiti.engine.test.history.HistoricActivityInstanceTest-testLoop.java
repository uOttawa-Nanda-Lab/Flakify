@Deployment public void testLoop(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("historic-activity-loops",CollectionUtil.singletonMap("input",0));
  for (int i=0; i < 10; i++) {
    Task task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    Number inputNumber=(Number)taskService.getVariable(task.getId(),"input");
    int input=inputNumber.intValue();
    assertEquals(i,input);
    taskService.complete(task.getId(),CollectionUtil.singletonMap("input",input + 1));
    task=taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
  }
  List<HistoricActivityInstance> taskActivityInstances=historyService.createHistoricActivityInstanceQuery().activityType("userTask").list();
  assertEquals(10,taskActivityInstances.size());
  for (  HistoricActivityInstance historicActivityInstance : taskActivityInstances) {
    assertNotNull(historicActivityInstance.getStartTime());
    assertNotNull(historicActivityInstance.getEndTime());
  }
  List<HistoricActivityInstance> serviceTaskInstances=historyService.createHistoricActivityInstanceQuery().activityType("serviceTask").list();
  assertEquals(15,serviceTaskInstances.size());
  for (  HistoricActivityInstance historicActivityInstance : serviceTaskInstances) {
    assertNotNull(historicActivityInstance.getStartTime());
    assertNotNull(historicActivityInstance.getEndTime());
  }
}

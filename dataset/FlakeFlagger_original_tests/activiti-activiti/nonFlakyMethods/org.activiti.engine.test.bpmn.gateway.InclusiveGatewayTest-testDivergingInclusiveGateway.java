@Deployment public void testDivergingInclusiveGateway(){
  for (int i=1; i <= 3; i++) {
    ProcessInstance pi=runtimeService.startProcessInstanceByKey("inclusiveGwDiverging",CollectionUtil.singletonMap("input",i));
    List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
    List<String> expectedNames=new ArrayList<String>();
    if (i == 1) {
      expectedNames.add(TASK1_NAME);
    }
    if (i <= 2) {
      expectedNames.add(TASK2_NAME);
    }
    expectedNames.add(TASK3_NAME);
    assertEquals(4 - i,tasks.size());
    for (    Task task : tasks) {
      expectedNames.remove(task.getName());
    }
    assertEquals(0,expectedNames.size());
    runtimeService.deleteProcessInstance(pi.getId(),"testing deletion");
  }
}

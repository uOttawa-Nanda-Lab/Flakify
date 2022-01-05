@Deployment public void testSequentialSubProcessEndEvent(){
  String procId=runtimeService.startProcessInstanceByKey("miSequentialSubprocess").getId();
  TaskQuery query=taskService.createTaskQuery().orderByTaskName().asc();
  for (int i=0; i < 4; i++) {
    List<Task> tasks=query.list();
    assertEquals(1,tasks.size());
    assertEquals("task one",tasks.get(0).getName());
    taskService.complete(tasks.get(0).getId());
    if (i != 3) {
      List<String> activities=runtimeService.getActiveActivityIds(procId);
      assertNotNull(activities);
      assertEquals(2,activities.size());
    }
  }
  assertProcessEnded(procId);
}

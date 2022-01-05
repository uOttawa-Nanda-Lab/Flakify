@Deployment public void testParallelUserTasksBasedOnCollection(){
  List<String> assigneeList=Arrays.asList("kermit","gonzo","mispiggy","fozzie","bubba");
  String procId=runtimeService.startProcessInstanceByKey("miParallelUserTasksBasedOnCollection",CollectionUtil.singletonMap("assigneeList",assigneeList)).getId();
  List<Task> tasks=taskService.createTaskQuery().orderByTaskAssignee().asc().list();
  assertEquals(5,tasks.size());
  assertEquals("bubba",tasks.get(0).getAssignee());
  assertEquals("fozzie",tasks.get(1).getAssignee());
  assertEquals("gonzo",tasks.get(2).getAssignee());
  assertEquals("kermit",tasks.get(3).getAssignee());
  assertEquals("mispiggy",tasks.get(4).getAssignee());
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  taskService.complete(tasks.get(2).getId());
  assertEquals(0,taskService.createTaskQuery().count());
  assertProcessEnded(procId);
}

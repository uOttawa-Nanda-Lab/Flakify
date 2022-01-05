public void testQueryWithIncludeTaskVariableAndTaskCategory(){
  List<Task> tasks=taskService.createTaskQuery().taskAssignee(GONZO).list();
  for (  Task task : tasks) {
    assertNotNull(task.getCategory());
    assertEquals("testCategory",task.getCategory());
  }
  tasks=taskService.createTaskQuery().taskAssignee(GONZO).includeTaskLocalVariables().list();
  for (  Task task : tasks) {
    assertNotNull(task.getCategory());
    assertEquals("testCategory",task.getCategory());
  }
  tasks=taskService.createTaskQuery().taskAssignee(GONZO).includeProcessVariables().list();
  for (  Task task : tasks) {
    assertNotNull(task.getCategory());
    assertEquals("testCategory",task.getCategory());
  }
}

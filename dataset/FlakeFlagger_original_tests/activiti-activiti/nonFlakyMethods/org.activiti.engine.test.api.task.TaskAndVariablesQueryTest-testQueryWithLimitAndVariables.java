public void testQueryWithLimitAndVariables() throws Exception {
  int taskVariablesLimit=2000;
  int expectedNumberOfTasks=103;
  try {
    multipleTaskIds=generateMultipleTestTasks();
    List<Task> tasks=taskService.createTaskQuery().includeProcessVariables().includeTaskLocalVariables().limitTaskVariables(taskVariablesLimit).orderByTaskPriority().asc().listPage(0,200);
    assertEquals(expectedNumberOfTasks,tasks.size());
    tasks=taskService.createTaskQuery().includeProcessVariables().includeTaskLocalVariables().orderByTaskPriority().limitTaskVariables(taskVariablesLimit).asc().listPage(50,100);
    assertEquals(53,tasks.size());
  }
  finally {
    taskService.deleteTasks(multipleTaskIds,true);
  }
}

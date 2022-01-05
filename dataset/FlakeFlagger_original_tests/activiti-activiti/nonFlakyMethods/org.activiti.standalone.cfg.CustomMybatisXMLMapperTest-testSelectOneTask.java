public void testSelectOneTask(){
  for (int i=0; i < 4; i++) {
    createTask(i + "",null,null,0);
  }
  final String taskId=createTask("4",null,null,0);
  CustomTask customTask=managementService.executeCommand(new Command<CustomTask>(){
    @Override public CustomTask execute(    CommandContext commandContext){
      return (CustomTask)commandContext.getDbSqlSession().selectOne("selectOneCustomTask",taskId);
    }
  }
);
  assertEquals("4",customTask.getName());
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(5,tasks.size());
  Task task=taskService.createTaskQuery().taskName("2").singleResult();
  assertEquals("2",task.getName());
  deleteTasks(taskService.createTaskQuery().list());
}

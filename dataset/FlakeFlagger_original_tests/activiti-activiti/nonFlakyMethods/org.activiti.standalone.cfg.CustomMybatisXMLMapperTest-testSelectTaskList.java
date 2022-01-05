public void testSelectTaskList(){
  for (int i=0; i < 5; i++) {
    createTask(i + "",null,null,0);
  }
  List<CustomTask> tasks=managementService.executeCommand(new Command<List<CustomTask>>(){
    @SuppressWarnings("unchecked") @Override public List<CustomTask> execute(    CommandContext commandContext){
      return (List<CustomTask>)commandContext.getDbSqlSession().selectList("selectCustomTaskList");
    }
  }
);
  assertEquals(5,tasks.size());
  deleteCustomTasks(tasks);
}

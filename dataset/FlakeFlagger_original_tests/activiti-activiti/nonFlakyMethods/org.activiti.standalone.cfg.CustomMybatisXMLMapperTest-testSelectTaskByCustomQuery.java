public void testSelectTaskByCustomQuery(){
  for (int i=0; i < 5; i++) {
    createTask(i + "",null,null,0);
  }
  createTask("Owned task","kermit",null,0);
  CustomTask task=new CustomTaskQuery(managementService).taskOwner("kermit").singleResult();
  assertEquals("kermit",task.getOwner());
  List<CustomTask> tasks=new CustomTaskQuery(managementService).list();
  deleteCustomTasks(tasks);
}

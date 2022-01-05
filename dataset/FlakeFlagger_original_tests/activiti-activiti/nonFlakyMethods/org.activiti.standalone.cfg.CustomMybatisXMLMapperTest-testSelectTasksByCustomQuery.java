public void testSelectTasksByCustomQuery(){
  for (int i=0; i < 5; i++) {
    createTask(i + "",null,null,0);
  }
  createTask("Owned task","kermit",null,0);
  List<CustomTask> tasks=new CustomTaskQuery(managementService).unOwned().list();
  assertEquals(5,tasks.size());
  assertEquals(5,new CustomTaskQuery(managementService).unOwned().count());
  tasks=new CustomTaskQuery(managementService).list();
  deleteCustomTasks(tasks);
}

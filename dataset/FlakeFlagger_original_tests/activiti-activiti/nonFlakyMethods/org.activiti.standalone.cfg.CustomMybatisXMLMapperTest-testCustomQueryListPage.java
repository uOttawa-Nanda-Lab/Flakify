public void testCustomQueryListPage(){
  for (int i=0; i < 15; i++) {
    createTask(i + "",null,null,0);
  }
  List<CustomTask> tasks=new CustomTaskQuery(managementService).listPage(0,10);
  assertEquals(10,tasks.size());
  tasks=new CustomTaskQuery(managementService).list();
  deleteCustomTasks(tasks);
}

public void testCustomQueryOrderBy(){
  for (int i=0; i < 5; i++) {
    createTask(i + "",null,null,i * 20);
  }
  List<CustomTask> tasks=new CustomTaskQuery(managementService).orderByTaskPriority().desc().list();
  assertEquals(5,tasks.size());
  for (int i=0, j=4; i < 5; i++, j--) {
    CustomTask task=tasks.get(i);
    assertEquals(j * 20,task.getPriority());
  }
  tasks=new CustomTaskQuery(managementService).orderByTaskPriority().asc().list();
  assertEquals(5,tasks.size());
  for (int i=0; i < 5; i++) {
    CustomTask task=tasks.get(i);
    assertEquals(i * 20,task.getPriority());
  }
  deleteCustomTasks(tasks);
}

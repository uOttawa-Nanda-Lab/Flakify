@Deployment public void testCatchErrorOnParallelMultiInstance(){
  String procId=runtimeService.startProcessInstanceByKey("catchErrorOnParallelMi").getId();
  List<Task> tasks=taskService.createTaskQuery().list();
  assertEquals(5,tasks.size());
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("throwError",false);
  taskService.complete(tasks.get(2).getId(),vars);
  taskService.complete(tasks.get(3).getId(),vars);
  vars.put("throwError",true);
  taskService.complete(tasks.get(1).getId(),vars);
  assertEquals(0,taskService.createTaskQuery().count());
  assertProcessEnded(procId);
}

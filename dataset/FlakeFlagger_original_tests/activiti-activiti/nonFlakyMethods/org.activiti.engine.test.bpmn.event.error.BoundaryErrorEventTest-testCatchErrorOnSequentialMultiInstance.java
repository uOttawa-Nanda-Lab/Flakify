@Deployment public void testCatchErrorOnSequentialMultiInstance(){
  String procId=runtimeService.startProcessInstanceByKey("catchErrorOnSequentialMi").getId();
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("throwError",false);
  Task task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId(),vars);
  vars.put("throwError",true);
  task=taskService.createTaskQuery().singleResult();
  taskService.complete(task.getId(),vars);
  assertProcessEnded(procId);
}

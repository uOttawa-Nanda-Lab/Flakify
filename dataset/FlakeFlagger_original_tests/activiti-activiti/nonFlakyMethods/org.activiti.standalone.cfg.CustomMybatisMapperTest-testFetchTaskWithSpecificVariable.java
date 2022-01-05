public void testFetchTaskWithSpecificVariable(){
  for (int i=0; i < 5; i++) {
    Task task=taskService.newTask();
    task.setName(i + "");
    taskService.saveTask(task);
    taskService.setVariable(task.getId(),"myVar",Long.valueOf(task.getId()) * 2);
    taskService.setVariable(task.getId(),"myVar2","SomeOtherValue");
  }
  CustomSqlExecution<MyTestMapper,List<Map<String,Object>>> customSqlExecution=new AbstractCustomSqlExecution<MyTestMapper,List<Map<String,Object>>>(MyTestMapper.class){
    public List<Map<String,Object>> execute(    MyTestMapper customMapper){
      return customMapper.selectTaskWithSpecificVariable("myVar");
    }
  }
;
  List<Map<String,Object>> results=managementService.executeCustomSql(customSqlExecution);
  assertEquals(5,results.size());
  for (int i=0; i < 5; i++) {
    Map<String,Object> result=results.get(i);
    Long id=Long.valueOf((String)result.get("TASKID"));
    Long variableValue=(Long)result.get("VARIABLEVALUE");
    assertEquals(id * 2,variableValue.longValue());
  }
  for (  Task task : taskService.createTaskQuery().list()) {
    taskService.deleteTask(task.getId());
    historyService.deleteHistoricTaskInstance(task.getId());
  }
}

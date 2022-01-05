@Deployment(resources="org/activiti/engine/test/bpmn/multiinstance/MultiInstanceTest.testSequentialCallActivityWithList.bpmn20.xml") public void testSequentialCallActivityWithList(){
  ArrayList<String> list=new ArrayList<String>();
  list.add("one");
  list.add("two");
  HashMap<String,Object> variables=new HashMap<String,Object>();
  variables.put("list",list);
  String procId=runtimeService.startProcessInstanceByKey("parentProcess",variables).getId();
  Task task1=taskService.createTaskQuery().processVariableValueEquals("element","one").singleResult();
  Task task2=taskService.createTaskQuery().processVariableValueEquals("element","two").singleResult();
  assertNotNull(task1);
  assertNotNull(task2);
  HashMap<String,Object> subVariables=new HashMap<String,Object>();
  subVariables.put("x","y");
  taskService.complete(task1.getId(),subVariables);
  taskService.complete(task2.getId(),subVariables);
  Task task3=taskService.createTaskQuery().processDefinitionKey("midProcess").singleResult();
  assertNotNull(task3);
  taskService.complete(task3.getId(),null);
  Task task4=taskService.createTaskQuery().processDefinitionKey("parentProcess").singleResult();
  assertNotNull(task4);
  taskService.complete(task4.getId(),null);
  assertProcessEnded(procId);
}

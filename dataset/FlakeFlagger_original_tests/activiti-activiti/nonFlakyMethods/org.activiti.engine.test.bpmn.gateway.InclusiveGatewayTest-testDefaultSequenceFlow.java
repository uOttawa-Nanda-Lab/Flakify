@Deployment public void testDefaultSequenceFlow(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("inclusiveGwDefaultSequenceFlow",CollectionUtil.singletonMap("input",1));
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  assertEquals(2,tasks.size());
  Map<String,String> expectedNames=new HashMap<String,String>();
  expectedNames.put("Input is one","Input is one");
  expectedNames.put("Input is three or one","Input is three or one");
  for (  Task t : tasks) {
    expectedNames.remove(t.getName());
  }
  assertEquals(0,expectedNames.size());
  runtimeService.deleteProcessInstance(pi.getId(),null);
  pi=runtimeService.startProcessInstanceByKey("inclusiveGwDefaultSequenceFlow",CollectionUtil.singletonMap("input",3));
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Input is three or one",task.getName());
  pi=runtimeService.startProcessInstanceByKey("inclusiveGwDefaultSequenceFlow",CollectionUtil.singletonMap("input",5));
  task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Default input",task.getName());
}

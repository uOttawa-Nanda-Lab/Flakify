@Deployment public void testNoIdOnSequenceFlow(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("inclusiveNoIdOnSequenceFlow",CollectionUtil.singletonMap("input",3));
  Task task=taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
  assertEquals("Input is more than one",task.getName());
  pi=runtimeService.startProcessInstanceByKey("inclusiveNoIdOnSequenceFlow",CollectionUtil.singletonMap("input",1));
  List<Task> tasks=taskService.createTaskQuery().processInstanceId(pi.getId()).list();
  assertEquals(2,tasks.size());
  Map<String,String> expectedNames=new HashMap<String,String>();
  expectedNames.put("Input is one","Input is one");
  expectedNames.put("Input is more than one","Input is more than one");
  for (  Task t : tasks) {
    expectedNames.remove(t.getName());
  }
  assertEquals(0,expectedNames.size());
}

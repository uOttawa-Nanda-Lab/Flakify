/** 
 * Based on the process and use cases described in http://www.bp-3.com/blogs/2013/09/joins-and-ibm-bpm-diving-deeper/
 */
@Test @org.activiti.engine.test.Deployment(resources="org/activiti/engine/test/api/v6/Activiti6Test.testInclusiveTrickyMerge.bpmn20.xml") public void testInclusiveTrickyMergeEasy(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("trickyInclusiveMerge");
  assertNotNull(processInstance);
  assertFalse(processInstance.isEnded());
  assertEquals(3,taskService.createTaskQuery().count());
  Task taskC=taskService.createTaskQuery().taskName("C").singleResult();
  taskService.complete(taskC.getId());
  List<Task> tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals(3,tasks.size());
  assertEquals("A",tasks.get(0).getName());
  assertEquals("B",tasks.get(1).getName());
  assertEquals("E",tasks.get(2).getName());
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  tasks=taskService.createTaskQuery().orderByTaskName().asc().list();
  assertEquals(2,tasks.size());
  assertEquals("D",tasks.get(0).getName());
  assertEquals("E",tasks.get(1).getName());
  taskService.complete(tasks.get(0).getId());
  taskService.complete(tasks.get(1).getId());
  assertEquals(0,runtimeService.createExecutionQuery().count());
}

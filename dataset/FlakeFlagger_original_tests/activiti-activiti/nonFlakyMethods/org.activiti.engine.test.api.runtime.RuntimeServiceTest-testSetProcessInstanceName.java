@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testSetProcessInstanceName(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  assertNotNull(processInstance);
  assertNull(processInstance.getName());
  runtimeService.setProcessInstanceName(processInstance.getId(),"New name");
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(processInstance);
  assertEquals("New name",processInstance.getName());
  runtimeService.setProcessInstanceName(processInstance.getId(),null);
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(processInstance);
  assertNull(processInstance.getName());
  try {
    runtimeService.setProcessInstanceName("unexisting",null);
    fail("Exception expected");
  }
 catch (  ActivitiObjectNotFoundException aonfe) {
    assertEquals(ProcessInstance.class,aonfe.getObjectClass());
  }
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(processInstance);
  assertNull(processInstance.getName());
  runtimeService.suspendProcessInstanceById(processInstance.getId());
  try {
    runtimeService.setProcessInstanceName(processInstance.getId(),null);
    fail("Exception expected");
  }
 catch (  ActivitiException ae) {
    assertEquals("process instance " + processInstance.getId() + " is suspended, cannot set name",ae.getMessage());
  }
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
  assertNotNull(processInstance);
  assertNull(processInstance.getName());
}

/** 
 * Test confirming fix for ACT-1731
 */
@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testIncludeBinaryVariables() throws Exception {
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess",Collections.singletonMap("binaryVariable",(Object)"It is I, le binary".getBytes()));
  processInstance=runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).includeProcessVariables().singleResult();
  assertNotNull(processInstance);
  byte[] bytes=(byte[])processInstance.getProcessVariables().get("binaryVariable");
  assertEquals("It is I, le binary",new String(bytes));
}

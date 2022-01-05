/** 
 * Test for ACT-1686
 */
@Deployment(resources={"org/activiti/engine/test/api/runtime/oneTaskProcess.bpmn20.xml"}) public void testUserMultipleTimesinvolvedWithProcessInstance(){
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("oneTaskProcess");
  runtimeService.addUserIdentityLink(processInstance.getId(),"kermit","type1");
  runtimeService.addUserIdentityLink(processInstance.getId(),"kermit","type2");
  assertEquals(1L,runtimeService.createProcessInstanceQuery().involvedUser("kermit").count());
}

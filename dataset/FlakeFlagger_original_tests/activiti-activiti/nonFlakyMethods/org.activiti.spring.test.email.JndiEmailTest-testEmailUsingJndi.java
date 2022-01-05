@Deployment(resources={"org/activiti/spring/test/email/EmailTaskUsingJndi.bpmn20.xml"}) public void testEmailUsingJndi(){
  Map<String,Object> variables=new HashMap<String,Object>();
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("EmailJndiProcess",variables);
  assertEquals(0,runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).count());
}

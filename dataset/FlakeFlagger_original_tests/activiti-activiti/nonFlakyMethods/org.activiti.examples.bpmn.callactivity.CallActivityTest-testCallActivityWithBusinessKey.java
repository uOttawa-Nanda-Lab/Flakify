@Deployment(resources={"org/activiti/examples/bpmn/callactivity/mainProcess.bpmn20.xml","org/activiti/examples/bpmn/callactivity/childProcess.bpmn20.xml","org/activiti/examples/bpmn/callactivity/mainProcessBusinessKey.bpmn20.xml","org/activiti/examples/bpmn/callactivity/mainProcessInheritBusinessKey.bpmn20.xml"}) public void testCallActivityWithBusinessKey(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("mainProcess");
  ProcessInstance subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).singleResult();
  assertNull(subProcessInstance.getBusinessKey());
  Map<String,Object> variables=new HashMap<>();
  variables.put("busKey","123");
  pi=runtimeService.startProcessInstanceByKey("mainProcessBusinessKey",variables);
  subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).singleResult();
  assertEquals("123",subProcessInstance.getBusinessKey());
  pi=runtimeService.startProcessInstanceByKey("mainProcessInheritBusinessKey","123");
  subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(pi.getId()).singleResult();
  assertEquals("123",subProcessInstance.getBusinessKey());
}

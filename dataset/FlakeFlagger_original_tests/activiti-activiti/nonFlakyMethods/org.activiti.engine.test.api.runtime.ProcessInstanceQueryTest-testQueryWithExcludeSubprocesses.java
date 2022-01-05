@Deployment(resources={"org/activiti/engine/test/api/runtime/superProcessWithNestedSubProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/nestedSubProcess.bpmn20.xml","org/activiti/engine/test/api/runtime/subProcess.bpmn20.xml"}) public void testQueryWithExcludeSubprocesses(){
  ProcessInstance superProcessInstance=runtimeService.startProcessInstanceByKey("nestedSubProcessQueryTest");
  ProcessInstance subProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(superProcessInstance.getId()).singleResult();
  ProcessInstance nestedSubProcessInstance=runtimeService.createProcessInstanceQuery().superProcessInstanceId(subProcessInstance.getId()).singleResult();
  List<ProcessInstance> instanceList=runtimeService.createProcessInstanceQuery().excludeSubprocesses(true).list();
  assertEquals(6,instanceList.size());
  boolean superProcessFound=false;
  boolean subProcessFound=false;
  boolean nestedSubProcessFound=false;
  for (  ProcessInstance processInstance : instanceList) {
    if (processInstance.getId().equals(superProcessInstance.getId())) {
      superProcessFound=true;
    }
 else     if (processInstance.getId().equals(subProcessInstance.getId())) {
      subProcessFound=true;
    }
 else     if (processInstance.getId().equals(nestedSubProcessInstance.getId())) {
      nestedSubProcessFound=true;
    }
  }
  assertTrue(superProcessFound);
  assertFalse(subProcessFound);
  assertFalse(nestedSubProcessFound);
  instanceList=runtimeService.createProcessInstanceQuery().excludeSubprocesses(false).list();
  assertEquals(8,instanceList.size());
}

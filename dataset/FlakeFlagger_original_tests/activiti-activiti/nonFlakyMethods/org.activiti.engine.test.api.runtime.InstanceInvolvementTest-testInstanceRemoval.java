@Deployment(resources={"org/activiti/engine/test/api/runtime/threeParallelTasks.bpmn20.xml"}) public void testInstanceRemoval(){
  String instanceId=startProcessAsUser("threeParallelTasks","user1");
  assertInvolvement("user1",instanceId);
  runtimeService.deleteProcessInstance(instanceId,"Testing instance removal");
  assertNoInvolvement("user1");
}

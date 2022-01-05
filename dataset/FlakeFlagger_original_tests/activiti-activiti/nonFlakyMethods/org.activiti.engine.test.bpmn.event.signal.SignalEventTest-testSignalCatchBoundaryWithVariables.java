@Deployment(resources={"org/activiti/engine/test/bpmn/event/signal/SignalEventTests.catchAlertSignalBoundaryWithReceiveTask.bpmn20.xml","org/activiti/engine/test/bpmn/event/signal/SignalEventTests.throwAlertSignal.bpmn20.xml"}) public void testSignalCatchBoundaryWithVariables(){
  HashMap<String,Object> variables1=new HashMap<String,Object>();
  variables1.put("processName","catchSignal");
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("catchSignal",variables1);
  HashMap<String,Object> variables2=new HashMap<String,Object>();
  variables2.put("processName","throwSignal");
  runtimeService.startProcessInstanceByKey("throwSignal",variables2);
  assertEquals("throwSignal",runtimeService.getVariable(pi.getId(),"processName"));
}

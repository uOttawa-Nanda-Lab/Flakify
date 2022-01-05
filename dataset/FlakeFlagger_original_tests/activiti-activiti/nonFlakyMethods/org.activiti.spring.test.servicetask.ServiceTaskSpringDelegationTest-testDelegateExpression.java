@Deployment public void testDelegateExpression(){
  ProcessInstance procInst=runtimeService.startProcessInstanceByKey("delegateExpressionToSpringBean");
  assertEquals("Activiti BPMN 2.0 process engine",runtimeService.getVariable(procInst.getId(),"myVar"));
  assertEquals("fieldInjectionWorking",runtimeService.getVariable(procInst.getId(),"fieldInjection"));
}

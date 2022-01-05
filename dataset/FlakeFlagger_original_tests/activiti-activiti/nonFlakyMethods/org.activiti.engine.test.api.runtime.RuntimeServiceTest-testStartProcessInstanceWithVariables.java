@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testStartProcessInstanceWithVariables(){
  Map<String,Object> vars=new HashMap<String,Object>();
  vars.put("basicType",new DummySerializable());
  runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  Task task=taskService.createTaskQuery().includeProcessVariables().singleResult();
  assertNotNull(task.getProcessVariables());
}

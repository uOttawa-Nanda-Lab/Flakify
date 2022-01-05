@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testStartProcessInstanceWithLongStringVariable(){
  Map<String,Object> vars=new HashMap<String,Object>();
  StringBuilder longString=new StringBuilder();
  for (int i=0; i < 4001; i++) {
    longString.append("c");
  }
  vars.put("longString",longString.toString());
  runtimeService.startProcessInstanceByKey("oneTaskProcess",vars);
  Task task=taskService.createTaskQuery().includeProcessVariables().singleResult();
  assertNotNull(task.getProcessVariables());
  assertEquals(longString.toString(),task.getProcessVariables().get("longString"));
}

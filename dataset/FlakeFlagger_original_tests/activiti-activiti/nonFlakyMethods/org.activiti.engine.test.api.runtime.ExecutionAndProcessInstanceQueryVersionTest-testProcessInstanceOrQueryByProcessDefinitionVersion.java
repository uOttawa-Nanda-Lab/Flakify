public void testProcessInstanceOrQueryByProcessDefinitionVersion(){
  assertEquals(1,runtimeService.createProcessInstanceQuery().or().processDefinitionVersion(1).processDefinitionId("undefined").endOr().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().or().processDefinitionVersion(2).processDefinitionId("undefined").endOr().count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().or().processDefinitionVersion(3).processDefinitionId("undefined").endOr().count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().or().processDefinitionVersion(1).processDefinitionId("undefined").endOr().list().size());
  assertEquals(1,runtimeService.createProcessInstanceQuery().or().processDefinitionVersion(2).processDefinitionId("undefined").endOr().list().size());
  assertEquals(0,runtimeService.createProcessInstanceQuery().or().processDefinitionVersion(3).processDefinitionId("undefined").endOr().list().size());
}

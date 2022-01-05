public void testProcessInstanceQueryByProcessDefinitionVersion(){
  assertEquals(1,runtimeService.createProcessInstanceQuery().processDefinitionVersion(1).count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processDefinitionVersion(2).count());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processDefinitionVersion(3).count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processDefinitionVersion(1).count());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processDefinitionVersion(2).list().size());
  assertEquals(0,runtimeService.createProcessInstanceQuery().processDefinitionVersion(3).list().size());
}

public void testQueryByProcessInstanceName(){
  runtimeService.setProcessInstanceName(processInstanceIds.get(0),"new name");
  assertNotNull(runtimeService.createProcessInstanceQuery().processInstanceName("new name").singleResult());
  assertEquals(1,runtimeService.createProcessInstanceQuery().processInstanceName("new name").list().size());
  assertNull(runtimeService.createProcessInstanceQuery().processInstanceName("unexisting").singleResult());
}

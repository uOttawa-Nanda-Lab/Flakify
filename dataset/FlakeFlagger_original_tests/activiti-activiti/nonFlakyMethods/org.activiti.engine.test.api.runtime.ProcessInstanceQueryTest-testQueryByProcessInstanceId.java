public void testQueryByProcessInstanceId(){
  for (  String processInstanceId : processInstanceIds) {
    assertNotNull(runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult());
    assertEquals(1,runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).list().size());
  }
}

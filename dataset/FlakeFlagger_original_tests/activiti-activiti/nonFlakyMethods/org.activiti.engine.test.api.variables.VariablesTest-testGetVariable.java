public void testGetVariable(){
  String value=(String)runtimeService.getVariable(processInstanceId,"stringVar3");
  assertEquals("stringVarValue-3",value);
}

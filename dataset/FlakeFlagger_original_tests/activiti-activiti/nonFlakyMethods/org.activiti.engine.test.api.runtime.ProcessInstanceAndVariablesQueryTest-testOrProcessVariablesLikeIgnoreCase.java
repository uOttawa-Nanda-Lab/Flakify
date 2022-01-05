public void testOrProcessVariablesLikeIgnoreCase(){
  List<ProcessInstance> instanceList=runtimeService.createProcessInstanceQuery().or().variableValueLikeIgnoreCase("test","TES%").variableValueLikeIgnoreCase("test","%XYZ").endOr().list();
  assertEquals(4,instanceList.size());
}

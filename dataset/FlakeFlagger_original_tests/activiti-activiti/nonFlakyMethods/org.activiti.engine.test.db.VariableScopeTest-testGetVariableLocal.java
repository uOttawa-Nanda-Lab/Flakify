@Deployment public void testGetVariableLocal(){
  ProcessInstance pi=runtimeService.startProcessInstanceByKey("getVariableLocal");
  assertNotNull(pi);
  String variableName="Variable-That-Does-Not-Exist";
  Object value=runtimeService.getVariableLocal(pi.getId(),variableName);
  assertNull(value);
}

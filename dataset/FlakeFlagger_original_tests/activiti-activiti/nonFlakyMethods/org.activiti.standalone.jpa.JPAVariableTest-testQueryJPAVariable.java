@Deployment public void testQueryJPAVariable(){
  setupQueryJPAEntity();
  Map<String,Object> variables=new HashMap<String,Object>();
  variables.put("entityToQuery",entityToQuery);
  ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("JPAVariableProcess",variables);
  ProcessInstance result=runtimeService.createProcessInstanceQuery().variableValueEquals("entityToQuery",entityToQuery).singleResult();
  assertNotNull(result);
  assertEquals(result.getId(),processInstance.getId());
  FieldAccessJPAEntity unexistingEntity=new FieldAccessJPAEntity();
  unexistingEntity.setId(8888L);
  result=runtimeService.createProcessInstanceQuery().variableValueEquals("entityToQuery",unexistingEntity).singleResult();
  assertNull(result);
  try {
    runtimeService.createProcessInstanceQuery().variableValueNotEquals("entityToQuery",entityToQuery).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("JPA entity variables can only be used in 'variableValueEquals'",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueGreaterThan("entityToQuery",entityToQuery).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("JPA entity variables can only be used in 'variableValueEquals'",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueGreaterThanOrEqual("entityToQuery",entityToQuery).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("JPA entity variables can only be used in 'variableValueEquals'",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueLessThan("entityToQuery",entityToQuery).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("JPA entity variables can only be used in 'variableValueEquals'",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueLessThanOrEqual("entityToQuery",entityToQuery).singleResult();
    fail("Exception expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("JPA entity variables can only be used in 'variableValueEquals'",ae.getMessage());
  }
}

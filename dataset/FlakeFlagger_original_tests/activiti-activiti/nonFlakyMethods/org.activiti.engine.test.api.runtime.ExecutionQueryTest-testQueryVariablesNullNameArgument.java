public void testQueryVariablesNullNameArgument(){
  try {
    runtimeService.createExecutionQuery().variableValueEquals(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createExecutionQuery().variableValueNotEquals(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createExecutionQuery().variableValueGreaterThan(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createExecutionQuery().variableValueGreaterThanOrEqual(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createExecutionQuery().variableValueLessThan(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createExecutionQuery().variableValueLessThanOrEqual(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createExecutionQuery().variableValueLike(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
}

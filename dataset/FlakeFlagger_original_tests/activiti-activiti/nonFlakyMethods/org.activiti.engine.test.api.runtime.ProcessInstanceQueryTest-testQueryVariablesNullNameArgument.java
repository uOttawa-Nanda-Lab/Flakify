public void testQueryVariablesNullNameArgument(){
  try {
    runtimeService.createProcessInstanceQuery().variableValueEquals(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueNotEquals(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueGreaterThan(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueGreaterThanOrEqual(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueLessThan(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueLessThanOrEqual(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
  try {
    runtimeService.createProcessInstanceQuery().variableValueLike(null,"value");
    fail("Expected exception");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("name is null",ae.getMessage());
  }
}

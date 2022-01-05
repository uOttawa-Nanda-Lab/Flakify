public void testSetProcessDefinitionVersionEmptyArguments(){
  try {
    new SetProcessDefinitionVersionCmd(null,23);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("The process instance id is mandatory, but 'null' has been provided.",ae.getMessage());
  }
  try {
    new SetProcessDefinitionVersionCmd("",23);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("The process instance id is mandatory, but '' has been provided.",ae.getMessage());
  }
  try {
    new SetProcessDefinitionVersionCmd("42",null);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("The process definition version is mandatory, but 'null' has been provided.",ae.getMessage());
  }
  try {
    new SetProcessDefinitionVersionCmd("42",-1);
    fail("ActivitiException expected");
  }
 catch (  ActivitiIllegalArgumentException ae) {
    assertTextPresent("The process definition version must be positive, but '-1' has been provided.",ae.getMessage());
  }
}

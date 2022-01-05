public void testNoScriptProvided(){
  try {
    repositoryService.createDeployment().addClasspathResource("org/activiti/examples/bpmn/scripttask/ScriptTaskTest.testNoScriptProvided.bpmn20.xml").deploy();
  }
 catch (  ActivitiException e) {
    assertTextPresent("No script provided",e.getMessage());
  }
}

public void testResourceRedeploymentAfterProcessDefinitionChange() throws Exception {
  createAppContext(CTX_PATH);
  assertEquals(1,repositoryService.createDeploymentQuery().count());
  ((AbstractXmlApplicationContext)applicationContext).close();
  String filePath="org/activiti/spring/test/autodeployment/autodeploy.a.bpmn20.xml";
  String originalBpmnFileContent=IoUtil.readFileAsString(filePath);
  String updatedBpmnFileContent=originalBpmnFileContent.replace("flow1","fromStartToEndFlow");
  assertTrue(updatedBpmnFileContent.length() > originalBpmnFileContent.length());
  IoUtil.writeStringToFile(updatedBpmnFileContent,filePath);
  waitUntilFileIsWritten(filePath,updatedBpmnFileContent.length());
  try {
    applicationContext=new ClassPathXmlApplicationContext(CTX_NO_DROP_PATH);
    repositoryService=(RepositoryService)applicationContext.getBean("repositoryService");
  }
  finally {
    IoUtil.writeStringToFile(originalBpmnFileContent,filePath);
  }
  assertEquals(2,repositoryService.createDeploymentQuery().count());
  assertEquals(6,repositoryService.createProcessDefinitionQuery().count());
}

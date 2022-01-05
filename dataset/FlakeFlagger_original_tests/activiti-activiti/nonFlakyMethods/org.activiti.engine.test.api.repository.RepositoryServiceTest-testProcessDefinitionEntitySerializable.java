@Deployment(resources={"org/activiti/engine/test/api/oneTaskProcess.bpmn20.xml"}) public void testProcessDefinitionEntitySerializable(){
  String procDefId=repositoryService.createProcessDefinitionQuery().singleResult().getId();
  ProcessDefinition processDefinition=repositoryService.getProcessDefinition(procDefId);
  try {
    ByteArrayOutputStream baos=new ByteArrayOutputStream();
    new ObjectOutputStream(baos).writeObject(processDefinition);
    byte[] bytes=baos.toByteArray();
    assertTrue(bytes.length > 0);
  }
 catch (  IOException e) {
    e.printStackTrace();
    fail();
  }
}

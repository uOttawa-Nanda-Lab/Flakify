@Test public void testNonExecutableProcessDefinitionWarning(){
  BpmnModel bpmnModel=new BpmnModel();
  for (int i=0; i < 3; i++) {
    org.activiti.bpmn.model.Process process=TestProcessUtil.createOneTaskProcess();
    process.setExecutable(false);
    bpmnModel.addProcess(process);
  }
  org.activiti.bpmn.model.Process process=TestProcessUtil.createOneTaskProcess();
  process.setExecutable(true);
  bpmnModel.addProcess(process);
  List<ValidationError> errors=processValidator.validate(bpmnModel);
  Assert.assertEquals(3,errors.size());
  for (  ValidationError error : errors) {
    Assert.assertTrue(error.isWarning());
    Assert.assertNotNull(error.getValidatorSetName());
    Assert.assertNotNull(error.getProblem());
    Assert.assertNotNull(error.getDefaultDescription());
  }
}

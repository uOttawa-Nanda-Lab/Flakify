@Test public void testAllNonExecutableProcesses(){
  BpmnModel bpmnModel=new BpmnModel();
  for (int i=0; i < 5; i++) {
    org.activiti.bpmn.model.Process process=TestProcessUtil.createOneTaskProcess();
    process.setExecutable(false);
    bpmnModel.addProcess(process);
  }
  List<ValidationError> errors=processValidator.validate(bpmnModel);
  Assert.assertEquals(1,errors.size());
}

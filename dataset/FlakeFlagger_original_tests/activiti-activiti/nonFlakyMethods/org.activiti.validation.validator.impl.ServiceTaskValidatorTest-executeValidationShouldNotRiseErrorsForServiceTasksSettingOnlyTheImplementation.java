@Test public void executeValidationShouldNotRiseErrorsForServiceTasksSettingOnlyTheImplementation(){
  Process process=new Process();
  ServiceTask serviceTask=new ServiceTask();
  serviceTask.setImplementation("myImpl");
  process.addFlowElement(serviceTask);
  BpmnModel bpmnModel=new BpmnModel();
  ArrayList<ValidationError> errors=new ArrayList<>();
  validator.executeValidation(bpmnModel,process,errors);
  assertThat(errors).as("No error is expected: the default behavior will be used").isEmpty();
}

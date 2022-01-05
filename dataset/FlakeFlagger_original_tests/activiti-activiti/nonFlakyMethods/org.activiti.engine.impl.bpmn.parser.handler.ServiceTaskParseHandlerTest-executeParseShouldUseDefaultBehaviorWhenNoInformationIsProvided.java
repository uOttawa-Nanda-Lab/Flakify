@Test public void executeParseShouldUseDefaultBehaviorWhenNoInformationIsProvided() throws Exception {
  ServiceTask serviceTask=new ServiceTask();
  ServiceTaskDelegateExpressionActivityBehavior defaultBehavior=mock(ServiceTaskDelegateExpressionActivityBehavior.class);
  given(activityBehaviorFactory.createDefaultServiceTaskBehavior(serviceTask)).willReturn(defaultBehavior);
  serviceTaskParseHandler.executeParse(bpmnParse,serviceTask);
  assertThat(serviceTask.getBehavior()).isEqualTo(defaultBehavior);
}

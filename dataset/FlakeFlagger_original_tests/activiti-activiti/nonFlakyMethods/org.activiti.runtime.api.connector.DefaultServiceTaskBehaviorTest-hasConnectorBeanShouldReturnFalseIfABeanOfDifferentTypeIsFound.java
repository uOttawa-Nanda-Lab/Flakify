@Test public void hasConnectorBeanShouldReturnFalseIfABeanOfDifferentTypeIsFound(){
  String connectorName="connector";
  DelegateExecution execution=buildExecution(connectorName);
  given(context.containsBean(connectorName)).willReturn(true);
  DelegateExecution nonConnectorBean=mock(DelegateExecution.class);
  given(context.getBean(connectorName)).willReturn(nonConnectorBean);
  boolean hasConnectorBean=behavior.hasConnectorBean(execution);
  assertThat(hasConnectorBean).isFalse();
}

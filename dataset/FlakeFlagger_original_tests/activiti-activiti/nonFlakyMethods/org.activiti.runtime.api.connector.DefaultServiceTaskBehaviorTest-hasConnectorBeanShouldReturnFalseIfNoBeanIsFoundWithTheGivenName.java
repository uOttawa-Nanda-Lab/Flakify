@Test public void hasConnectorBeanShouldReturnFalseIfNoBeanIsFoundWithTheGivenName(){
  String connectorName="connector";
  DelegateExecution execution=buildExecution(connectorName);
  given(context.containsBean(connectorName)).willReturn(false);
  boolean hasConnectorBean=behavior.hasConnectorBean(execution);
  assertThat(hasConnectorBean).isFalse();
}

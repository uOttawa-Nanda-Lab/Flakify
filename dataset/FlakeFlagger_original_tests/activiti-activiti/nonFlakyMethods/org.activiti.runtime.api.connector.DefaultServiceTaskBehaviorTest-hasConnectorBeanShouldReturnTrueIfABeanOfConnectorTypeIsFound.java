@Test public void hasConnectorBeanShouldReturnTrueIfABeanOfConnectorTypeIsFound(){
  String connectorName="connector";
  DelegateExecution execution=buildExecution(connectorName);
  given(context.containsBean(connectorName)).willReturn(true);
  given(context.getBean(connectorName)).willReturn(mock(Connector.class));
  boolean hasConnectorBean=behavior.hasConnectorBean(execution);
  assertThat(hasConnectorBean).isTrue();
}

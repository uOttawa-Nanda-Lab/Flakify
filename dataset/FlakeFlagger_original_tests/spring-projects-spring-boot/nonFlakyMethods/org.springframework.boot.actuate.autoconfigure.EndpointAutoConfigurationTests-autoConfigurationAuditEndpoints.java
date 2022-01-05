@Test public void autoConfigurationAuditEndpoints(){
  load(EndpointAutoConfiguration.class,ConditionEvaluationReport.class);
  assertNotNull(this.context.getBean(AutoConfigurationReportEndpoint.class));
}

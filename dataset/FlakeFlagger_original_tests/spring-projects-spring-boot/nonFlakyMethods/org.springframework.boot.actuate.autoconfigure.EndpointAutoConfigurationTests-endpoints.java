@Test public void endpoints() throws Exception {
  load(EndpointAutoConfiguration.class);
  assertNotNull(this.context.getBean(BeansEndpoint.class));
  assertNotNull(this.context.getBean(DumpEndpoint.class));
  assertNotNull(this.context.getBean(EnvironmentEndpoint.class));
  assertNotNull(this.context.getBean(HealthEndpoint.class));
  assertNotNull(this.context.getBean(InfoEndpoint.class));
  assertNotNull(this.context.getBean(MetricsEndpoint.class));
  assertNotNull(this.context.getBean(ShutdownEndpoint.class));
  assertNotNull(this.context.getBean(TraceEndpoint.class));
  assertNotNull(this.context.getBean(RequestMappingEndpoint.class));
}

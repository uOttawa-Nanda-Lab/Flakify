@Test public void testTraceConfiguration() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(AuditAutoConfiguration.class);
  this.context.refresh();
  assertNotNull(this.context.getBean(AuditEventRepository.class));
  assertNotNull(this.context.getBean(AuthenticationAuditListener.class));
  assertNotNull(this.context.getBean(AuthorizationAuditListener.class));
}

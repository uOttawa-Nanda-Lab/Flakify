@Test public void ownAutoRepository() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  this.context.register(Config.class,AuditAutoConfiguration.class);
  this.context.refresh();
  assertThat(this.context.getBean(AuditEventRepository.class),instanceOf(TestAuditEventRepository.class));
}

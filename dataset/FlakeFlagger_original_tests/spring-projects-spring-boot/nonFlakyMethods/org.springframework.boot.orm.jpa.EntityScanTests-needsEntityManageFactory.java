@Test public void needsEntityManageFactory() throws Exception {
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("Unable to configure " + "LocalContainerEntityManagerFactoryBean from @EntityScan, " + "ensure an appropriate bean is registered.");
  this.context=new AnnotationConfigApplicationContext(MissingEntityManager.class);
}

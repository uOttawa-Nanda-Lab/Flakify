@Test public void getSpringBootVersion() throws Exception {
  this.managedDependencies.getSpringBootVersion();
  verify(this.delegate).find("org.springframework.boot","spring-boot");
}

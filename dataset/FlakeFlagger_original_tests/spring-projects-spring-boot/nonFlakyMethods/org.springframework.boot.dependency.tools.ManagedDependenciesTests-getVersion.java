@Test @Deprecated public void getVersion() throws Exception {
  this.managedDependencies.getVersion();
  verify(this.delegate).find("org.springframework.boot","spring-boot");
}

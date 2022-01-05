@Test public void exludes() throws Exception {
  Dependency dependency=this.dependencies.find("org.sample","sample01");
  assertThat(dependency.getExclusions().size(),equalTo(0));
}

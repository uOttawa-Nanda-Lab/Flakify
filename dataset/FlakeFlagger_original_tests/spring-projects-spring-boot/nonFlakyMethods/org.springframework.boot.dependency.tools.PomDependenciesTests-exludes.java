@Test public void exludes() throws Exception {
  Dependency dependency=this.dependencies.find("org.sample","sample01");
  assertThat(dependency.getExclusions().toString(),equalTo("[org.exclude:exclude01]"));
}

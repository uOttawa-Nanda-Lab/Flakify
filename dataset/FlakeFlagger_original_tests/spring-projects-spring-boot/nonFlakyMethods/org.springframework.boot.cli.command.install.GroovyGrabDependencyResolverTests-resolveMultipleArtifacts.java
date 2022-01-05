@Test public void resolveMultipleArtifacts() throws Exception {
  List<File> resolved=this.resolver.resolve(Arrays.asList("junit:junit:4.11","commons-logging:commons-logging:1.1.3"));
  assertThat(resolved,hasSize(3));
  assertThat(getNames(resolved),hasItems("junit-4.11.jar","commons-logging-1.1.3.jar","hamcrest-core-1.3.jar"));
}

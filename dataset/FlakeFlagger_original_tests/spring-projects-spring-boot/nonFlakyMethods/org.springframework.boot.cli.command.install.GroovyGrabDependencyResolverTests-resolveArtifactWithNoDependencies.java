@Test public void resolveArtifactWithNoDependencies() throws Exception {
  List<File> resolved=this.resolver.resolve(Arrays.asList("commons-logging:commons-logging:1.1.3"));
  assertThat(resolved,hasSize(1));
  assertThat(getNames(resolved),hasItems("commons-logging-1.1.3.jar"));
}

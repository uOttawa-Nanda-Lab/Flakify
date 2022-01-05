@Test public void resolveArtifactWithDependencies() throws Exception {
  List<File> resolved=this.resolver.resolve(Arrays.asList("org.springframework:spring-core:4.1.1.RELEASE"));
  assertThat(resolved,hasSize(2));
  assertThat(getNames(resolved),hasItems("commons-logging-1.1.3.jar","spring-core-4.1.1.RELEASE.jar"));
}

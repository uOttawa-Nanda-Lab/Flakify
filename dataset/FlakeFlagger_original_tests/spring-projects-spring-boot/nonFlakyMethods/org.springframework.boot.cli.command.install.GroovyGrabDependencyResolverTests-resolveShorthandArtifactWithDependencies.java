@SuppressWarnings("unchecked") @Test public void resolveShorthandArtifactWithDependencies() throws Exception {
  List<File> resolved=this.resolver.resolve(Arrays.asList("spring-core"));
  assertThat(resolved,hasSize(2));
  assertThat(getNames(resolved),hasItems(startsWith("commons-logging-"),startsWith("spring-core-")));
}

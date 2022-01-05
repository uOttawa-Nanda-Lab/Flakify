@Test public void installNewDependency() throws Exception {
  File foo=createTemporaryFile("foo.jar");
  given(this.resolver.resolve(Arrays.asList("foo"))).willReturn(Arrays.asList(foo));
  this.installer.install(Arrays.asList("foo"));
  assertThat(getNamesOfFilesInLib(),containsInAnyOrder("foo.jar",".installed"));
}

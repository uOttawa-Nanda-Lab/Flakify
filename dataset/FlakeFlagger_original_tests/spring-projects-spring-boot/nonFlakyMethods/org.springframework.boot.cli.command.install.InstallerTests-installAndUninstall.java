@Test public void installAndUninstall() throws Exception {
  File foo=createTemporaryFile("foo.jar");
  given(this.resolver.resolve(Arrays.asList("foo"))).willReturn(Arrays.asList(foo));
  this.installer.install(Arrays.asList("foo"));
  this.installer.uninstall(Arrays.asList("foo"));
  assertThat(getNamesOfFilesInLib(),contains(".installed"));
}

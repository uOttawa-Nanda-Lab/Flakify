@Test public void jarLayout() throws Exception {
  Layout layout=new Layouts.Jar();
  assertThat(layout.getLibraryDestination("lib.jar",LibraryScope.COMPILE),equalTo("lib/"));
  assertThat(layout.getLibraryDestination("lib.jar",LibraryScope.PROVIDED),equalTo("lib/"));
  assertThat(layout.getLibraryDestination("lib.jar",LibraryScope.RUNTIME),equalTo("lib/"));
}

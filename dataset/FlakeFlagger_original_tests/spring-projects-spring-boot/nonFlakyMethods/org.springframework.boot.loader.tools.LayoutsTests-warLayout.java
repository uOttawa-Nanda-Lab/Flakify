@Test public void warLayout() throws Exception {
  Layout layout=new Layouts.War();
  assertThat(layout.getLibraryDestination("lib.jar",LibraryScope.COMPILE),equalTo("WEB-INF/lib/"));
  assertThat(layout.getLibraryDestination("lib.jar",LibraryScope.PROVIDED),equalTo("WEB-INF/lib-provided/"));
  assertThat(layout.getLibraryDestination("lib.jar",LibraryScope.RUNTIME),equalTo("WEB-INF/lib/"));
}

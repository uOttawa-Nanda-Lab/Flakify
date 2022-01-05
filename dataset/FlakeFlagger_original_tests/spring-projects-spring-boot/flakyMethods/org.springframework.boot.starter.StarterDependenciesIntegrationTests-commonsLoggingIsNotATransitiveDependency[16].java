@Test public void commonsLoggingIsNotATransitiveDependency() throws IOException {
  runBuildForTask("checkCommonsLogging");
}

@Test public void oldSpringModulesAreNotTransitiveDependencies() throws IOException {
  runBuildForTask("checkSpring");
}

@Test public void runWithIsolatedClassLoader() throws Exception {
  String output=this.cli.run("classloader-test-app.groovy",SpringCli.class.getName());
  assertThat(output,containsString("HasClasses-false-true-false"));
}

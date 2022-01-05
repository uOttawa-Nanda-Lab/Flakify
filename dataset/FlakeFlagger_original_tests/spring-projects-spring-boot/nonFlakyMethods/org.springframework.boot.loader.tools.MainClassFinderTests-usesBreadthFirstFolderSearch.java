@Test public void usesBreadthFirstFolderSearch() throws Exception {
  this.testJarFile.addClass("a/B.class",ClassWithMainMethod.class);
  this.testJarFile.addClass("a/b/c/E.class",ClassWithMainMethod.class);
  String actual=MainClassFinder.findMainClass(this.testJarFile.getJarSource());
  assertThat(actual,equalTo("a.B"));
}

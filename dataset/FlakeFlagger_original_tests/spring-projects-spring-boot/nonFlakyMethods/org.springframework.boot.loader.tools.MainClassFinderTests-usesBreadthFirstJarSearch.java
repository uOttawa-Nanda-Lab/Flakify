@Test public void usesBreadthFirstJarSearch() throws Exception {
  this.testJarFile.addClass("a/B.class",ClassWithMainMethod.class);
  this.testJarFile.addClass("a/b/c/E.class",ClassWithMainMethod.class);
  String actual=MainClassFinder.findMainClass(this.testJarFile.getJarFile(),"");
  assertThat(actual,equalTo("a.B"));
}

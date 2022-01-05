@Test public void findMainClassInJarSubLocation() throws Exception {
  this.testJarFile.addClass("a/B.class",ClassWithMainMethod.class);
  this.testJarFile.addClass("a/b/c/E.class",ClassWithMainMethod.class);
  String actual=MainClassFinder.findMainClass(this.testJarFile.getJarFile(),"a/");
  assertThat(actual,equalTo("B"));
}

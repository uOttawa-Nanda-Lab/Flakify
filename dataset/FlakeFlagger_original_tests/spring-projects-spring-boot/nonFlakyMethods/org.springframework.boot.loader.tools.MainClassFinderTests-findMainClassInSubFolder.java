@Test public void findMainClassInSubFolder() throws Exception {
  this.testJarFile.addClass("a/b/c/D.class",ClassWithMainMethod.class);
  this.testJarFile.addClass("a/b/c/E.class",ClassWithoutMainMethod.class);
  this.testJarFile.addClass("a/b/F.class",ClassWithoutMainMethod.class);
  String actual=MainClassFinder.findMainClass(this.testJarFile.getJarSource());
  assertThat(actual,equalTo("a.b.c.D"));
}

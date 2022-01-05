@Test public void findMainClassInFolder() throws Exception {
  this.testJarFile.addClass("B.class",ClassWithMainMethod.class);
  this.testJarFile.addClass("A.class",ClassWithoutMainMethod.class);
  String actual=MainClassFinder.findMainClass(this.testJarFile.getJarSource());
  assertThat(actual,equalTo("B"));
}

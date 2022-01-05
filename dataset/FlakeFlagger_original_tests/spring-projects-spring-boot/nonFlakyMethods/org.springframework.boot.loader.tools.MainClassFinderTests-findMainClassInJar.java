@Test public void findMainClassInJar() throws Exception {
  this.testJarFile.addClass("B.class",ClassWithMainMethod.class);
  this.testJarFile.addClass("A.class",ClassWithoutMainMethod.class);
  String actual=MainClassFinder.findMainClass(this.testJarFile.getJarFile(),"");
  assertThat(actual,equalTo("B"));
}

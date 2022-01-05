@Test public void findSingleJarSearch() throws Exception {
  this.testJarFile.addClass("a/B.class",ClassWithMainMethod.class);
  this.testJarFile.addClass("a/b/c/E.class",ClassWithMainMethod.class);
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("Unable to find a single main class " + "from the following candidates [a.B, a.b.c.E]");
  MainClassFinder.findSingleMainClass(this.testJarFile.getJarFile(),"");
}

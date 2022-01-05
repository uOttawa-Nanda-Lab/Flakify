@Test public void doWithFolderMainMethods() throws Exception {
  this.testJarFile.addClass("a/b/c/D.class",ClassWithMainMethod.class);
  this.testJarFile.addClass("a/b/c/E.class",ClassWithoutMainMethod.class);
  this.testJarFile.addClass("a/b/F.class",ClassWithoutMainMethod.class);
  this.testJarFile.addClass("a/b/G.class",ClassWithMainMethod.class);
  ClassNameCollector callback=new ClassNameCollector();
  MainClassFinder.doWithMainClasses(this.testJarFile.getJarSource(),callback);
  assertThat(callback.getClassNames().toString(),equalTo("[a.b.G, a.b.c.D]"));
}

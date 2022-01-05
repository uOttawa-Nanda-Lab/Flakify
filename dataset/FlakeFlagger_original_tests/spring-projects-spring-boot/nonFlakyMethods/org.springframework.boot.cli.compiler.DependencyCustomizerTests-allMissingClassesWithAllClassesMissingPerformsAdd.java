@Test public void allMissingClassesWithAllClassesMissingPerformsAdd(){
  this.dependencyCustomizer.ifAllMissingClasses("does.not.Exist","does.not.exist.Either").add("spring-boot-starter-logging");
  assertEquals(1,this.classNode.getAnnotations(new ClassNode(Grab.class)).size());
}

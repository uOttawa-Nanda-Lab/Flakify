@Test public void allMissingClassesWithMixtureOfClassesDoesNotPerformAdd(){
  this.dependencyCustomizer.ifAllMissingClasses(getClass().getName(),"does.not.Exist").add("spring-boot-starter-logging");
  assertEquals(0,this.classNode.getAnnotations(new ClassNode(Grab.class)).size());
}

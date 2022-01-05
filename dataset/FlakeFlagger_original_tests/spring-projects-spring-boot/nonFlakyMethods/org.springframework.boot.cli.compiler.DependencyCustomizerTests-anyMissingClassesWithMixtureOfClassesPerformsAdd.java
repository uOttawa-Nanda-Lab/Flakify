@Test public void anyMissingClassesWithMixtureOfClassesPerformsAdd(){
  this.dependencyCustomizer.ifAnyMissingClasses(getClass().getName(),"does.not.Exist").add("spring-boot-starter-logging");
  assertEquals(1,this.classNode.getAnnotations(new ClassNode(Grab.class)).size());
}

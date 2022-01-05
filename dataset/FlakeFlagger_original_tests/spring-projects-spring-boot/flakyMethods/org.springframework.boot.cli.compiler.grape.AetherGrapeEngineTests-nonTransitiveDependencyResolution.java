@Test public void nonTransitiveDependencyResolution(){
  Map<String,Object> args=new HashMap<String,Object>();
  this.grapeEngine.grab(args,createDependency("org.springframework","spring-jdbc","3.2.4.RELEASE",false));
  assertEquals(1,this.groovyClassLoader.getURLs().length);
}

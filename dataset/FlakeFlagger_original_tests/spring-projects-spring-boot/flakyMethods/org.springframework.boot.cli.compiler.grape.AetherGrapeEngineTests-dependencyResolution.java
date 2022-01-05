@Test public void dependencyResolution(){
  Map<String,Object> args=new HashMap<String,Object>();
  this.grapeEngine.grab(args,createDependency("org.springframework","spring-jdbc","3.2.4.RELEASE"));
  assertEquals(5,this.groovyClassLoader.getURLs().length);
}

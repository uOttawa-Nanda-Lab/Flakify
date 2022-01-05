@Test public void resolutionWithCustomResolver(){
  Map<String,Object> args=new HashMap<String,Object>();
  this.grapeEngine.addResolver(createResolver("restlet.org","http://maven.restlet.org"));
  this.grapeEngine.grab(args,createDependency("org.restlet","org.restlet","1.1.6"));
  assertEquals(1,this.groovyClassLoader.getURLs().length);
}

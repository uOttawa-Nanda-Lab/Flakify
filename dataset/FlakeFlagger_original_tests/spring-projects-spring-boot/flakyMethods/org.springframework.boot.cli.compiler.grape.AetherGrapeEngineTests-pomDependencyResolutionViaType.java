@Test public void pomDependencyResolutionViaType(){
  Map<String,Object> args=new HashMap<String,Object>();
  Map<String,Object> dependency=createDependency("org.springframework","spring-framework-bom","4.0.5.RELEASE");
  dependency.put("type","pom");
  this.grapeEngine.grab(args,dependency);
  URL[] urls=this.groovyClassLoader.getURLs();
  assertEquals(1,urls.length);
  assertTrue(urls[0].toExternalForm().endsWith(".pom"));
}

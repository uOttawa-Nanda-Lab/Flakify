@Test public void resolutionWithClassifier(){
  Map<String,Object> args=new HashMap<String,Object>();
  Map<String,Object> dependency=createDependency("org.springframework","spring-jdbc","3.2.4.RELEASE",false);
  dependency.put("classifier","sources");
  this.grapeEngine.grab(args,dependency);
  URL[] urls=this.groovyClassLoader.getURLs();
  assertEquals(1,urls.length);
  assertTrue(urls[0].toExternalForm().endsWith("-sources.jar"));
}

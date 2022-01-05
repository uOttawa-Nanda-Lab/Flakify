@Test(expected=IllegalArgumentException.class) public void differingTypeAndExt(){
  Map<String,Object> dependency=createDependency("org.grails","grails-dependencies","2.4.0");
  dependency.put("type","foo");
  dependency.put("ext","bar");
  this.grapeEngine.grab(Collections.emptyMap(),dependency);
}

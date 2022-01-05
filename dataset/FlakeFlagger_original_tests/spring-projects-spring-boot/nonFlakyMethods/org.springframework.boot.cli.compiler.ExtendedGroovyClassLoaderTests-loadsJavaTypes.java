@Test public void loadsJavaTypes() throws Exception {
  this.defaultScopeGroovyClassLoader.loadClass("java.lang.Boolean");
}

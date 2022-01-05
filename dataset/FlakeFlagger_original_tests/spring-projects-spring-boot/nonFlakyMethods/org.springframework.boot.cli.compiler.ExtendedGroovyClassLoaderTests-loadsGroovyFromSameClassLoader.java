@Test public void loadsGroovyFromSameClassLoader() throws Exception {
  Class<?> c1=this.contextClassLoader.loadClass("groovy.lang.Script");
  Class<?> c2=this.defaultScopeGroovyClassLoader.loadClass("groovy.lang.Script");
  assertThat(c1.getClassLoader(),sameInstance(c2.getClassLoader()));
}

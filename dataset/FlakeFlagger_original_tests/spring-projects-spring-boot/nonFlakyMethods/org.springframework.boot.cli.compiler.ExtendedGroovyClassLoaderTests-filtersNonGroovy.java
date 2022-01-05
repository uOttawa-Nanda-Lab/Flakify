@Test public void filtersNonGroovy() throws Exception {
  this.contextClassLoader.loadClass("org.springframework.util.StringUtils");
  this.thrown.expect(ClassNotFoundException.class);
  this.defaultScopeGroovyClassLoader.loadClass("org.springframework.util.StringUtils");
}

@Test public void should_select_classloader_from_osgi() throws Exception {
  ConfigurationContext context=new ConfigurationContext();
  final ClassLoader osgiClassLoader=new ClassLoader(){
    @Override public Class<?> loadClass(    String name) throws ClassNotFoundException {
      return super.loadClass(name);
    }
  }
;
  context.setOSGIClassLoader(osgiClassLoader);
  final ClassLoader classLoader=context.selectClassLoader(CompleteBean.class);
  assertThat(classLoader).isSameAs(osgiClassLoader);
}

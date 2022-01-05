@Test public void should_init_osgi_classloader() throws Exception {
  ConfigMap params=new ConfigMap();
  params.put(OSGI_CLASS_LOADER,this.getClass().getClassLoader());
  final ClassLoader actual=extractor.initOSGIClassLoader(params);
  assertThat(actual).isSameAs(this.getClass().getClassLoader());
}

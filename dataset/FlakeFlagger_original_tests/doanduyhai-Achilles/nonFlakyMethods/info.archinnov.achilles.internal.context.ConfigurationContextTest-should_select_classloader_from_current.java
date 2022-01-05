@Test public void should_select_classloader_from_current() throws Exception {
  ConfigurationContext context=new ConfigurationContext();
  final ClassLoader actual=context.selectClassLoader();
  assertThat(actual).isSameAs(this.getClass().getClassLoader());
}

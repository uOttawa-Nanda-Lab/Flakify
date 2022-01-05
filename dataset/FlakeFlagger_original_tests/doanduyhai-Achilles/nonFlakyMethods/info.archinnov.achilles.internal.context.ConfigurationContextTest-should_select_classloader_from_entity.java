@Test public void should_select_classloader_from_entity() throws Exception {
  ConfigurationContext context=new ConfigurationContext();
  final ClassLoader classLoader=context.selectClassLoader(CompleteBean.class);
  assertThat(classLoader).isSameAs(this.getClass().getClassLoader());
}

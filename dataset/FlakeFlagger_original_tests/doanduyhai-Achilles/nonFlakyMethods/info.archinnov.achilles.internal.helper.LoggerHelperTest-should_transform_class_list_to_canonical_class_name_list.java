@Test public void should_transform_class_list_to_canonical_class_name_list() throws Exception {
  List<Class<?>> classes=new ArrayList<Class<?>>();
  classes.add(Long.class);
  assertThat(Lists.transform(classes,LoggerHelper.fqcnToStringFn)).contains(Long.class.getCanonicalName());
}

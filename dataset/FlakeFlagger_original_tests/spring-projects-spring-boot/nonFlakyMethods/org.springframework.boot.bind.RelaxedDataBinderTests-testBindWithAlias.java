@Test public void testBindWithAlias() throws Exception {
  VanillaTarget target=new VanillaTarget();
  MutablePropertyValues properties=new MutablePropertyValues();
  properties.add("flub","a");
  properties.add("foo","b");
  new RelaxedDataBinder(target).withAlias("flub","fooBaz").bind(properties);
  assertThat(target.getFooBaz(),equalTo("a"));
  assertThat(target.getFoo(),equalTo("b"));
}

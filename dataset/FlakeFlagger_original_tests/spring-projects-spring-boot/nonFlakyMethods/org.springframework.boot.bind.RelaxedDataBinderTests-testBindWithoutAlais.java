@Test public void testBindWithoutAlais() throws Exception {
  VanillaTarget target=new VanillaTarget();
  MutablePropertyValues properties=new MutablePropertyValues();
  properties.add("flub","a");
  properties.add("foo","b");
  new RelaxedDataBinder(target).bind(properties);
  assertThat(target.getFooBaz(),nullValue());
  assertThat(target.getFoo(),equalTo("b"));
}

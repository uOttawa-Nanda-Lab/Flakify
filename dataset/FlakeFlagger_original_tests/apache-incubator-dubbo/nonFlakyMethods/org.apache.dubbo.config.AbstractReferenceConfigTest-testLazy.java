@Test public void testLazy() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setLazy(true);
  assertThat(referenceConfig.getLazy(),is(true));
}

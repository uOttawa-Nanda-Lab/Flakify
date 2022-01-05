@Test public void testInjvm() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setInit(true);
  assertThat(referenceConfig.isInit(),is(true));
}

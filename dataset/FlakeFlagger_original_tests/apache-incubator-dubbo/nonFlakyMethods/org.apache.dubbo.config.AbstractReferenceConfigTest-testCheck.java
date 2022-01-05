@Test public void testCheck() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setCheck(true);
  assertThat(referenceConfig.isCheck(),is(true));
}

@Test public void testMerger() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setMerger("merger");
  assertThat(methodConfig.getMerger(),equalTo("merger"));
}

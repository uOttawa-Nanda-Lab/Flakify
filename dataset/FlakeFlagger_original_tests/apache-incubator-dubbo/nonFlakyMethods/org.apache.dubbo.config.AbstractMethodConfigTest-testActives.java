@Test public void testActives() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setActives(10);
  assertThat(methodConfig.getActives(),equalTo(10));
}

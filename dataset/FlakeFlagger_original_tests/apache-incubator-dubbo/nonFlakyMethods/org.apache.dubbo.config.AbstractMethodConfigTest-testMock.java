@Test public void testMock() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setMock((Boolean)null);
  assertThat(methodConfig.getMock(),isEmptyOrNullString());
  methodConfig.setMock(true);
  assertThat(methodConfig.getMock(),equalTo("true"));
  methodConfig.setMock("return null");
  assertThat(methodConfig.getMock(),equalTo("return null"));
  methodConfig.setMock("mock");
  assertThat(methodConfig.getMock(),equalTo("mock"));
}

@Test public void testValidation() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setValidation("validation");
  assertThat(methodConfig.getValidation(),equalTo("validation"));
}

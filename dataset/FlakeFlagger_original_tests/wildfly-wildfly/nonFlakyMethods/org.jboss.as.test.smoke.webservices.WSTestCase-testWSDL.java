@Test @InSequence(1) public void testWSDL() throws Exception {
  String wsdl=performCall("?wsdl");
  assertNotNull(wsdl);
  assertThat(wsdl,containsString("wsdl:definitions"));
}

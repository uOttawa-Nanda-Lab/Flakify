@Test(expected=UnauthorizedRequestException.class) public void testProxyUnauthorizedRequest() throws Exception {
  processProxyWithResourceId("test");
}

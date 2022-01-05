@Test public void testNormalException(){
  RpcException rpcException=new RpcException();
  Response response=exceptionMapper.toResponse(rpcException);
  assertThat(response,not(nullValue()));
  assertThat(response.getEntity(),instanceOf(String.class));
}

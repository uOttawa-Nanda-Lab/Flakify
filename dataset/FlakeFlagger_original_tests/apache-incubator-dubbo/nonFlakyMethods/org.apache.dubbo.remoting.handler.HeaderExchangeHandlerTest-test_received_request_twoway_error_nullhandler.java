@Test(expected=IllegalArgumentException.class) public void test_received_request_twoway_error_nullhandler() throws RemotingException {
  new HeaderExchangeHandler(null);
}

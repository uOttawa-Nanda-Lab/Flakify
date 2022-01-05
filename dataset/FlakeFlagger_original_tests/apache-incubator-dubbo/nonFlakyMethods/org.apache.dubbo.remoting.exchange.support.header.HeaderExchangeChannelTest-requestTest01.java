@Test(expected=RemotingException.class) public void requestTest01() throws RemotingException {
  header.close(1000);
  Object requestob=new Object();
  header.request(requestob);
}

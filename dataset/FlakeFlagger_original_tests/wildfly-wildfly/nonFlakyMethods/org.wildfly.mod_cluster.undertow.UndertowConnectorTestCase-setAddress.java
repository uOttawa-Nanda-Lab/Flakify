@Test public void setAddress() throws UnknownHostException {
  connector.setAddress(InetAddress.getLocalHost());
  verifyZeroInteractions(this.listener);
}

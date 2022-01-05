@Test public void createMulticastSocket() throws IOException {
  this.createMulticastSocket("known-service","binding");
  this.createMulticastSocket("unknown-service","unknown-service");
}

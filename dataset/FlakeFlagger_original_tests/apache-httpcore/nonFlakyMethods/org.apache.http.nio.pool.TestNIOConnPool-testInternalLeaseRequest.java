@Test public void testInternalLeaseRequest() throws Exception {
  LeaseRequest<String,IOSession,LocalPoolEntry> leaseRequest=new LeaseRequest<String,IOSession,LocalPoolEntry>("somehost",null,0,new BasicFuture<LocalPoolEntry>(null));
  Assert.assertEquals("[somehost][null]",leaseRequest.toString());
}

@Test public void overCapacityRequestSpaceTest() throws TException {
  Assert.assertTrue(mWorkerServiceHandler.requestSpace(1L,WORKER_CAPACITY_BYTES / 10L));
  Assert.assertFalse(mWorkerServiceHandler.requestSpace(1L,WORKER_CAPACITY_BYTES * 10L));
}

@Test public void totalOverCapacityRequestSpaceTest() throws TException {
  Assert.assertTrue(mWorkerServiceHandler.requestSpace(1,WORKER_CAPACITY_BYTES / 2));
  Assert.assertTrue(mWorkerServiceHandler.requestSpace(2,WORKER_CAPACITY_BYTES / 2));
  Assert.assertFalse(mWorkerServiceHandler.requestSpace(1,WORKER_CAPACITY_BYTES / 2));
  Assert.assertFalse(mWorkerServiceHandler.requestSpace(2,WORKER_CAPACITY_BYTES / 2));
}

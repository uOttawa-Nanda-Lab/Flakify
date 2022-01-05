@Test public void returnSpaceTest() throws TException {
  Assert.assertTrue(mWorkerServiceHandler.requestSpace(1,WORKER_CAPACITY_BYTES));
  Assert.assertFalse(mWorkerServiceHandler.requestSpace(1,WORKER_CAPACITY_BYTES));
  mWorkerServiceHandler.returnSpace(1,WORKER_CAPACITY_BYTES);
  Assert.assertTrue(mWorkerServiceHandler.requestSpace(1,WORKER_CAPACITY_BYTES));
  mWorkerServiceHandler.returnSpace(2,WORKER_CAPACITY_BYTES);
  Assert.assertFalse(mWorkerServiceHandler.requestSpace(2,WORKER_CAPACITY_BYTES / 10));
}

@Test(expected=RejectedExecutionException.class) public void testOffer1() throws Exception {
  TaskQueue<Runnable> queue=new TaskQueue<Runnable>(1);
  queue.offer(mock(Runnable.class));
}

@Test public void testConcurrentOperations() throws InterruptedException {
  ActionQueue aq=new ActionQueue();
  String[] hosts=new String[]{"h0","h1","h2","h3","h4","h5","h6","h7","h8","h9"};
  ActionQueueOperation[] enqueOperators=new ActionQueueOperation[threadCount];
  ActionQueueOperation[] dequeOperators=new ActionQueueOperation[threadCount];
  ActionQueueOperation[] dequeAllOperators=new ActionQueueOperation[threadCount];
  for (int i=0; i < threadCount; i++) {
    dequeOperators[i]=new ActionQueueOperation(aq,hosts,ActionQueueOperation.OpType.DEQUEUE);
    Thread t=new Thread(dequeOperators[i]);
    t.start();
  }
  for (int i=0; i < threadCount; i++) {
    enqueOperators[i]=new ActionQueueOperation(aq,hosts,ActionQueueOperation.OpType.ENQUEUE);
    Thread t=new Thread(enqueOperators[i]);
    t.start();
  }
  for (int i=0; i < threadCount; i++) {
    dequeAllOperators[i]=new ActionQueueOperation(aq,hosts,ActionQueueOperation.OpType.DEQUEUEALL);
    Thread t=new Thread(dequeAllOperators[i]);
    t.start();
  }
  Thread.sleep(100);
  for (int i=0; i < threadCount; i++) {
    enqueOperators[i].stop();
  }
  boolean allDequeued=false;
  while (!allDequeued) {
    Thread.sleep(10);
    allDequeued=true;
    for (    String host : hosts) {
      if (aq.size(host) > 0) {
        allDequeued=false;
        break;
      }
    }
  }
  for (int i=0; i < threadCount; i++) {
    dequeOperators[i].stop();
    dequeAllOperators[i].stop();
  }
  for (int h=0; h < hosts.length; h++) {
    long opsEnqueued=0;
    long opsDequeued=0;
    for (int i=0; i < threadCount; i++) {
      opsEnqueued+=enqueOperators[i].getOpCounts()[h];
      opsDequeued+=dequeOperators[i].getOpCounts()[h];
      opsDequeued+=dequeAllOperators[i].getOpCounts()[h];
    }
    assertTrue(opsEnqueued != 0);
    assertEquals(0,aq.size(hosts[h]));
    LOG.info("Host: " + hosts[h] + ", opsEnqueued: "+ opsEnqueued+ ", opsDequeued: "+ opsDequeued);
    assertEquals(opsDequeued,opsEnqueued);
  }
}

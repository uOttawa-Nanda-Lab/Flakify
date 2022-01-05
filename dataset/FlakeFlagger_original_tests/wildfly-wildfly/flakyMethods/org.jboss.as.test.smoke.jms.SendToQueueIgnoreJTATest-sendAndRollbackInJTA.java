/** 
 * JMS message is send using connection factory with transactional = true and JTA rollback Messaging behaves as a part of JTA transaction, message should NOT be delivered.
 */
@Test public void sendAndRollbackInJTA() throws Exception {
  sender.send(false,true);
  latch.await(MAX_WAIT_IN_SECONDS,SECONDS);
  assertEquals(1,latch.getCount());
}

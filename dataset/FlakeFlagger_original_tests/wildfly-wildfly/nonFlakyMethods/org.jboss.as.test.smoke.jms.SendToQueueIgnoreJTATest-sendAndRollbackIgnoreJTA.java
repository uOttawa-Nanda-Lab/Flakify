/** 
 * JMS message is send using connection factory with transactional = false and with rollback of JTA transaction. Message should be still delivered as JTA transaction is ignored.
 */
@Test public void sendAndRollbackIgnoreJTA() throws Exception {
  sender.send(true,true);
  latch.await(MAX_WAIT_IN_SECONDS,SECONDS);
  assertEquals(0,latch.getCount());
}

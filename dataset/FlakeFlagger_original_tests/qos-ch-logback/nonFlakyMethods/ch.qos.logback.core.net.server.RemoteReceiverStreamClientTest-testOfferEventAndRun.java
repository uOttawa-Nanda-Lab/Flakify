@Test public void testOfferEventAndRun() throws Exception {
  client.offer(TEST_EVENT);
  Thread thread=new Thread(client);
  thread.start();
  thread.join(1000);
  assertFalse(thread.isAlive());
  ObjectInputStream ois=new ObjectInputStream(new ByteArrayInputStream(outputStream.toByteArray()));
  assertEquals(TEST_EVENT,ois.readObject());
}

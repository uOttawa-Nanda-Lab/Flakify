/** 
 * Test method for  {@link MulticastRegistry#destroy()}
 */
@Test public void testDestroy(){
  MulticastSocket socket=registry.getMulticastSocket();
  assertFalse(socket.isClosed());
  registry.destroy();
  socket=registry.getMulticastSocket();
  assertTrue(socket.isClosed());
}

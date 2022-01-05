@Test public void testGet(){
  FramedataImpl1 binary=FramedataImpl1.get(Opcode.BINARY);
  assertEquals("Frame must be binary",true,binary instanceof BinaryFrame);
  FramedataImpl1 text=FramedataImpl1.get(Opcode.TEXT);
  assertEquals("Frame must be text",true,text instanceof TextFrame);
  FramedataImpl1 closing=FramedataImpl1.get(Opcode.CLOSING);
  assertEquals("Frame must be closing",true,closing instanceof CloseFrame);
  FramedataImpl1 continuous=FramedataImpl1.get(Opcode.CONTINUOUS);
  assertEquals("Frame must be continuous",true,continuous instanceof ContinuousFrame);
  FramedataImpl1 ping=FramedataImpl1.get(Opcode.PING);
  assertEquals("Frame must be ping",true,ping instanceof PingFrame);
  FramedataImpl1 pong=FramedataImpl1.get(Opcode.PONG);
  assertEquals("Frame must be pong",true,pong instanceof PongFrame);
  try {
    FramedataImpl1.get(null);
    fail("IllegalArgumentException should be thrown");
  }
 catch (  IllegalArgumentException e) {
  }
}

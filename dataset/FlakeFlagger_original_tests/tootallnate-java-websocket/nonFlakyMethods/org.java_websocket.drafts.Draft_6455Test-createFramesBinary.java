@Test public void createFramesBinary() throws Exception {
  Draft_6455 draft_6455=new Draft_6455();
  BinaryFrame curframe=new BinaryFrame();
  ByteBuffer test0=ByteBuffer.wrap("Test0".getBytes());
  curframe.setPayload(test0);
  curframe.setTransferemasked(false);
  List<Framedata> createdFrame=draft_6455.createFrames(test0,false);
  assertEquals(1,createdFrame.size());
  assertEquals(curframe,createdFrame.get(0));
  curframe=new BinaryFrame();
  ByteBuffer test1=ByteBuffer.wrap("Test1".getBytes());
  curframe.setPayload(test1);
  curframe.setTransferemasked(true);
  createdFrame=draft_6455.createFrames(test1,true);
  assertEquals(1,createdFrame.size());
  assertEquals(curframe,createdFrame.get(0));
}

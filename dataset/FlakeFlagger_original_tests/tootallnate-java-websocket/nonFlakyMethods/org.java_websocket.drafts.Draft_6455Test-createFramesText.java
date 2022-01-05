@Test public void createFramesText() throws Exception {
  Draft_6455 draft_6455=new Draft_6455();
  TextFrame curframe=new TextFrame();
  curframe.setPayload(ByteBuffer.wrap(Charsetfunctions.utf8Bytes("Test0")));
  curframe.setTransferemasked(false);
  List<Framedata> createdFrame=draft_6455.createFrames("Test0",false);
  assertEquals(1,createdFrame.size());
  assertEquals(curframe,createdFrame.get(0));
  curframe=new TextFrame();
  curframe.setPayload(ByteBuffer.wrap(Charsetfunctions.utf8Bytes("Test0")));
  curframe.setTransferemasked(true);
  createdFrame=draft_6455.createFrames("Test0",true);
  assertEquals(1,createdFrame.size());
  assertEquals(curframe,createdFrame.get(0));
}

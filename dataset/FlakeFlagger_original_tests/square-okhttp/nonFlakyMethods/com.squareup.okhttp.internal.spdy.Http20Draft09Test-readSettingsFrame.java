@Test public void readSettingsFrame() throws IOException {
  OkBuffer frame=new OkBuffer();
  final int reducedTableSizeBytes=16;
  frame.writeShort(16);
  frame.writeByte(Http20Draft09.TYPE_SETTINGS);
  frame.writeByte(0);
  frame.writeInt(0 & 0x7fffffff);
  frame.writeInt(Settings.HEADER_TABLE_SIZE & 0xffffff);
  frame.writeInt(reducedTableSizeBytes);
  frame.writeInt(Settings.ENABLE_PUSH & 0xffffff);
  frame.writeInt(0);
  final Http20Draft09.Reader fr=new Http20Draft09.Reader(frame,4096,false);
  fr.nextFrame(new BaseTestHandler(){
    @Override public void settings(    boolean clearPrevious,    Settings settings){
      assertFalse(clearPrevious);
      assertEquals(reducedTableSizeBytes,settings.getHeaderTableSize());
      assertEquals(false,settings.getEnablePush(true));
    }
  }
);
}

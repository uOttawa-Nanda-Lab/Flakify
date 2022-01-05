public void testSeekTo() throws Exception {
  Path p=makeNewFile();
  HFile.Reader reader=new HFile.Reader(fs,p,null,false);
  reader.loadFileInfo();
  assertEquals(2,reader.blockIndex.count);
  HFileScanner scanner=reader.getScanner(false,true);
  assertEquals(-1,scanner.seekTo(Bytes.toBytes("a")));
  assertEquals(1,scanner.seekTo(Bytes.toBytes("d")));
  assertEquals("c",scanner.getKeyString());
  assertEquals(1,scanner.seekTo(Bytes.toBytes("h")));
  assertEquals("g",scanner.getKeyString());
  assertEquals(1,scanner.seekTo(Bytes.toBytes("l")));
  assertEquals("k",scanner.getKeyString());
}

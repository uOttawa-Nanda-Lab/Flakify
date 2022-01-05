public void testBlockContainingKey() throws Exception {
  Path p=makeNewFile();
  HFile.Reader reader=new HFile.Reader(fs,p,null,false);
  reader.loadFileInfo();
  System.out.println(reader.blockIndex.toString());
  assertEquals(-1,reader.blockIndex.blockContainingKey(Bytes.toBytes("a"),0,1));
  assertEquals(0,reader.blockIndex.blockContainingKey(Bytes.toBytes("c"),0,1));
  assertEquals(0,reader.blockIndex.blockContainingKey(Bytes.toBytes("d"),0,1));
  assertEquals(0,reader.blockIndex.blockContainingKey(Bytes.toBytes("e"),0,1));
  assertEquals(0,reader.blockIndex.blockContainingKey(Bytes.toBytes("g"),0,1));
  assertEquals(0,reader.blockIndex.blockContainingKey(Bytes.toBytes("h"),0,1));
  assertEquals(1,reader.blockIndex.blockContainingKey(Bytes.toBytes("i"),0,1));
  assertEquals(1,reader.blockIndex.blockContainingKey(Bytes.toBytes("j"),0,1));
  assertEquals(1,reader.blockIndex.blockContainingKey(Bytes.toBytes("k"),0,1));
  assertEquals(1,reader.blockIndex.blockContainingKey(Bytes.toBytes("l"),0,1));
}

public void testSeekBefore() throws Exception {
  Path p=makeNewFile();
  HFile.Reader reader=new HFile.Reader(fs,p,null,false);
  reader.loadFileInfo();
  HFileScanner scanner=reader.getScanner(false,true);
  assertEquals(false,scanner.seekBefore(Bytes.toBytes("a")));
  assertEquals(false,scanner.seekBefore(Bytes.toBytes("c")));
  assertEquals(true,scanner.seekBefore(Bytes.toBytes("d")));
  assertEquals("c",scanner.getKeyString());
  assertEquals(true,scanner.seekBefore(Bytes.toBytes("e")));
  assertEquals("c",scanner.getKeyString());
  assertEquals(true,scanner.seekBefore(Bytes.toBytes("f")));
  assertEquals("e",scanner.getKeyString());
  assertEquals(true,scanner.seekBefore(Bytes.toBytes("g")));
  assertEquals("e",scanner.getKeyString());
  assertEquals(true,scanner.seekBefore(Bytes.toBytes("h")));
  assertEquals("g",scanner.getKeyString());
  assertEquals(true,scanner.seekBefore(Bytes.toBytes("i")));
  assertEquals("g",scanner.getKeyString());
  assertEquals(true,scanner.seekBefore(Bytes.toBytes("j")));
  assertEquals("i",scanner.getKeyString());
  assertEquals(true,scanner.seekBefore(Bytes.toBytes("k")));
  assertEquals("i",scanner.getKeyString());
  assertEquals(true,scanner.seekBefore(Bytes.toBytes("l")));
  assertEquals("k",scanner.getKeyString());
}

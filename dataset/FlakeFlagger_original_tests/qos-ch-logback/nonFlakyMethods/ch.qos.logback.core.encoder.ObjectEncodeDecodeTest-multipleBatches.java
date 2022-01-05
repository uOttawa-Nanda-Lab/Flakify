@Test public void multipleBatches() throws IOException {
  File file=new File(randomOutputDir + "m.lbo");
  List<String> witness=new ArrayList<String>();
  for (int i=0; i < 100 * 10; i++) {
    witness.add("hello" + i);
  }
  encodeList(file,witness);
  List<String> back=decodeList(file);
  assertEquals(witness,back);
}

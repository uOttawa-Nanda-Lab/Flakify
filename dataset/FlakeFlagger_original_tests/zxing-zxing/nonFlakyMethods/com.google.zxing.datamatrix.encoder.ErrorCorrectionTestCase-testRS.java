@Test public void testRS(){
  char[] cw={142,164,186};
  SymbolInfo symbolInfo=SymbolInfo.lookup(3);
  CharSequence s=ErrorCorrection.encodeECC200(String.valueOf(cw),symbolInfo);
  assertEquals("142 164 186 114 25 5 88 102",HighLevelEncodeTestCase.visualize(s));
  cw=new char[]{66,129,70};
  s=ErrorCorrection.encodeECC200(String.valueOf(cw),symbolInfo);
  assertEquals("66 129 70 138 234 82 82 95",HighLevelEncodeTestCase.visualize(s));
}

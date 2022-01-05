@Test public void testVeryLongLoggerName(){
  ClassicConverter converter=new LoggerConverter();
  this.optionList.add("5");
  converter.setOptionList(this.optionList);
  converter.start();
  StringBuilder buf=new StringBuilder();
  char c='a';
  int extraParts=3;
  int totalParts=ClassicConstants.MAX_DOTS + extraParts;
  StringBuilder loggerNameBuf=new StringBuilder();
  StringBuilder witness=new StringBuilder();
  for (int i=0; i < totalParts; i++) {
    loggerNameBuf.append(c).append(c).append(c);
    if (i < ClassicConstants.MAX_DOTS) {
      witness.append(c);
    }
 else {
      witness.append(c).append(c).append(c);
    }
    loggerNameBuf.append('.');
    witness.append('.');
  }
  loggerNameBuf.append("zzzzzz");
  witness.append("zzzzzz");
  le.setLoggerName(loggerNameBuf.toString());
  converter.write(buf,le);
  assertEquals(witness.toString(),buf.toString());
}

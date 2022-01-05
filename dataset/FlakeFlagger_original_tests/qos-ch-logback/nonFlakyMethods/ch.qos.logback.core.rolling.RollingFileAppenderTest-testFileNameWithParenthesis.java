@Test public void testFileNameWithParenthesis(){
  rfa.setContext(context);
  tbrp.setFileNamePattern(randomOutputDir + "program(x86)/toto-%d.log");
  tbrp.start();
  rfa.setRollingPolicy(tbrp);
  rfa.start();
  rfa.doAppend("hello");
}

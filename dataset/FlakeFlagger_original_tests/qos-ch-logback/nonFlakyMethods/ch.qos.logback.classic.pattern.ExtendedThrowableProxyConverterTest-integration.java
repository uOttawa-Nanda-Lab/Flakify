@Test public void integration(){
  PatternLayout pl=new PatternLayout();
  pl.setContext(lc);
  pl.setPattern("%m%n");
  pl.start();
  ILoggingEvent e=createLoggingEvent(new Exception("x"));
  String res=pl.doLayout(e);
  Pattern p=Pattern.compile(" \\[junit.*\\]");
  Matcher m=p.matcher(res);
  int i=0;
  while (m.find()) {
    i++;
  }
  assertTrue(i + " should be larger than 5",i > 5);
}

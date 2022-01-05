@Test public void integration(){
  PatternLayout pl=new PatternLayout();
  pl.setContext(context);
  pl.setPattern("%m%rEx%n");
  pl.start();
  ILoggingEvent e=createLoggingEvent(new Exception("x"));
  String result=pl.doLayout(e);
  Pattern p=Pattern.compile(" \\[junit.*\\]");
  Matcher m=p.matcher(result);
  int i=0;
  while (m.find()) {
    i++;
  }
  assertThat(i).isGreaterThan(5);
}

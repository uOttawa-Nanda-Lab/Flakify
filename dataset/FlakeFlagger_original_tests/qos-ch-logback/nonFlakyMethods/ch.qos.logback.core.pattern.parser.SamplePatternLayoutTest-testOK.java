@Test public void testOK(){
  PatternLayoutBase<Object> plb=getPatternLayoutBase();
  Context context=new ContextBase();
  plb.setContext(context);
  plb.setPattern("x%OTT");
  plb.start();
  String s=plb.doLayout(new Object());
  assertEquals("x123",s);
}

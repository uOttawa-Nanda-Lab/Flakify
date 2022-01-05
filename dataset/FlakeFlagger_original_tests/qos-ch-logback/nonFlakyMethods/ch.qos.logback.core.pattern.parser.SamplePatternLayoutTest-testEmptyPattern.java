@Test public void testEmptyPattern(){
  PatternLayoutBase<E> plb=getPatternLayoutBase();
  Context context=new ContextBase();
  plb.setContext(context);
  plb.setPattern("");
  plb.start();
  String s=plb.doLayout(getEventObject());
  assertEquals("",s);
  StatusChecker checker=new StatusChecker(context.getStatusManager());
  checker.assertContainsMatch("Empty or null pattern.");
}

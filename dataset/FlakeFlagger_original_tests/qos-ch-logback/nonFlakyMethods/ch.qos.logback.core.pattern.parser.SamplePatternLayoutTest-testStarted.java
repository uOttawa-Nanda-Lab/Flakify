@Test public void testStarted(){
  PatternLayoutBase<E> plb=getPatternLayoutBase();
  Context context=new ContextBase();
  plb.setContext(context);
  String s=plb.doLayout(getEventObject());
  assertEquals("",s);
  StatusManager sm=context.getStatusManager();
  StatusPrinter.print(sm);
}

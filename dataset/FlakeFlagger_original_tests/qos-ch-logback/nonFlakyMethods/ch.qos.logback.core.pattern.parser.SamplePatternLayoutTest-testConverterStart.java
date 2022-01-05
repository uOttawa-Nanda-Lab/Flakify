/** 
 * This test checks that the pattern layout implementation starts its converters. ExceptionalConverter throws an exception if it's convert method is called before being started.
 */
@Test public void testConverterStart(){
  PatternLayoutBase<E> plb=getPatternLayoutBase();
  plb.setContext(getContext());
  plb.getInstanceConverterMap().put("EX",ExceptionalConverter.class.getName());
  plb.setPattern("%EX");
  plb.start();
  String result=plb.doLayout(getEventObject());
  assertFalse(result.contains("%PARSER_ERROR_EX"));
}

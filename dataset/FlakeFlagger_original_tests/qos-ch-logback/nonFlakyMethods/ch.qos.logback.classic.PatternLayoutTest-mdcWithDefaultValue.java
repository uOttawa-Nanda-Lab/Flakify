@Test public void mdcWithDefaultValue(){
  String pattern="%msg %mdc{foo} %mdc{bar:-[null]}";
  pl.setPattern(OptionHelper.substVars(pattern,lc));
  pl.start();
  MDC.put("foo","foo");
  try {
    String val=pl.doLayout(getEventObject());
    assertEquals("Some message foo [null]",val);
  }
  finally {
    MDC.remove("foo");
  }
}

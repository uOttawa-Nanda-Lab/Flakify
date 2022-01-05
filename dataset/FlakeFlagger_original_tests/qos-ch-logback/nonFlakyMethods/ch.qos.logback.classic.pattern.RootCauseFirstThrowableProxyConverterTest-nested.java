@Test public void nested(){
  Throwable nestedException=makeNestedException(2);
  nestedException.printStackTrace(printWriter);
  ILoggingEvent le=createLoggingEvent(nestedException);
  String result=converter.convert(le);
  assertThat(result).startsWith("java.lang.Exception: nesting level=0");
  assertThat(positionOf("nesting level=0").in(result)).isLessThan(positionOf("nesting level =1").in(result));
  assertThat(positionOf("nesting level =1").in(result)).isLessThan(positionOf("nesting level =2").in(result));
}

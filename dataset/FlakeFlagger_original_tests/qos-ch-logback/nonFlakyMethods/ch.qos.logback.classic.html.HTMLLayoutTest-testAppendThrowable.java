@Test public void testAppendThrowable() throws Exception {
  StringBuilder buf=new StringBuilder();
  DummyThrowableProxy tp=new DummyThrowableProxy();
  tp.setClassName("test1");
  tp.setMessage("msg1");
  StackTraceElement ste1=new StackTraceElement("c1","m1","f1",1);
  StackTraceElement ste2=new StackTraceElement("c2","m2","f2",2);
  StackTraceElementProxy[] stepArray={new StackTraceElementProxy(ste1),new StackTraceElementProxy(ste2)};
  tp.setStackTraceElementProxyArray(stepArray);
  DefaultThrowableRenderer renderer=(DefaultThrowableRenderer)layout.getThrowableRenderer();
  renderer.render(buf,tp);
  System.out.println(buf.toString());
  String[] result=buf.toString().split(CoreConstants.LINE_SEPARATOR);
  System.out.println(result[0]);
  assertEquals("test1: msg1",result[0]);
  assertEquals(DefaultThrowableRenderer.TRACE_PREFIX + "at c1.m1(f1:1)",result[1]);
}

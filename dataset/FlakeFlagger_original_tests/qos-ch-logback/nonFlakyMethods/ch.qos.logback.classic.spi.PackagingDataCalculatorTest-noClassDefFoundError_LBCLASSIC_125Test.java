@Test public void noClassDefFoundError_LBCLASSIC_125Test() throws MalformedURLException {
  ClassLoader cl=(URLClassLoader)makeBogusClassLoader();
  Thread.currentThread().setContextClassLoader(cl);
  Throwable t=new Throwable("x");
  ThrowableProxy tp=new ThrowableProxy(t);
  StackTraceElementProxy[] stepArray=tp.getStackTraceElementProxyArray();
  StackTraceElement bogusSTE=new StackTraceElement("com.Bogus","myMethod","myFile",12);
  stepArray[0]=new StackTraceElementProxy(bogusSTE);
  PackagingDataCalculator pdc=tp.getPackagingDataCalculator();
  pdc.calculate(tp);
}

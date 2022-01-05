@Test public void somekeReplace(){
  pl.setPattern("%replace(a1234b){'\\d{4}', 'XXXX'}");
  pl.start();
  StatusPrinter.print(lc);
  String val=pl.doLayout(getEventObject());
  assertEquals("aXXXXb",val);
}

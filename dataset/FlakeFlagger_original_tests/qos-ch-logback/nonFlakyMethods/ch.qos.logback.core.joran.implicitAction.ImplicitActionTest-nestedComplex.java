@Test public void nestedComplex() throws Exception {
  try {
    simpleConfigurator.doConfigure(IMPLCIT_DIR + "nestedComplex.xml");
    verifyFruit();
  }
 catch (  Exception je) {
    StatusPrinter.print(fruitContext);
    throw je;
  }
}

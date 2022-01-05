@Test public void recursiveLogbackInitialization(){
  Logger logger=LoggerFactory.getLogger("RecursiveInitializationTest" + diff);
  logger.info("RecursiveInitializationTest");
  LoggerContext loggerContext=(LoggerContext)LoggerFactory.getILoggerFactory();
  StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
  StatusChecker statusChecker=new StatusChecker(loggerContext);
  assertEquals("Was expecting no errors",Status.WARN,statusChecker.getHighestLevel(0));
}

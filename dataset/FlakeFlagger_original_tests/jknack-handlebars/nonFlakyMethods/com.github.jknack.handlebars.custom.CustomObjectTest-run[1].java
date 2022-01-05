@Test public void run() throws HandlebarsException, IOException {
  if (!skip(spec)) {
    run(alter(spec));
  }
 else {
    Report report=new Report();
    report.header(80);
    report.append("Skipping Test: %s",spec.id());
    report.header(80);
    throw new SkipTestException(spec.name());
  }
}

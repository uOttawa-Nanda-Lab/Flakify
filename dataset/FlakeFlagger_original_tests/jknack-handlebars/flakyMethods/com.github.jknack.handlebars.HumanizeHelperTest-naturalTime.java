@Test public void naturalTime() throws IOException, InterruptedException {
  Calendar calendar=Calendar.getInstance();
  Date now=calendar.getTime();
  Thread.sleep(1000);
  assertEquals("moments ago",handlebars.compile("{{naturalTime this locale=\"en_US\"}}").apply(now));
}

@Test public void naturalDay() throws IOException {
  Calendar calendar=Calendar.getInstance();
  Date now=calendar.getTime();
  calendar.add(Calendar.HOUR,-24);
  Date yesterday=calendar.getTime();
  calendar.add(Calendar.HOUR,24 * 2);
  Date tomorrow=calendar.getTime();
  assertEquals("yesterday",handlebars.compile("{{naturalDay this locale=\"en_US\"}}").apply(yesterday));
  assertEquals("today",handlebars.compile("{{naturalDay this locale=\"en_US\"}}").apply(now));
  assertEquals("tomorrow",handlebars.compile("{{naturalDay this locale=\"en_US\"}}").apply(tomorrow));
}

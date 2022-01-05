@Test public void pluralize() throws IOException {
  assertEquals("There are no files on disk.",handlebars.compile("{{pluralize this 0 \"disk\" locale=\"en_US\"}}").apply("There {0} on {1}.::are no files::is one file::are {0} files"));
  assertEquals("There is one file on disk.",handlebars.compile("{{pluralize this 1 \"disk\" locale=\"en_US\"}}").apply("There {0} on {1}.::are no files::is one file::are {0} files"));
  assertEquals("There are 1,000 files on disk.",handlebars.compile("{{pluralize this 1000 \"disk\" locale=\"en_US\"}}").apply("There {0} on {1}.::are no files::is one file::are {0} files"));
}

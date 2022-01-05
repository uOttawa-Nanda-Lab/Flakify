@Test public void precompile() throws IOException {
  String js=handlebars.compile("{{precompile \"input\" wrapper=\"" + wrapper + "\"}}").apply("Handlebar.js");
  InputStream in=getClass().getResourceAsStream("/" + wrapper + ".precompiled.js");
  assertEquals(IOUtils.toString(in),js);
  in.close();
}

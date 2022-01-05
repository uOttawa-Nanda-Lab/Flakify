@Test public void localFileTakesPrecedenceOverClasspath() throws Exception {
  File localFile=new File(new File("."),"application.properties");
  assertThat(localFile.exists(),equalTo(false));
  try {
    Properties properties=new Properties();
    properties.put("the.property","fromlocalfile");
    OutputStream out=new FileOutputStream(localFile);
    try {
      properties.store(out,"");
    }
  finally {
      out.close();
    }
    this.initializer.onApplicationEvent(this.event);
    String property=this.environment.getProperty("the.property");
    assertThat(property,equalTo("fromlocalfile"));
  }
  finally {
    localFile.delete();
  }
}

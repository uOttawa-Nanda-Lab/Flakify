@Test public void testDatasourceSerialization() throws Exception {
  InitialContext context=new InitialContext();
  DataSource originalDs=(DataSource)context.lookup(JNDI_NAME);
  ByteArrayOutputStream bos=new ByteArrayOutputStream();
  ObjectOutput out=null;
  DataSource ds;
  ObjectInput in=null;
  try {
    out=new ObjectOutputStream(bos);
    out.writeObject(originalDs);
    byte[] bytes=bos.toByteArray();
    ByteArrayInputStream bis=new ByteArrayInputStream(bytes);
    try {
      in=new ObjectInputStream(bis);
      ds=(DataSource)in.readObject();
    }
  finally {
      try {
        bis.close();
      }
 catch (      IOException ex) {
      }
      try {
        if (in != null) {
          in.close();
        }
      }
 catch (      IOException ex) {
      }
    }
    Connection conn=ds.getConnection();
    ResultSet rs=conn.prepareStatement("select 1").executeQuery();
    Assert.assertTrue(rs.next());
    conn.close();
  }
  finally {
    try {
      if (out != null) {
        out.close();
      }
    }
 catch (    IOException ex) {
    }
    try {
      bos.close();
    }
 catch (    IOException ex) {
    }
  }
}

@Test public void testStoredContext() throws Exception {
  final ServiceName bindingName=ServiceName.JBOSS.append("foo-stored").append("again");
  bindObject(bindingName,new Context(){
    @Override public Object lookup(    Name name) throws NamingException {
      if ("blah/blah2".equals(name.toString())) {
        return new Integer(5);
      }
      return null;
    }
    @Override public Object lookup(    String name) throws NamingException {
      return lookup(new CompositeName(name));
    }
    @Override public void bind(    Name name,    Object obj) throws NamingException {
    }
    @Override public void bind(    String name,    Object obj) throws NamingException {
    }
    @Override public void rebind(    Name name,    Object obj) throws NamingException {
    }
    @Override public void rebind(    String name,    Object obj) throws NamingException {
    }
    @Override public void unbind(    Name name) throws NamingException {
    }
    @Override public void unbind(    String name) throws NamingException {
    }
    @Override public void rename(    Name oldName,    Name newName) throws NamingException {
    }
    @Override public void rename(    String oldName,    String newName) throws NamingException {
    }
    @Override public NamingEnumeration<NameClassPair> list(    Name name) throws NamingException {
      return null;
    }
    @Override public NamingEnumeration<NameClassPair> list(    String name) throws NamingException {
      return null;
    }
    @Override public NamingEnumeration<Binding> listBindings(    Name name) throws NamingException {
      if (!"hi/there".equals(name.toString()))       throw new IllegalArgumentException("Expected hi/there");
      return null;
    }
    @Override public NamingEnumeration<Binding> listBindings(    String name) throws NamingException {
      return null;
    }
    @Override public void destroySubcontext(    Name name) throws NamingException {
    }
    @Override public void destroySubcontext(    String name) throws NamingException {
    }
    @Override public Context createSubcontext(    Name name) throws NamingException {
      return null;
    }
    @Override public Context createSubcontext(    String name) throws NamingException {
      return null;
    }
    @Override public Object lookupLink(    Name name) throws NamingException {
      return null;
    }
    @Override public Object lookupLink(    String name) throws NamingException {
      return null;
    }
    @Override public NameParser getNameParser(    Name name) throws NamingException {
      return null;
    }
    @Override public NameParser getNameParser(    String name) throws NamingException {
      return null;
    }
    @Override public Name composeName(    Name name,    Name prefix) throws NamingException {
      return null;
    }
    @Override public String composeName(    String name,    String prefix) throws NamingException {
      return null;
    }
    @Override public Object addToEnvironment(    String propName,    Object propVal) throws NamingException {
      return null;
    }
    @Override public Object removeFromEnvironment(    String propName) throws NamingException {
      return null;
    }
    @Override public Hashtable<?,?> getEnvironment() throws NamingException {
      return null;
    }
    @Override public void close() throws NamingException {
    }
    @Override public String getNameInNamespace() throws NamingException {
      return null;
    }
  }
);
  final NamingContext ctx=new NamingContext(new CompositeName(),store,null);
  final Object obj=ctx.lookup(new CompositeName("foo-stored/again/blah/blah2"));
  ctx.listBindings("foo-stored/again/hi/there");
  assertNotNull(obj);
  assertEquals(new Integer(5),obj);
}

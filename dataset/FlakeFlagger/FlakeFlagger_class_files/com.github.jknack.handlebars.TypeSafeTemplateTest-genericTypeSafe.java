package com.github.jknack.handlebars;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class TypeSafeTemplateTest extends AbstractTest {

  public static class User {
    private String name;

    public User(final String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

  public static interface UserTemplate extends TypeSafeTemplate<User> {
    public UserTemplate setAge(int age);

    public UserTemplate setRole(String role);

    public void set();

    public void set(int v);
  }

  @Test public void genericTypeSafe() throws IOException{User user=new User("edgar");TypeSafeTemplate<User> userTemplate=compile("Hello {{name}}!").as();assertEquals("Hello edgar!",userTemplate.apply(user));}
}

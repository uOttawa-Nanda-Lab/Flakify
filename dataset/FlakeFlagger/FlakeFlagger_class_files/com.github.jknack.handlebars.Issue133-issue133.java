package com.github.jknack.handlebars;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class Issue133 extends AbstractTest {

  @Test public void issue133() throws IOException{shouldCompileTo("{{times nullvalue 3}}",$("nullvalue",null),"");shouldCompileTo("{{times nullvalue 3}}",$("nullvalue","a"),"aaa");}
}

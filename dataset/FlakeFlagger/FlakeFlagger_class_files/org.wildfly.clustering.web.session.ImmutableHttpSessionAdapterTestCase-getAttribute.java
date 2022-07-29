package org.wildfly.clustering.web.session;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.junit.Test;

public class ImmutableHttpSessionAdapterTestCase {
    private final ImmutableSession session = mock(ImmutableSession.class);
    private final ServletContext context = mock(ServletContext.class);
    private final HttpSession httpSession = new ImmutableHttpSessionAdapter(this.session, this.context);

    @Test public void getAttribute(){SessionAttributes attributes=mock(SessionAttributes.class);String name="name";Object expected=new Object();when(this.session.getAttributes()).thenReturn(attributes);when(attributes.getAttribute(name)).thenReturn(expected);Object result=this.httpSession.getAttribute(name);assertSame(expected,result);}
}

package io.elasticjob.lite.api.strategy;

import io.elasticjob.lite.util.env.IpUtils;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public final class JobInstanceTest {
    
    @Test public void assertGetIp(){assertThat(new JobInstance().getIp(),Is.is(IpUtils.getIp()));}
}

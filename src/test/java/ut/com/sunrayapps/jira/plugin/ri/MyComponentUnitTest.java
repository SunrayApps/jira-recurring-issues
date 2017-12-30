package ut.com.sunrayapps.jira.plugin.ri;

import org.junit.Test;
import com.sunrayapps.jira.plugin.ri.api.MyPluginComponent;
import com.sunrayapps.jira.plugin.ri.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}
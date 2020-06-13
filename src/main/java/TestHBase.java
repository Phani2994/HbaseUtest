import org.apache.hadoop.hbase.testclassification.MediumTests;
import org.apache.hadoop.hbase.testclassification.MiscTests;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertFalse;

/**
 * Make sure we can spin up a HBTU without a hbase-site.xml
 */
@Category({MiscTests.class, MediumTests.class})
public class TestHBase {
    @ClassRule
    public static final HBaseClassTestRule CLASS_RULE =
            HBaseClassTestRule.forClass(TestHBaseTestingUtilSpinup.class);

    private static final Logger LOG = LoggerFactory.getLogger(TestHBaseTestingUtilSpinup.class);
    private final static HBaseTestingUtility UTIL = new HBaseTestingUtility();

    @BeforeClass
    public static void beforeClass() throws Exception {
        UTIL.startMiniCluster();
        if (!UTIL.getHBaseCluster().waitForActiveAndReadyMaster(30000)) {
            throw new RuntimeException("Active master not ready");
        }
    }

    @AfterClass
    public static void afterClass() throws Exception {
        UTIL.shutdownMiniCluster();
    }

    @Test
    public void testGetMetaTableRows() throws Exception {
        List<byte[]> results = UTIL.getMetaTableRows();
        assertFalse("results should have some entries and is empty.", results.isEmpty());
    }

}

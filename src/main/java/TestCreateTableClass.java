import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;



public class TestCreateTableClass {

    private final static String tableName = "myTable";
    private final static HBaseTestingUtility UTIL = new HBaseTestingUtility();

    public static void main( String[] args ) throws Exception {

        //Start the "mini cluster"
        /*...*/




        @Before
        public void setup() throws Exception
        //StartMiniClusterOption utility = StartMiniClusterOption.Builder.build();
        StartMiniClusterOption option = StartMiniClusterOption.builder().numMasters(3).rsClass(MyRegionServer.class).createWALDir(true).build();

        @After
        public static void afterClass() throws Exception {
            UTIL.shutdownMiniCluster();
        }
        //Get the configuration
        //Configuration conf =
        Configuration conf = HBaseConfiguration.create();



        //Instantiate a connection
        Connection connection = ConnectionFactory.createConnection(conf);
        Admin admin = connection.getAdmin();
        //Define table "myTable"

        TableName table = TableName.valueOf(tableName);
        TableDescriptorBuilder tDB = TableDescriptorBuilder.newBuilder(table);
        ColumnFamilyDescriptorBuilder cDB= ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("cf1"))
                .setCompressionType(Compression.Algorithm.GZ);
        //HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tableName)); //Depricated
        tDB.setColumnFamily(cDB.build());
        tDB.build();

        //table.addFamily(new HColumnDescriptor("cf1").setCompressionType(Compression.Algorithm.NONE));//Depricated

        if(!admin.tableExists(table.getNameAsString())) {
            System.out.println(" Creating new table ");
            admin.createTable(table);
            System.out.println("Done");
        }
}}

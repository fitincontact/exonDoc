import com.fitincontact.exondoc.storage.StorageApi;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class NameTest {
    final static Logger LOG = Logger.getLogger(NameTest.class);
    StorageApi api = new StorageApi();

    @Test
    public void Test(){
        api.start();
    }

}
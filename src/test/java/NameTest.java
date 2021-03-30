import com.fitincontact.exondoc.storage.Name;
import com.fitincontact.exondoc.storage.StorageApi;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import java.util.List;

public class NameTest {
    final static Logger LOG = Logger.getLogger(NameTest.class);
    StorageApi api = new StorageApi();

    @Test
    public void Test(){
        api.start();
        final List<Name> names = api.getNames();
        api.stop();
    }

}
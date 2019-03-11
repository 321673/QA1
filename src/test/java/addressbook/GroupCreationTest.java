package addressbook;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase{


    @Test
    public void testGroupsCreation() throws Exception {

        gotoGroupPage("groups");
        initGroupCreation("new");
        fillGroupForm(new GroupData("Name", "Header", "Footer"));
        submitGroupCreaton();
        returnToGroupPage("group page");

    }

}

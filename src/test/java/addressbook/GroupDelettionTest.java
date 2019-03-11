package addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupDelettionTest extends  TestBase{

    @Test
    public void testGroupDeletion() {
        gotoGroupPage("groups");
        selectGroup("(.//*[normalize-space(text()) and normalize-space(.)='Name'])[4]/input[1]");
        deleteGroup("(.//*[normalize-space(text()) and normalize-space(.)='Name'])[4]/following::input[2]");
        returnToGroupPage("group page");
    }


}

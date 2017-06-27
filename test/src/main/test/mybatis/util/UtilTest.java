package mybatis.util;

import mybatis.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UtilTest {

    /**
     * return only 1 person
     */
    @Test
    public void returnOne() {
        Util util = new Util();
        List<Person> p = util.getContactsFromRequest(
                "{\"id\":1,\"name\":\"test\",\"phone\":\"me\",\"email\":\"please\"}");
        Assert.assertEquals(1, p.size());
    }

    /**
     * return many person
     * */
    @Test
    public void returnMany() {
        Util util = new Util();
        List<Person> p = util.getContactsFromRequest(
                //difference between this '['
                "[{\"id\":1,\"name\":\"test\",\"phone\":\"me\",\"email\":\"please\"}," +
                        "{\"id\":26,\"name\":\"New Guy\",\"phone\":\"+7-(000)-000-00-00\",\"email\":\"example@mail.ru\"}," +
                        "{\"id\":27,\"name\":\"New Guy\",\"phone\":\"+7-(000)-000-00-00\",\"email\":\"example@mail.ru\"}]");
        Assert.assertEquals(3, p.size());
    }


}
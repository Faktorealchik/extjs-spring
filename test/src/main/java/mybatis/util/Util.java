package mybatis.util;


import mybatis.model.Person;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Util {

    public List<Person> getContactsFromRequest(Object data) {
        List<Person> list;
        //it is an array - have to cast to array object
        if (data.toString().contains("[")) {
            list = getListContactsFromJSON(data);
        } else {//it is only one object - cast to object/bean
            Person person = getContactFromJSON(data);
            list = new ArrayList<>();
            list.add(person);
        }
        return list;
    }

    private Person getContactFromJSON(Object data) {
        JSONObject jsonObject = JSONObject.fromObject(data);
        return (Person) JSONObject.toBean(jsonObject, Person.class);
    }

    @SuppressWarnings("unchecked")
    private List<Person> getListContactsFromJSON(Object data) {
        JSONArray jsonArray = JSONArray.fromObject(data);
        return (List<Person>) JSONArray.toList(jsonArray, Person.class);
    }

    @SuppressWarnings("unchecked")
    public List<Integer> getListIdFromJSON(Object data) {
        JSONArray jsonArray = JSONArray.fromObject(data);
        return (List<Integer>) JSONArray.toList(jsonArray, Integer.class);
    }
}
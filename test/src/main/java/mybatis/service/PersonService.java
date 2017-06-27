package mybatis.service;

import mybatis.dao.Mapper;
import mybatis.model.Person;
import mybatis.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private Mapper mapper;
    private Util util;

    /**
     * gets all person`s
     * @return List<Person>
     * */
    public List<Person> getAllPerson() {
        return mapper.getAllPerson();
    }

    /**
     * gets all new person`s
     * @return new List<Persons>
     * */
    public List<Person> create(Object data) {
        List<Person> newContacts = new ArrayList<>();
        List<Person> list = util.getContactsFromRequest(data);
        for (Person person : list) {
            mapper.createPerson(person);
            newContacts.add(person);
        }
        return newContacts;
    }

    /**
     * gets all updated persons
     * @return List<Person>
     * */
    public List<Person> update(Object data) {
        List<Person> returnContacts = new ArrayList<>();
        List<Person> updatedContacts = util.getContactsFromRequest(data);
        for (Person person : updatedContacts) {
            mapper.updatePerson(person.getId(),
                    person.getName(),
                    person.getPhone(),
                    person.getEmail());
            returnContacts.add(person);
        }
        return returnContacts;
    }

    /**
     * delete all persons that we have pick out
     * */
    public List<Person> delete(Object data) {
        //if it is an array - we need to cast to object
        if (data.toString().contains("[")) {
            List<Integer> deleteContacts = util.getListIdFromJSON(data);
            for (Integer id : deleteContacts) {
                mapper.deletePerson(id);
            }
        } else { //it is only one object - cast to object
            Integer id = Integer.parseInt(data.toString());
            mapper.deletePerson(id);
        }
        return new ArrayList<>();
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Autowired
    public void setUtil(Util util) {
        this.util = util;
    }

}
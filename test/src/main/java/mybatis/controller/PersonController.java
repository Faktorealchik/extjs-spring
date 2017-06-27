package mybatis.controller;

import mybatis.model.Person;
import mybatis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PersonController {

    private PersonService personService;

    @RequestMapping(value = "/person/view.action")
    public
    @ResponseBody
    Map<String, Object> view() {
        return getMap(personService.getAllPerson());
    }

    @RequestMapping(value = "/person/create.action")
    public
    @ResponseBody
    Map<String, Object> create(@RequestParam Object data) {
        return getMap(personService.create(data));
    }

    @RequestMapping(value = "/person/update.action")
    public
    @ResponseBody
    Map<String, Object> update(@RequestParam Object data) {
        return getMap(personService.update(data));
    }

    @RequestMapping(value = "/person/delete.action")
    public
    @ResponseBody
    Map<String, Object> delete(@RequestParam Object data) {
        return getMap(personService.delete(data));
    }

    /**
     * generates json that will be sent to crud-grid.js
     */
    private Map<String, Object> getMap(List<Person> persons) {
        Map<String, Object> modelMap = new HashMap<>();
        modelMap.put("total", persons.size());
        modelMap.put("data", persons);
        modelMap.put("success", true);
        return modelMap;
    }

    @Autowired
    public void setContactService(PersonService personService) {
        this.personService = personService;
    }
}

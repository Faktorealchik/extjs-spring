package mybatis.dao;

import mybatis.model.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Mapper {
    /**
     * Retrieves the List of person
     *
     * @return person list
     */
    List<Person> getAllPerson();

    /******************************************
     * Create a person
     * @param person
     */
    void createPerson(Person person);

    /******************************************
     * Update a person
     * @param id, name
     */
    void updatePerson(@Param("id") int id,
                      @Param("name") String name,
                      @Param("phone") String phone,
                      @Param("email") String email);

    /******************************************
     * Delete a person
     * @param id
     */
    void deletePerson(int id);
}

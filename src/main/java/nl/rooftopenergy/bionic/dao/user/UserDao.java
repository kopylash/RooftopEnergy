package nl.rooftopenergy.bionic.dao.user;

import nl.rooftopenergy.bionic.dao.Dao;
import nl.rooftopenergy.bionic.entity.User;

public interface UserDao extends Dao<User, Long> {

    User findByName(String name);

}

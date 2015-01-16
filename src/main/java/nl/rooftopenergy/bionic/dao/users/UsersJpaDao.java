package nl.rooftopenergy.bionic.dao.users;

import nl.rooftopenergy.bionic.dao.JpaDao;
import nl.rooftopenergy.bionic.entity.Users;
import org.springframework.stereotype.Repository;

/**
 * Created by Владислав on 14.01.2015.
 */
@Repository
public class UsersJpaDao extends JpaDao<Users,String> implements UsersDao {

    UsersJpaDao() {
        super(Users.class);
    }
}

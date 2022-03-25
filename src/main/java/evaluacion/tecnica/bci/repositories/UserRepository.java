package evaluacion.tecnica.bci.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import evaluacion.tecnica.bci.models.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>
{
	Iterable<User> findAll();
    Optional<User> findById(String id);
    <S extends User> S save(S entity);
    List<User> findByEmail(String email);
}

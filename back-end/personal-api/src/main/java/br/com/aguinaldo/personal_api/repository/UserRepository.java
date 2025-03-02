package br.com.aguinaldo.personal_api.repository;

import br.com.aguinaldo.personal_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Query(value =
            "     SELECT us.* " +
            "       FROM tb_user us" +
            "      WHERE us.active = 1", nativeQuery = true)
    List<User> findAllUsersActive();

    @Modifying //indica que a consulta que está sendo executada modifica o estado dos dados no banco de dados, em vez de apenas lê-los.
    @Query(value =
            "   UPDATE tb_user us " +
            "      SET us.active = 0 " +
            "    WHERE us.id = :id", nativeQuery = true)
    void softDelete(@Param("id") Long id);
}

package br.com.aguinaldo.personal_api.service;

import br.com.aguinaldo.personal_api.model.User;
import br.com.aguinaldo.personal_api.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User save(User user) {
        userValidator(user);
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsersActive();
    }

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O usuário com o ID"  + id + " não existe.")); // o orElseThow já cuida do caso Optional estar vazio
    }

    @Transactional
    public User updateUser(Long id, User userUpdate) {
       User existingUser = userRepository.findById(id)
               .orElseThrow(() -> new IllegalArgumentException("O usuário com o ID"  + id + " não existe."));

        existingUser.setUsername(userUpdate.getUsername());
        existingUser.setEmail(userUpdate.getEmail());
        existingUser.setPassword(userUpdate.getPassword());

       return userRepository.save(existingUser);
    }

    @Transactional
    public void deleteUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("O ID informado " + id + " não existe."));

        if(!user.isActive()) {
            throw new IllegalArgumentException("O usuário já está inativo.");
        }

        userRepository.softDelete(id);
    }


    private void userValidator(User user) {
        if (!StringUtils.hasText(user.getUsername())) {
            throw new IllegalArgumentException("O nome deve ser preenchido.");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("O username informado já existe.");
        }

        if (!StringUtils.hasText(user.getEmail())) {
            throw new IllegalArgumentException("O e-mail deve ser preenchido.");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("O e-mail informado já existe.");
        }
    }
}

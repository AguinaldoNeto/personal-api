package br.com.aguinaldo.personal_api.service.translator;

import br.com.aguinaldo.personal_api.model.User;
import br.com.aguinaldo.personal_api.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserTranslator {

    private final PasswordEncoder encoder;

    @Autowired
    public UserTranslator(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public UserDTO toDTO(User model) {
        final UserDTO dto = new UserDTO();
        dto.setId(model.getId());
        dto.setUsername(model.getUsername());
        dto.setEmail(model.getEmail());
        dto.setPassword(model.getPassword());
        dto.setCreatedAt(model.getCreatedAt());
        dto.setUpdatedAt(model.getUpdatedAt());
        return dto;
    }

    public User toEntity(UserDTO dto) {
        String cryptoPassword = encoder.encode(dto.getPassword());

        final User model = new User();
        model.setUsername(dto.getUsername());
        model.setEmail(dto.getEmail());
        model.setPassword(cryptoPassword);
        model.setActive(Boolean.TRUE);
        return model;

    }


}

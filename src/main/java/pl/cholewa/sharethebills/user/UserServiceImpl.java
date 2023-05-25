package pl.cholewa.sharethebills.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.cholewa.sharethebills.dictionary.Dictionary;
import pl.cholewa.sharethebills.dictionary.DictionaryRepository;
import pl.cholewa.sharethebills.dictionaryValue.DictionaryValue;
import pl.cholewa.sharethebills.dictionaryValue.DictionaryValueRepository;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserAttributeRepository userAttributeRepository;
    private final DictionaryValueRepository dictionaryValueRepository;
    private final DictionaryRepository dictionaryRepository;
    @Override
    public UserResponse create(CreateUserRequest request) {
        Dictionary dictionary = dictionaryRepository
                .findByName("CLIDAT")
                .orElseThrow(()-> new IllegalArgumentException("Brak slownika z atrybutami identyfikacyjnymi"));
        DictionaryValue dictionaryValue = dictionaryValueRepository
                .findByContent(request.attrType())
                .orElseThrow(
                ()-> new IllegalArgumentException("Brak w słowniku takiego atrybutu"));
        User user = User.builder()
                .login(request.login())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .build();

        userRepository.save(user);

        UserAttribute userAttribute = UserAttribute.builder()
                .user(user)
                .type(request.attrType())
                .content(request.attrContent())
                .build();
        userAttributeRepository.save(userAttribute);
        return userResponse(user, userAttribute);
    }
    private UserResponse userResponse(User user,UserAttribute userAttribute){
        return new UserResponse(
                user.getLogin(),
                user.getFirstName(),
                user.getLastName(),
                userAttribute.getUser().getUserAttributesList()
        );
    }
}

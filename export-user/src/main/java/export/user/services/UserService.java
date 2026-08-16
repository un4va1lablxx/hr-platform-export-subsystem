package export.user.services;

import export.user.clients.OrganizationClient;
import export.user.dtos.OrganizationDto;
import export.user.dtos.UserOrganizationDto;
import export.user.entities.User;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import export.user.repositories.UserRepository;
import export.user.dtos.UserInfo;
import export.user.mappers.UserMapper;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OrganizationClient organizationClient;

    @Transactional(readOnly = true)
    public List<UserOrganizationDto> findAllUsers() {
        List<User> users = userRepository.findAll();

        try {
            List<OrganizationDto> organizations = organizationClient.getAllOrganizations();
            Map<UUID, OrganizationDto> organizationsMap = organizations.stream()
                    .collect(Collectors.toMap(OrganizationDto::getId, Function.identity()));

            return users.stream()
                    .map(user -> {
                        UserInfo userInfo = userMapper.toUserInfo(user);
                        UUID organizationId = user.getOrganizationId();
                        OrganizationDto organizationDto = organizationsMap.get(organizationId);

                        if (organizationDto == null) {
                            return createUserDtoWithError(userInfo, "Организация не найдена");
                        }
                        return userMapper.toUserOrganizationDto(userInfo, organizationDto);
                    })
                    .toList();

        } catch (FeignException e) {
            log.error("Не удалось получить организации: {}", e.getMessage());
            return users.stream()
                    .map(user -> createUserDtoWithError(userMapper.toUserInfo(user), "Сервис организаций недоступен"))
                    .toList();
        } catch (Exception e) {
            log.error("Непредвиденная ошибка при получении организаций: {}", e.getMessage());
            return users.stream()
                    .map(user -> createUserDtoWithError(userMapper.toUserInfo(user), "Ошибка при получении данных об организациях"))
                    .toList();
        }
    }

    private UserOrganizationDto createUserDtoWithError(UserInfo userInfo, String errorMessage) {
        OrganizationDto errorOrg = new OrganizationDto(
                null,
                "N/A",
                null,
                errorMessage
        );
        return new UserOrganizationDto(userInfo, errorOrg);
    }
}
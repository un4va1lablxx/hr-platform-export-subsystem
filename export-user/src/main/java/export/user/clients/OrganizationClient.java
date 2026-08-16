package export.user.clients;

import export.user.dtos.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "organization-service", url = "${feign.client.config.organization-service.url}")
public interface OrganizationClient {

    @GetMapping("/export-organization")
    List<OrganizationDto> getAllOrganizations();
}

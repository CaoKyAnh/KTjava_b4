package hutech.com.demo.Service;

import hutech.com.demo.repository.RoleRepository;
import hutech.com.demo.repository.UserRepository;
import hutech.com.demo.RequestEntities.RoleCreate;
import hutech.com.demo.RequestEntities.RoleUpdate;
import hutech.com.demo.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAllRoles() {return roleRepository.findAll();}
    public Role getRoleById(String id) {
        return roleRepository.findById(id).orElseThrow(
                () -> {
                   throw  new RuntimeException("Khong tim thay id" + id);
                }
        );
    }
    public Role createRole(RoleCreate roleCreate) {
        try {
            Role role = new Role();
            role.setRole_name(roleCreate.getRole_name());
            roleRepository.save(role);
            return role;
        }catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }
    }
    public Role updateRole(RoleUpdate roleUpdate) {
        try {
            Role role = getRoleById(roleUpdate.getRole_id());
            role.setRole_name(roleUpdate.getRole_name());
            roleRepository.save(role);
            return role;
        }
        catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }
    }
    public void deleteRole(String role_id) {roleRepository.deleteById(role_id);}
}

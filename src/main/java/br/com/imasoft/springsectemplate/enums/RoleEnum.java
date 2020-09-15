package br.com.imasoft.springsectemplate.enums;

public enum RoleEnum {

    ROLE_ADMIN(1, "ROLE_ADMIN"),
    ROLE_COMMON(2, "ROLE_COMMON");

    private Integer id;
    private String roleName;

    RoleEnum(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}

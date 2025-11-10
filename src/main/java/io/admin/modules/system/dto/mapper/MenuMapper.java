package io.admin.modules.system.dto.mapper;

import io.admin.framework.config.data.sysmenu.MenuDefinition;
import io.admin.modules.system.dto.response.MenuResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MenuMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "id", target = "key")
    @Mapping(source = "name", target = "label")
    MenuResponse menuToResponse(MenuDefinition menu);

    List<MenuResponse> menuToResponseList(List<MenuDefinition> menuList);

    @AfterMapping
    default void handleNullFields(MenuDefinition source, @MappingTarget MenuResponse target) {
        if (target.getPath() == null) {
            target.setPath("");
        }
    }
}

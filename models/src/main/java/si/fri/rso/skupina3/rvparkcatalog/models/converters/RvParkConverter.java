package si.fri.rso.skupina3.rvparkcatalog.models.converters;

import si.fri.rso.skupina3.rvparkcatalog.lib.RvPark;
import si.fri.rso.skupina3.rvparkcatalog.models.entities.RvParkEntity;

public class RvParkConverter {

    public static RvPark toDto(RvParkEntity entity) {
        RvPark dto = new RvPark();
        dto.setRv_park_id(entity.getRv_park_id());
        dto.setUser_id(entity.getUser_id());
        dto.setName(entity.getName());
        dto.setCost_per_day(entity.getCost_per_day());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    public static RvParkEntity toEntity(RvPark dto) {
        RvParkEntity entity = new RvParkEntity();
        entity.setRv_park_id(dto.getRv_park_id());
        entity.setUser_id(dto.getUser_id());
        entity.setName(dto.getName());
        entity.setCost_per_day(dto.getCost_per_day());
        entity.setDescription(dto.getDescription());

        return entity;
    }
}

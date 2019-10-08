package es.urjc.code.mapper;

public interface Transformer<Entity, Dto> {

    Entity fromDtoToEntity(Dto dto);
    Dto fromEntityToDto(Entity entity);
}

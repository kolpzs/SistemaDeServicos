package model.repository;

public interface BasicCrud {

    Object create(Object object);

    Object findById(Long id);

    Object updateById(Object object);

    Object delete(Long id);

}

package model.repositories;

public interface BasicCrud {
	Object create (Object obj);
	
	Object update (Object obj);
	
	Object findById(Long id);

	Object updateById(Object object);

	void delete(Long id);
}

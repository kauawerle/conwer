package controller;

import model.entities.AddressEntity;
import model.entities.UserEntity;
import model.services.AddressService;

public class AddressController {
    private final AddressService svc = new AddressService();

    public AddressEntity buscarPorCep(String cep) {
        return svc.buscarPorCep(cep);
    }

    public AddressEntity create(AddressEntity a) { return svc.create(a); }
    public AddressEntity update(AddressEntity a) { return svc.update(a); }
    public AddressEntity findById(Long id) { return svc.findById(id); }
    public void delete(Long id) { svc.delete(id); }

    public UserEntity atrelarAoUsuario(Long userId, AddressEntity addr) {
        return svc.attachAddressToUser(userId, addr);
    }
}

package model.services;

import model.entities.AddressEntity;
import model.entities.UserEntity;
import model.repositories.AddressRepository;
import model.repositories.UserRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class AddressService {
    private final AddressRepository addressRepo = new AddressRepository();
    private final UserRepository userRepo = new UserRepository();

    public AddressEntity create(AddressEntity a) {
        return (AddressEntity) addressRepo.create(a);
    }

    public AddressEntity update(AddressEntity a) {
        return (AddressEntity) addressRepo.update(a);
    }

    public AddressEntity findById(Long id) {
        return (AddressEntity) addressRepo.findById(id);
    }

    public void delete(Long id) {
        addressRepo.delete(id);
    }

    public UserEntity attachAddressToUser(Long userId, AddressEntity addressData) {
        UserEntity user = (UserEntity) userRepo.findById(userId);
        if (user == null) throw new IllegalArgumentException("Usuário não encontrado: " + userId);

        AddressEntity addr = user.getAddress();
        if (addr == null) {
            addr = create(addressData); // cria novo
        } else {
            // atualiza existente
            addr.setCep(addressData.getCep());
            addr.setCidade(addressData.getCidade());
            addr.setRua(addressData.getRua());
            addr.setNumero(addressData.getNumero());
            addr.setComplemento(addressData.getComplemento());
            addr.setUf(addressData.getUf());
            addr = update(addr);
        }

        // associa ao usuário e persiste o usuário
        user.setAddress(addr);
        userRepo.update(user); // seu UserRepository já faz merge

        return user;
    }

    // ===== ViaCEP (opcional) =====
    public AddressEntity buscarPorCep(String cep) {
        try {
            String urlStr = "https://viacep.com.br/ws/" + cep.replaceAll("\\D","") + "/json/";
            HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(8000);
            con.setReadTimeout(8000);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))) {
                StringBuilder resp = new StringBuilder();
                for (String line; (line = in.readLine()) != null; ) resp.append(line);

                JSONObject json = new JSONObject(resp.toString());
                if (json.optBoolean("erro", false)) {
                    throw new IllegalArgumentException("CEP não encontrado.");
                }

                AddressEntity addr = new AddressEntity();
                addr.setCep(json.optString("cep"));
                addr.setCidade(json.optString("localidade"));
                addr.setRua(json.optString("logradouro"));
                addr.setUf(json.optString("uf"));
                // numero e complemento permanecem para o usuário informar
                return addr;
            }
        } catch (Exception e) {
            throw new RuntimeException("Falha ao consultar ViaCEP: " + e.getMessage(), e);
        }
    }
}
